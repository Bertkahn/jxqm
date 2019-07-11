package com.cloud.common.tool;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.cloud.common.constant.AliConst;

public class SmsTool {

    private static final String loginCode = "SMS_168670132";
    private static final String registerCode = "SMS_168670130";
    private static final String changePasswordCode = "SMS_168670129";
    private static final String bindPhoneCode = "SMS_168670133";

    // 发送注册验证码
    public static boolean sendRegisterCode (String phone, Integer code) {
        return sendVerifyCode(phone, code, registerCode);
    }

    // 发送登录验证码
    public static boolean sendLoginCode (String phone, Integer code) {
        return sendVerifyCode(phone, code, loginCode);
    }

    // 发送修改密码验证码
    public static boolean sendChangePasswordCode (String phone, Integer code) {
        return sendVerifyCode(phone, code, changePasswordCode);
    }

    // 发送修改密码验证码
    public static boolean sendBindPhoneCode (String phone, Integer code) {
        return sendVerifyCode(phone, code, bindPhoneCode);
    }

    // 发送验证码
    private static boolean sendVerifyCode (String phone, Integer code, String smsCode) {
        final String product = "Dysmsapi";
        final String domain = "dysmsapi.aliyuncs.com";
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", AliConst.smsAppId, AliConst.smsSecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            return false;
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setMethod(MethodType.POST);
        request.setPhoneNumbers(phone);
        request.setSignName("愫美科技");
        request.setTemplateCode(smsCode);
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        SendSmsResponse sendSmsResponse;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
        return sendSmsResponse.getCode().equals("OK");
    }
}
