package com.ls.l7mall.service;

import com.ls.l7mall.global.ResponseEntity;

import java.util.Map;

/**
 * @author laijs
 * @date 2020-3-20-10:07
 */
public interface OrderService {
    public ResponseEntity pay(Integer userId, Long orderNo, String path);
    public ResponseEntity aliCallback(Map<String,String> params);
    public ResponseEntity queryOrderPayStatus(Integer userId,Long orderNo);
}
