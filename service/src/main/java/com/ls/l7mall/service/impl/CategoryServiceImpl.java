package com.ls.l7mall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ls.l7mall.dao.CategoryDao;
import com.ls.l7mall.entity.Category;
import com.ls.l7mall.global.ResponseEntity;
import com.ls.l7mall.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author laijs
 * @date 2020-3-14-19:57
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    @Qualifier("categoryDao")
    private CategoryDao categoryDao;

    @Override
    public ResponseEntity addCategory(String categoryName, Integer parentId) {
        if(categoryName.isBlank() || parentId == null){
            return ResponseEntity.responesWhenError("添加分类参数错误");
        }
        Category insertCategory = new Category();
        insertCategory.setName(categoryName);
        insertCategory.setParentId(parentId);
        insertCategory.setStatus(true);
        int resultCount = categoryDao.insertCategory(insertCategory);
        if(resultCount > 0){
            return ResponseEntity.responesWhenSuccess("添加分类成功");
        }
        return ResponseEntity.responesWhenError("添加分类失败");
    }

    public ResponseEntity setCategoryName(Integer categoryId,String categoryName){
        if(categoryId == null || categoryName.isBlank()){
            return ResponseEntity.responesWhenError("更新分类参数错误");
        }
        Category updateCategory = new Category();
        updateCategory.setName(categoryName);
        updateCategory.setId(categoryId);
        int resultCount = categoryDao.updateCategoryById(updateCategory);
        if(resultCount > 0 ){
            return ResponseEntity.responesWhenSuccess("更新分类名称成功");
        }
        return ResponseEntity.responesWhenError("更新分类名称失败");
    }

    public ResponseEntity<List<Category>> getChildrenCategoryById(Integer categoryId){
        List<Category> categories = categoryDao.selectChildrenCategoryById(categoryId);
        if(categories.isEmpty()){
            // 以日志形式记录
            logger.info("未找到当前分类的子分类");
        }
        return ResponseEntity.responesWhenSuccess(categories);
    }

    public ResponseEntity<List<Integer>> getDeepChildrenCategoryById(Integer categoryId){
        // 创建一个set集合用于储存遍历的category对象
        Set<Category> categorySet = Sets.newHashSet();
        // 当category类的id一致时，就认为其是同一个的category对象，因此要重写category类的equals和hashCode方法

        // 创建一个递归方法将所有category对象存于set集合，在此调用
        findChildCategory(categorySet,categoryId);

        // 创建list集合保存set集合中所有category对象的id值
        List<Integer> categoryIdList = Lists.newArrayList();
        if(categoryId != null){
            for(Category categoryItem : categorySet){
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ResponseEntity.responesWhenSuccess(categoryIdList);
    }

    // 递归算法
    private Set<Category> findChildCategory(Set<Category> categorySet,Integer categoryId){
        // 判断当前id表示的节点
        Category category = categoryDao.selectById(categoryId);
        if(category != null){
            // 加入set集合中
            categorySet.add(category);
        }
        // 获取当前节点的子节点
        List<Category> categories = categoryDao.selectChildrenCategoryById(categoryId);
        // 遍历集合，集合中的每个category都进行再遍历
        for (Category categoryItem : categories) {
            findChildCategory(categorySet,categoryItem.getId());
        }
        // 最终的set已经保存了每个category对象
        return categorySet;
    }

}
