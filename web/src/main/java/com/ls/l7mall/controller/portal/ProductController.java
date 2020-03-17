package com.ls.l7mall.controller.portal;

import com.ls.l7mall.entity.User;
import com.ls.l7mall.global.Const;
import com.ls.l7mall.global.ResponseEntity;
import com.ls.l7mall.service.ProductService;
import com.ls.l7mall.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author laijs
 * @date 2020-3-17-22:00
 */
@Controller
@RequestMapping("/product/")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @RequestMapping("detail.do")
    @ResponseBody
    public ResponseEntity<ProductDetailVo> getDetail(Integer productId){
         return productService.getProductDetail(productId);
    }

}
