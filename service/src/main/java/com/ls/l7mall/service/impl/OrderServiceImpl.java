package com.ls.l7mall.service.impl;

import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ls.l7mall.dao.OrderDao;
import com.ls.l7mall.dao.OrderItemDao;
import com.ls.l7mall.dao.PayInfoDao;
import com.ls.l7mall.entity.Order;
import com.ls.l7mall.entity.OrderItem;
import com.ls.l7mall.entity.PayInfo;
import com.ls.l7mall.global.Const;
import com.ls.l7mall.global.ResponseEntity;
import com.ls.l7mall.service.OrderService;
import com.ls.l7mall.util.BigDecimalUtils;
import com.ls.l7mall.util.DateTimeUtil;
import com.ls.l7mall.util.FTPServerUtils;
import com.ls.l7mall.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author laijs
 * @date 2020-3-20-10:07
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    public static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private static AlipayTradeService tradeService;
    {
        /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
         *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
         */
        Configs.init("zfbinfo.properties");

        /** 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

    }
    
    @Autowired
    @Qualifier("orderDao")
    private OrderDao orderDao;

    @Autowired
    @Qualifier("orderItemDao")
    private OrderItemDao orderItemDao;
    
    @Autowired
    @Qualifier("payInfoDao")
    private PayInfoDao payInfoDao;

    public ResponseEntity pay(Integer userId, Long orderNo, String path) {
        HashMap map = Maps.newHashMap();
        Order order = orderDao.selectOrderByUserIdAndOrderNo(userId, orderNo);
        if (order == null) {
            return ResponseEntity.responesWhenError("用户无该订单");
        }
        map.put("orderNo", order.getOrderNo().toString());

        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        String outTradeNo = order.getOrderNo().toString();

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        String subject = new StringBuilder().append("L7Mall扫码支付，订单号：").append(outTradeNo).toString();

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = order.getPayment().toString();

        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = new StringBuilder().append("订单").append(outTradeNo).append("购买商品共").append(totalAmount).append("元").toString();

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "test_operator_id";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "test_store_id";

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");

        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";

        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
        List<OrderItem> orderItems = orderItemDao.selectByUserIdAndOrderNo(userId, orderNo);
        for (OrderItem orderItem : orderItems) {
            GoodsDetail goods = GoodsDetail.newInstance(orderItem.getProductId().toString(), orderItem.getProductName(),
                    BigDecimalUtils.multiply(orderItem.getCurrentUnitPrice().doubleValue(), 100).longValue(), orderItem.getQuantity());
            goodsDetailList.add(goods);
        }
        /*// 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
        GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "xxx小面包", 1000, 1);
        // 创建好一个商品后添加至商品明细列表
        goodsDetailList.add(goods1);
        // 继续创建并添加第一条商品信息，用户购买的产品为“黑人牙刷”，单价为5.00元，购买了两件
        GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "xxx牙刷", 500, 2);
        goodsDetailList.add(goods2);*/

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
                .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
                .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
                .setTimeoutExpress(timeoutExpress)
                // 回调函数访问路径
                .setNotifyUrl(PropertiesUtil.getProperty("alipay.callback.url"))//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setGoodsDetailList(goodsDetailList);
        
        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                logger.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);

                // 将生成的二维码保存到FTP服务器中

                // 创建目录
                File file = new File(path);
                if (!file.exists()) {
                    file.setExecutable(true);
                    file.mkdirs();
                }
                // 需要修改为运行机器上的路径
                String filePath = String.format(path + "/qr-%s.png", response.getOutTradeNo());
                String fileName = String.format("/qr-%s.png", response.getOutTradeNo());
                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
                // 上传文件
                File targetFile = new File(path, fileName);
                try {
                    FTPServerUtils.upload(Lists.newArrayList(targetFile));
                } catch (IOException e) {
                    logger.error("上传二维码异常", e);
                }
                logger.info("filePath:" + filePath);
                String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFile.getName();
                map.put("url",url);
                return ResponseEntity.responesWhenSuccess(map);

            case FAILED:
                logger.error("支付宝预下单失败!!!");
                return ResponseEntity.responesWhenError("支付宝预下单失败!!!");

            case UNKNOWN:
                logger.error("系统异常，预下单状态未知!!!");
                return ResponseEntity.responesWhenError("系统异常，预下单状态未知!!!");

            default:
                logger.error("不支持的交易状态，交易返回异常!!!");
                return ResponseEntity.responesWhenError("不支持的交易状态，交易返回异常!!!");
        }

    }

    // 处理支付宝的回调
    public ResponseEntity aliCallback(Map<String,String> params){
        // 从回调中获取订单号、交易号、交易状态码
        Long orderNo = Long.parseLong(params.get("out_trade_no"));
        String tradeNo = params.get("trade_no");
        String tradeStatus = params.get("trade_status");
        // 根据订单号获取订单
        Order order = orderDao.selectOrderOrderNo(orderNo);
        if(order == null){
            return ResponseEntity.responesWhenError("非本商场订单");
        }
        // 根据订单状态判断支付宝是否重复回调了
        if(order.getStatus() >= Const.OrderStatus.PAID.getCode()){
            return ResponseEntity.responesWhenSuccess("支付宝重复回调");
        }
        // 根据交易状态判断是否交易成功
        if(Const.TradeStatus.TRADE_STATUS_TRADE_SUCCESS.equals(tradeStatus)){
            // 交易成功--修改订单内容
            order.setPaymentTime(DateTimeUtil.strToDate(params.get("gmt_payment")));
            order.setStatus(Const.OrderStatus.PAID.getCode());
            orderDao.updateByPrimaryKeySelective(order);
        }
        
        // 将交易详情持久化
        PayInfo payInfo = new PayInfo(order.getUserId(), order.getOrderNo(), Const.payPlatformEnum.ALIPAY.getCode(), tradeNo, tradeStatus);
        payInfoDao.insert(payInfo);
        return ResponseEntity.responesWhenSuccess();

    }
    
    // 查询订单的支付状态
    public ResponseEntity queryOrderPayStatus(Integer userId,Long orderNo){
        Order order = orderDao.selectOrderByUserIdAndOrderNo(userId, orderNo);
        if (order == null) {
            return ResponseEntity.responesWhenError("用户无该订单");
        }
        if(order.getStatus() >= Const.OrderStatus.PAID.getCode()){
            return ResponseEntity.responesWhenSuccess();
        }
        return ResponseEntity.responesWhenError();
    }
    
    // 简单打印应答
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            logger.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                logger.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
                        response.getSubMsg()));
            }
            logger.info("body:" + response.getBody());
        }
    }

}
