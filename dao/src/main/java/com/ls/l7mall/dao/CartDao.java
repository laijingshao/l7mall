package com.ls.l7mall.dao;

import com.ls.l7mall.entity.Cart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author laijs
 * @date 2020-3-18-11:18
 */
@Repository("cartDao")
public interface CartDao {
    public List<Cart> selectCartByUserId(Integer userId);
    public int updateByPrimaryKeySelective(Cart cart);
    public int getAllCheckedByUserId(Integer userId);
    public Cart selectByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);
    public int insert(Cart cart);
}
