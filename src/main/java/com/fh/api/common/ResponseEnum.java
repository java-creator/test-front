package com.fh.api.common;

public enum ResponseEnum {
    SUCCESS(200,"操作成功"),
    ERROR(1000,"操作失败"),
    CODE_ERROR(1001,"验证码错误,或超时"),
    PHONE_ERROR(1002,"手机号不存在"),
    PHONE_NULL(1003,"手机号为空"),
    MEMBER_NAME_EXIST(1004,"用户名已存在"),
    PHONE_EXIST(1005,"手机号已被使用"),
    CODE_NULL(1006,"验证码为空"),
    MEMBER_NAME_PASSWORD_NULL(1007,"用户名或密码为空"),
    MEMBER_NULL(1008,"用户不存在"),
    PASSWORD_NOT(1009,"密码错误"),
    NOT_LOGIN(1010,"用户未登陆"),
    PRODUCT_NULL(1011,"商品不存在"),
    PRODUCT_DOWN(1012,"商品已下架"),
    CART_NULL(1013,"购物车为空"),
    ORDER_DETAIL_NULL(1014,"订单为空没必要生成订单"),
    ;
    private int code;
    private String msg;

    ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
