package com.atguigu.msmservice.service;

import java.util.Map;

/**
 * Administrator
 * 2021-8-6
 */
public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}
