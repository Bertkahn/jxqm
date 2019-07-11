package com.cloud.user.service;

import java.util.Map;

public interface SmsService {
    // 发送绑定手机号验证码
    void sendBindPhoneCode (String phone);
    // 发送注册验证码
    void sendRegisterCode (String phone);
    // 发送登录验证码
    void sendLoginCode (String phone);
    // 发送修改密码验证码
    void sendChangePasswordCode (String phone, Long userId);
    // 校验验证码
    void verifyCode (String phone, Integer code, Integer type);
}
