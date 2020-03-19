package com.ls.l7mall.dao;

import com.ls.l7mall.entity.Shipping;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author laijs
 * @date 2020-3-19-9:17
 */
@Repository("shippingDao")
public interface ShippingDao {
    
    public int insert(Shipping shipping);
    public int deleteByIdAndUserId(@Param("userId") Integer userId,@Param("shippingId") Integer shippingId);
    public int updateByShipping(Shipping shipping);
    public Shipping selectByIdAndUserId(@Param("userId") Integer userId,@Param("shippingId") Integer shippingId);
    public List<Shipping> selectById(Integer userId);
}
