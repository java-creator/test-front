package com.fh.api.sms.service;

import com.fh.api.common.ResponseEnum;
import com.fh.api.common.ServerResponse;
import com.fh.api.util.RedisUtil;
import com.fh.api.util.SendMsgUtil;
import com.fh.api.util.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public ServerResponse sendCode(String phone) {
        if(StringUtils.isNotBlank(phone)){
            String code = SendMsgUtil.phoneCode(phone);
            if(StringUtils.isEmpty(code)){
                return ServerResponse.error(ResponseEnum.PHONE_ERROR);
            }else {
                RedisUtil.set(phone,code);
                RedisUtil.expire(phone, SystemConstant.CODE_OUT_TIME);
                return ServerResponse.success();
            }
        }else {
            return ServerResponse.error(ResponseEnum.PHONE_NULL);
        }
    }
}
