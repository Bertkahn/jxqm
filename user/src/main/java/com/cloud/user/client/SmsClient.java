package com.cloud.user.client;

import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import com.cloud.user.service.SmsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/client/sms")
public class SmsClient extends ClientController {
    SmsClient() {
        noNeedLogin = new ArrayList<>(Arrays.asList("sendBindPhoneCode"));
    }

    @Resource
    private SmsService smsService;

    // 发送登录验证码
    @RequestMapping("/sendBindPhoneCode")
    public void sendBindPhoneCode () {
        String phone = postString("phone");
        if (!CommonUtil.checkPhone(phone))
            Res.fail(ErrorType.PARAM_ERR);
        smsService.sendBindPhoneCode(phone);
        Res.success();
    }

//    // sun
//    // 发送注册验证码
//    @RequestMapping("/sendRegisterCode")
//    public void sendRegisterCode () {
//        String phone = postString("phone");
//        if (!CommonUtil.checkPhone(phone))
//            Res.fail(ErrorType.PARAM_ERR);
//        smsService.sendRegisterCode(phone);
//        Res.success();
//    }
//
//    // sun
//    // 发送登录验证码
//    @RequestMapping("/sendLoginCode")
//    public void sendLoginCode () {
//        String phone = postString("phone");
//        if (!CommonUtil.checkPhone(phone))
//            Res.fail(ErrorType.PARAM_ERR);
//        smsService.sendLoginCode(phone);
//        Res.success();
//    }
//
//    // sun
//    // 发送修改密码验证码
//    @RequestMapping("/sendChangePasswordCode")
//    public void sendChangePasswordCode () {
//        String phone = postString("phone");
//        smsService.sendChangePasswordCode(phone, getUserId());
//        Res.success();
//    }
}
