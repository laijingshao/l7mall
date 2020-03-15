package com.ls.l7mall.dao;

import com.ls.l7mall.entity.Product;
import org.springframework.stereotype.Repository;

/**
 * @author laijs
 * @date 2020-3-15-19:50
 */
@Repository("productDao")
public interface ProductDao {
    public int insert(Product product);
    public int updateById(Product product);
    public Product selectById(Integer id);
}
