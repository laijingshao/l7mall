package com.ls.l7mall.dao;

import com.ls.l7mall.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author laijs
 * @date 2020-3-14-19:37
 */
@Repository("categoryDao")
public interface CategoryDao {
    // 添加分类
    public int insertCategory(Category category);

    // 根据id更新分类（忽略空字段）
    public int updateCategoryById(Category category);

    // 根据id查找子节点
    public List<Category> selectChildrenCategoryById(Integer parentId);

    // 根据id查找分类
    public Category selectById(Integer id);

}
