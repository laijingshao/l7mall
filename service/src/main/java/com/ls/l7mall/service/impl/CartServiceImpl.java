package com.ls.l7mall.service.impl;

import com.google.common.collect.Lists;
import com.ls.l7mall.dao.CartDao;
import com.ls.l7mall.dao.ProductDao;
import com.ls.l7mall.entity.Cart;
import com.ls.l7mall.entity.Product;
import com.ls.l7mall.global.Const;
import com.ls.l7mall.global.ResponseCode;
import com.ls.l7mall.global.ResponseEntity;
import com.ls.l7mall.service.CartService;
import com.ls.l7mall.util.BigDecimalUtils;
import com.ls.l7mall.vo.CartProductVo;
import com.ls.l7mall.vo.CartVo;
import com.ls.l7mall.vo.ProductListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author laijs
 * @date 2020-3-18-11:33
 */
@Service("cartService")
public class CartServiceImpl implements CartService {
    
    @Autowired
    @Qualifier("cartDao")
    private CartDao cartDao;
    
    @Autowired
    @Qualifier("productDao")
    private ProductDao productDao;
    
    // 展示购物车商品的方法
    private CartVo getList(Integer userId){
        CartVo cartVo = new CartVo();
        List<Cart> carts = cartDao.selectCartByUserId(userId);
        List<CartProductVo> cartProductVos = Lists.newArrayList();
        
        // 用于保存所有的购物车信息的总价
        BigDecimal cartTotalPrice = new BigDecimal("0");

        if(!carts.isEmpty()){
            // 每条购物车对应一个CartProductVo，遍历用户所有的购物车信息，保存到关于CartProductVo的集合中
            for (Cart cart : carts) {
                CartProductVo cartProductVo = new CartProductVo();
                cartProductVo.setId(cart.getId());
                cartProductVo.setUserId(cart.getUserId());
                cartProductVo.setProductId(cart.getProductId());
                // 根据productId获取product
                Product product = productDao.selectById(cart.getProductId());
                if(product != null){
                    cartProductVo.setProductName(product.getName());
                    cartProductVo.setProductSubtitle(product.getSubtitle());
                    cartProductVo.setProductMainImage(product.getMainImage());
                    cartProductVo.setProductPrice(product.getPrice());
                    cartProductVo.setProductStatus(product.getStatus());
                    cartProductVo.setProductStock(product.getStock());
                    
                    // 根据商品的库存确定商品的数量
                    Integer quantity = cart.getQuantity();
                    if(product.getStock() < quantity){
                        // 库存不足，购买限制为库存数
                        quantity = product.getStock();
                        cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_FAIL);
                        // 修改购物车中quantity
                        cartDao.updateByPrimaryKeySelective(new Cart(cart.getId(),quantity));
                    } else {
                        // 库存充足
                        cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_SUCCESS);
                    }
                    cartProductVo.setQuantity(quantity);
                    cartProductVo.setProductChecked(cart.getChecked());
                    // 根据数量和单价确定当前商品总价
                    BigDecimal productTotalPrice = BigDecimalUtils.multiply(quantity.doubleValue(), product.getPrice().doubleValue());
                    cartProductVo.setProductTotalPrice(productTotalPrice);

                    // 总价相应增加
                    if(product.getStatus() == Const.Cart.CHECKED){
                        cartTotalPrice = BigDecimalUtils.add(cartTotalPrice.doubleValue(), productTotalPrice.doubleValue());
                    }
                    
                }
                cartProductVos.add(cartProductVo);
            }
        }
        cartVo.setCartProductVoList(cartProductVos);
        cartVo.setCartTotalPrice(cartTotalPrice);
        // 全选状态：cart表中不存在user_Id为当前userId且checked为0的产品
        cartVo.setAllChecked(this.getAllCheckedStatus(userId));

        return cartVo;
    }
    
    // 判断是否为全选状态
    private boolean getAllCheckedStatus(Integer userId){
        if(userId == null){
            return false;
        }
        return cartDao.getAllCheckedByUserId(userId) == 0;

    }
    
    
   // list.do业务方法
    public ResponseEntity<CartVo> list(Integer userId){
        CartVo cartVo = this.getList(userId);
        return ResponseEntity.responesWhenSuccess(cartVo);
    }

    // add.do业务方法
    public ResponseEntity<CartVo> add(Integer userId,Integer productId,Integer count){
        if(productId == null || count == null){
            return ResponseEntity.responesWhenError(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDecs());
        }
        // 判断添加的product是否已经在购物车中
        Cart cart = cartDao.selectByUserIdAndProductId(userId, productId);
        if(cart == null){
            Cart cart1 = new Cart(userId, productId, count, Const.Cart.CHECKED);
            cartDao.insert(cart1);
        } else {
            count = cart.getQuantity() + count;
            cart.setQuantity(count);
            cartDao.updateByPrimaryKeySelective(cart);
        }
        return this.list(userId);
    }
    
    // update.do业务方法
    public ResponseEntity<CartVo> update(Integer userId,Integer productId,Integer count){
        if(productId == null || count == null){
            return ResponseEntity.responesWhenError(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDecs());
        }
        Cart cart = cartDao.selectByUserIdAndProductId(userId, productId);
        if(cart != null) {
            cart.setQuantity(count);
            cartDao.updateByPrimaryKeySelective(cart);
        }
        return this.list(userId);
    }

    // delete_product.do业务方法
    public ResponseEntity<CartVo> deleteProduct(Integer userId,String productIds){
        List<String> ids = Arrays.asList(productIds.split(","));
        if(ids.isEmpty()){
           return  ResponseEntity.responesWhenError(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDecs()); 
        }
        cartDao.deleteByUserIdAndProductIds(userId,ids);
        return this.list(userId);
    }
    
    // 单选或者取消单选
    public ResponseEntity<CartVo> selectOrUnselect(Integer userId,Integer productId,Integer checked){
        cartDao.updateCheckedByUserId(userId,productId,checked);
        return this.list(userId);
    }
    
    // 查询购物车中产品的数量
    public ResponseEntity<Integer> getCartProductCount(Integer userId){
        if(userId == null){
            return  ResponseEntity.responesWhenSuccess(0);
        }
        int count = cartDao.getCartProductCount(userId);
        return  ResponseEntity.responesWhenSuccess(count);
    }
    
}
