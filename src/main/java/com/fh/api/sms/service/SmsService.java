package com.fh.api.sms.service;

import com.fh.api.common.ServerResponse;

public interface SmsService {
    ServerResponse sendCode(String phone);
}
