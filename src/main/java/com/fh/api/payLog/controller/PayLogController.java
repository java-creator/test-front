package com.fh.api.payLog.controller;

import com.fh.api.payLog.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pay")
public class PayLogController {
    @Autowired
    private PayLogService payLogService;



}
