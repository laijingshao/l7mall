package com.ls.l7mall.service;

import com.github.pagehelper.PageInfo;
import com.ls.l7mall.entity.Shipping;
import com.ls.l7mall.global.ResponseEntity;

/**
 * @author laijs
 * @date 2020-3-19-9:24
 */
public interface ShippingService {
    public ResponseEntity addShipping(Integer userId, Shipping shipping);
    public ResponseEntity deleteShipping(Integer userId,Integer shippingId);
    public ResponseEntity updateShipping(Integer userId,Shipping shipping);
    public ResponseEntity selectShipping(Integer userId,Integer shippingId);
    public ResponseEntity<PageInfo> listShipping(Integer userId, Integer pageNum, Integer pageSize);
}
