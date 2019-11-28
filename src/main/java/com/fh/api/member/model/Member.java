package com.fh.api.member.model;

import com.baomidou.mybatisplus.annotation.TableField;

public class Member {
    private Integer id;
    private String memberName;
    private String passWord;
    private String phone;
    @TableField(exist = false)
    private String smsCode;

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Member() {
    }

    public Member(Integer id, String memberName, String passWord, String phone) {
        this.id = id;
        this.memberName = memberName;
        this.passWord = passWord;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
