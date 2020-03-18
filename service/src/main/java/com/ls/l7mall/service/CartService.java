package com.ls.l7mall.service;

import com.ls.l7mall.global.ResponseEntity;
import com.ls.l7mall.vo.CartVo;

/**
 * @author laijs
 * @date 2020-3-18-11:33
 */
public interface CartService {

    public ResponseEntity<CartVo> list(Integer userId);
    public ResponseEntity<CartVo> add(Integer userId,Integer productId,Integer count);
}
