package com.fh.api.sms.controller;

import com.fh.api.common.ServerResponse;
import com.fh.api.sms.service.SmsService;
import com.fh.api.util.SendMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sms/")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @RequestMapping("sendMsg")
    public ServerResponse sendMsg(String phone){
        return smsService.sendCode(phone);
    }
}
