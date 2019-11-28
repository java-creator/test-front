package com.fh.api.cart.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Integer id;
    private String totalPrice;
    private Integer totalCount;
    private List<CartInfo> cartInfoList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<CartInfo> getCartInfoList() {
        return cartInfoList;
    }

    public void setCartInfoList(List<CartInfo> cartInfoList) {
        this.cartInfoList = cartInfoList;
    }
}
