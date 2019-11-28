package com.fh.api.util;

public class Code {
    private Integer code;
    private Integer msg;
    private String obj;

    public Code(Integer code, Integer msg, String obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public Code() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getMsg() {
        return msg;
    }

    public void setMsg(Integer msg) {
        this.msg = msg;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }
}
