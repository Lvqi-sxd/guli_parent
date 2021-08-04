package com.sxd.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Administrator
 * 2021-8-4
 */

@Component
public class ConstanVodUtils implements InitializingBean {
    @Value("${aliyun.vod.file.keyid}")
    private String keyId;

    @Value("${aliyun.vod.file.keysecret}")
    private String keySecret;

    public  static String KEU_ID;
    public  static String KEY_SECRET;
    @Override
    public void afterPropertiesSet() throws Exception {
        KEU_ID=keyId;
        KEY_SECRET=keySecret;
    }
}
