package com.ls.l7mall.global;

/**
 * @author laijs
 * @date 2020-3-14-19:39
 */
public class Const {
    // 登录时session的key值
    public static final String CURRENT_USER = "currentUser";

    // 校验用户名和email时的type
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";

    // 用户等级
    public interface Role{
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1;//管理员
    }
}
