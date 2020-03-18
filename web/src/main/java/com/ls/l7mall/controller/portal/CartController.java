package com.ls.l7mall.controller.portal;

import com.ls.l7mall.entity.User;
import com.ls.l7mall.global.Const;
import com.ls.l7mall.global.ResponseEntity;
import com.ls.l7mall.service.CartService;
import com.ls.l7mall.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author laijs
 * @date 2020-3-18-11:35
 */
@Controller
@RequestMapping("/cart/")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @RequestMapping("list.do")
    @ResponseBody
    public ResponseEntity<CartVo> list(HttpSession session){
        // 判断是否已经登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ResponseEntity.responesWhenError("尚未登录，请登录");
        }
        return cartService.list(user.getId());
    }

    @RequestMapping("add.do")
    @ResponseBody
    public ResponseEntity<CartVo> add(HttpSession session,Integer productId,Integer count){
        // 判断是否已经登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ResponseEntity.responesWhenError("尚未登录，请登录");
        }
        return cartService.add(user.getId(),productId,count);
    }
    
    
    
    
    
}
