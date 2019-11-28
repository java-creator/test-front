package com.fh.api.utils;

/**
 * 微信支付需要的一些参数 
 * @author chenp 
 * */


 //@Data//此注解代替了set、get方法，如果不想用这个注解也可以自己写set、get方法。
public class WeChatParams {  

public String total_fee;//订单金额【备注：以分为单位】  
public String body;//商品名称  
public String out_trade_no;//商户订单号  
public String attach;//附加参数  
public String memberid;//会员ID  

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }
}
