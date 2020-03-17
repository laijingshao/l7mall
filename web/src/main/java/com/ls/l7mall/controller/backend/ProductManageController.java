package com.ls.l7mall.controller.backend;

import com.ls.l7mall.entity.Product;
import com.ls.l7mall.entity.User;
import com.ls.l7mall.global.Const;
import com.ls.l7mall.global.ResponseEntity;
import com.ls.l7mall.service.CategoryService;
import com.ls.l7mall.service.ProductService;
import com.ls.l7mall.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author laijs
 * @date 2020-3-15-19:48
 */
@Controller
@RequestMapping("/manage/product")
public class ProductManageController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;
    
    // 添加OR更新产品
    @RequestMapping("save.do")
    @ResponseBody
    public ResponseEntity saveOrUpdateProduct(HttpSession session, Product product){
        // 判断是否已经登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ResponseEntity.responesWhenError("尚未登录，请登录");
        }
        // 判断是否为管理员
        if(user.getRole() == Const.Role.ROLE_ADMIN){
            // 保存产品信息
            return productService.saveOrUpdateProduct(product);
        }
        return ResponseEntity.responesWhenError("需要管理员权限");
    }
    
    // 修改产品状态
    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ResponseEntity<String> setSaleStatus(HttpSession session, Integer productId,Integer status){
        // 判断是否已经登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ResponseEntity.responesWhenError("尚未登录，请登录");
        }
        // 判断是否为管理员
        if(user.getRole() == Const.Role.ROLE_ADMIN){
            // 保存产品信息
            return productService.setSaleStatus(productId,status);
        }
        return ResponseEntity.responesWhenError("需要管理员权限");
    
    }
    
    // 获取产品详细信息
    @RequestMapping("detail.do")
    @ResponseBody
    public ResponseEntity<ProductDetailVo> getDetail(HttpSession session, Integer id){
        // 判断是否已经登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ResponseEntity.responesWhenError("尚未登录，请登录");
        }
        // 判断是否为管理员
        if(user.getRole() == Const.Role.ROLE_ADMIN){
            // 保存产品信息
            return productService.manageProductDetail(id);
        }
        return ResponseEntity.responesWhenError("需要管理员权限");
    }
    
    
    
    
    
}
