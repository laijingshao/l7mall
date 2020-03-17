package com.ls.l7mall.global;

import com.google.common.collect.Sets;

import java.util.Set;

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
    
    // 产品状态
    public enum ProductStatus{
        ON_SALE(1,"在线");
        private int code;
        private String value;

        ProductStatus(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }
    
    // 分页的升降序
    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
    }
}
