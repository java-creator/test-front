package com.fh.WXpay;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyConfig extends WXPayConfig {
    @Override
    public String getAppID() {
        return "wxa1e44e130a9a8eee";
    }

    @Override
    public String getMchID() {
        return "1507758211";
    }

    @Override
    public String getKey() {
        return "feihujiaoyu12345678yuxiaoyang123";
    }

        public InputStream getCertStream() {
            return null;
        }

        public IWXPayDomain getWXPayDomain() {
            return new IWXPayDomain() {
                @Override
                public void report(String domain, long elapsedTimeMillis, Exception ex) {

                }

                @Override
                public DomainInfo getDomain(WXPayConfig config) {
                    return new DomainInfo(WXPayConstants.DOMAIN_API, true);
                }
            };

    }


}
