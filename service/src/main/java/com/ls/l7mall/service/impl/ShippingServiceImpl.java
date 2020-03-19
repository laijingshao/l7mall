package com.ls.l7mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.ls.l7mall.dao.ShippingDao;
import com.ls.l7mall.entity.Shipping;
import com.ls.l7mall.global.ResponseCode;
import com.ls.l7mall.global.ResponseEntity;
import com.ls.l7mall.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author laijs
 * @date 2020-3-19-9:24
 */
@Service("shippingService")
public class ShippingServiceImpl implements ShippingService {
    
    @Autowired
    @Qualifier("shippingDao")
    private ShippingDao shippingDao;

    // add.do业务代码
    public ResponseEntity addShipping(Integer userId,Shipping shipping){
        shipping.setUserId(userId);
        int count = shippingDao.insert(shipping);
        if(count > 0){
            HashMap map = Maps.newHashMap();
            map.put("shippingId",shipping.getId());
            return ResponseEntity.responesWhenSuccess("新建地址成功",map);
        }
        return ResponseEntity.responesWhenError("新建地址失败");
    }
    
    // delete.do业务代码
    public ResponseEntity deleteShipping(Integer userId,Integer shippingId){
        if(shippingId == null){
            return ResponseEntity.responesWhenError(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDecs());
        }
        int count = shippingDao.deleteByIdAndUserId(userId, shippingId);
        if(count > 0){
            return ResponseEntity.responesWhenSuccess("删除地址成功");
        }
        return ResponseEntity.responesWhenError("删除地址失败");

    }

    // update.do业务代码
    public ResponseEntity updateShipping(Integer userId,Shipping shipping){
        shipping.setUserId(userId);
        int count = shippingDao.updateByShipping(shipping);
        if(count > 0){
            return ResponseEntity.responesWhenSuccess("更新地址成功");
        }
        return ResponseEntity.responesWhenError("更新地址失败");
    }
    
    // select.do业务代码
    public ResponseEntity selectShipping(Integer userId,Integer shippingId){
        Shipping shipping = shippingDao.selectByIdAndUserId(userId, shippingId);
        if(shipping == null){
            return ResponseEntity.responesWhenError("获取地址详情失败");
        }
        return ResponseEntity.responesWhenSuccess("获取地址详情成功",shipping);
    }
    
    // list.do业务代码
    public ResponseEntity<PageInfo> listShipping(Integer userId,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Shipping> shippings = shippingDao.selectById(userId);
        PageInfo pageInfo = new PageInfo(shippings);
        return ResponseEntity.responesWhenSuccess("获取地址详情成功",pageInfo);
    }
    
    
    
}
