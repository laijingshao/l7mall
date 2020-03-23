package com.ls.l7mall.dao;

import com.ls.l7mall.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author laijs
 * @date 2020-3-20-14:42
 */
public interface OrderItemDao {
    public List<OrderItem> selectByUserIdAndOrderNo(@Param("userId") Integer userId, @Param("orderNo") Long orderNo);
    public void batchInsert(@Param("orderItems") List<OrderItem> orderItems);
    public List<OrderItem> selectByOrderNo( @Param("orderNo") Long orderNo);
}
