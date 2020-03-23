package com.ls.l7mall.service;

import com.github.pagehelper.PageInfo;
import com.ls.l7mall.global.ResponseEntity;

import java.util.Map;

/**
 * @author laijs
 * @date 2020-3-20-10:07
 */
public interface OrderService {
// portal   
    // 创键新订单
    public ResponseEntity createOrder(Integer userId,Integer shippingId);
    // 查询订单中的商品信息
    public ResponseEntity getOrderCartProduct(Integer userId);
    // 取消订单
    public ResponseEntity cancelOrder(Integer userId,Long orderNo);
    // 订单列表
    public ResponseEntity listOrder(Integer userId,Integer pageNum,Integer pageSize);
    // 订单详情
    public ResponseEntity orderDetail(Integer userId,Long orderNo);
 
//backend    
    // 订单列表
    public ResponseEntity listManageOrder(Integer pageNum,Integer pageSize);
    // 按订单号查询
    public ResponseEntity<PageInfo> search(Long orderNo, Integer pageNum, Integer pageSize);
    // 订单详情
    public ResponseEntity detail(Long orderNo);
    // 订单发货
    public ResponseEntity sendGoods(Long orderNo);
    
    
// 支付    
    public ResponseEntity pay(Integer userId, Long orderNo, String path);
    public ResponseEntity aliCallback(Map<String,String> params);
    public ResponseEntity queryOrderPayStatus(Integer userId,Long orderNo);
}
