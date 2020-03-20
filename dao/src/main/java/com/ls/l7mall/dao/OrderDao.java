package com.ls.l7mall.dao;

import com.ls.l7mall.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author laijs
 * @date 2020-3-20-10:00
 */
@Repository("orderDao")
public interface OrderDao {
    public Order selectOrderByUserIdAndOrderNo(@Param("userId") Integer userId,@Param("orderNo") Long orderNo);
    public Order selectOrderOrderNo(Long orderNo);
    public int updateByPrimaryKeySelective(Order order);
}
