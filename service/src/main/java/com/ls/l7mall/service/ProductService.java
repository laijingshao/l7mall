package com.ls.l7mall.service;

import com.github.pagehelper.PageInfo;
import com.ls.l7mall.entity.Product;
import com.ls.l7mall.global.ResponseEntity;
import com.ls.l7mall.vo.ProductDetailVo;

/**
 * @author laijs
 * @date 2020-3-15-19:49
 */
public interface ProductService {

    // 新增或更新产品
    public ResponseEntity saveOrUpdateProduct(Product product);

    // 更新产品状态
    public ResponseEntity<String> setSaleStatus(Integer productId,Integer status);
    
    // 获取产品详情
    public ResponseEntity<ProductDetailVo> manageProductDetail(Integer productId);
    
    // 获取产品列表
    public ResponseEntity<PageInfo> getProductList(Integer pageNum, Integer pageSize);

    // 搜索产品
    public ResponseEntity<PageInfo> searchProductList(String productName,Integer productId,Integer pageNum,Integer pageSize);
    
    // 前台获取产品详情
    public ResponseEntity<ProductDetailVo> getProductDetail(Integer productId);
    
    
}
