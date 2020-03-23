package com.ls.l7mall.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author laijs
 * @date 2020-3-23-14:21
 */
public class OrderProductVo {
    private List<OrderItemVo> orderItemVoList;
    private BigDecimal productTotalPrice;
    private String imageHost;

    public OrderProductVo(List<OrderItemVo> orderItemVoList, BigDecimal productTotalPrice, String imageHost) {
        this.orderItemVoList = orderItemVoList;
        this.productTotalPrice = productTotalPrice;
        this.imageHost = imageHost;
    }

    public List<OrderItemVo> getOrderItemVoList() {
        return orderItemVoList;
    }

    public void setOrderItemVoList(List<OrderItemVo> orderItemVoList) {
        this.orderItemVoList = orderItemVoList;
    }

    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(BigDecimal productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }
}
