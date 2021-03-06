package com.ls.l7mall.dao;

import com.ls.l7mall.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author laijs
 * @date 2020-3-15-19:50
 */
@Repository("productDao")
public interface ProductDao {
    public int insert(Product product);
    public int updateById(Product product);
    public Product selectById(Integer productId);
    public List<Product> selectAll();
    public List<Product> selectByIdAndName(@Param("productName") String productName, @Param("productId") Integer productId);
    public List<Product> selectByNameAndCategoryIds(@Param("productName") String productName, @Param("categoryIdList")List<Integer> categoryIdList);
}
