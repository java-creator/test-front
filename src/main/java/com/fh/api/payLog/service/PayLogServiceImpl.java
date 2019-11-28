package com.fh.api.payLog.service;

import com.fh.api.payLog.mapper.PayLogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PayLogServiceImpl implements PayLogService {
    @Resource
    private PayLogMapper payLogMapper;
}
