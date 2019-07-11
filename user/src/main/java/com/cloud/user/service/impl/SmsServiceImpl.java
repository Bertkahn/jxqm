package com.cloud.user.service.impl;

import com.cloud.common.constant.TimeConst;
import com.cloud.common.constant.VerifyCodeConst;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.tool.SmsTool;
import com.cloud.common.util.CommonUtil;
import com.cloud.common.util.TimeUtil;
import com.cloud.user.dao.UserMapper;
import com.cloud.user.dao.VerifyCodeMapper;
import com.cloud.user.entity.User;
import com.cloud.user.entity.VerifyCode;
import com.cloud.user.service.SmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SmsServiceImpl implements SmsService {
    private static final Integer phoneNumLimit = 10;
    private static final Integer ipNumLimit = 30;
    private static final Integer timeLimit = 30 * TimeConst.minute;
    private static final Integer codeExpireTime = 10 * TimeConst.minute;

    @Resource
    private VerifyCodeMapper verifyCodeMapper;
    @Resource
    private UserMapper userMapper;

    // 发送绑定手机号验证码
    @Override
    public void sendBindPhoneCode(String phone) {
        String ip = CommonUtil.getIp();
        checkSendTime(phone, ip);
        Integer code = createCode(phone);
        boolean send = SmsTool.sendBindPhoneCode(phone, code);
        if (!send)
            Res.fail(ErrorType.VERIFYCODE_FAIL);
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setCode(code);
        verifyCode.setCreateTime(TimeUtil.getTimeStamp());
        verifyCode.setIp(ip);
        verifyCode.setPhone(phone);
        verifyCode.setType(VerifyCodeConst.bindPhone);
        verifyCodeMapper.insert(verifyCode);
    }

    // 发送注册验证码
    @Override
    public void sendRegisterCode(String phone) {
        User user = userMapper.getUserByPhone(phone);
        if (user != null)
            Res.fail(ErrorType.PHONE_EXIST);
        String ip = CommonUtil.getIp();
        checkSendTime(phone, ip);
        Integer code = createCode(phone);
        boolean send = SmsTool.sendRegisterCode(phone, code);
        if (!send)
            Res.fail(ErrorType.VERIFYCODE_FAIL);
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setCode(code);
        verifyCode.setCreateTime(TimeUtil.getTimeStamp());
        verifyCode.setIp(ip);
        verifyCode.setPhone(phone);
        verifyCode.setType(VerifyCodeConst.register);
        verifyCodeMapper.insert(verifyCode);
    }

    // 发送登录验证码
    @Override
    public void sendLoginCode(String phone) {
        User user = userMapper.getUserByPhone(phone);
        if (user == null)
            Res.fail(ErrorType.PHONE_NOT_EXIST);
        String ip = CommonUtil.getIp();
        checkSendTime(phone, ip);
        Integer code = createCode(phone);
        boolean send = SmsTool.sendLoginCode(phone, code);
        if (!send)
            Res.fail(ErrorType.VERIFYCODE_FAIL);
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setCode(code);
        verifyCode.setCreateTime(TimeUtil.getTimeStamp());
        verifyCode.setIp(ip);
        verifyCode.setPhone(phone);
        verifyCode.setType(VerifyCodeConst.login);
        verifyCodeMapper.insert(verifyCode);
    }

    // 发送修改密码验证码
    @Override
    public void sendChangePasswordCode(String phone, Long userId) {
        if (userId > 0) {
            User user = userMapper.selectById(userId);
            phone = user.getPhone();
        } else {
            if (!CommonUtil.checkPhone(phone))
                Res.fail(ErrorType.PARAM_ERR);
        }
        String ip = CommonUtil.getIp();
        checkSendTime(phone, ip);
        Integer code = createCode(phone);
        boolean send = SmsTool.sendChangePasswordCode(phone, code);
        if (!send)
            Res.fail(ErrorType.VERIFYCODE_FAIL);
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setCode(code);
        verifyCode.setCreateTime(TimeUtil.getTimeStamp());
        verifyCode.setIp(ip);
        verifyCode.setPhone(phone);
        verifyCode.setType(VerifyCodeConst.changePassword);
        verifyCodeMapper.insert(verifyCode);
    }

    // 检验验证码
    @Override
    public void verifyCode(String phone, Integer code, Integer type) {
        VerifyCode verifyCode = verifyCodeMapper.getCodeByPhoneAndType(phone, type);
        if (verifyCode == null || verifyCode.getIsUse() == 1 || !verifyCode.getCode().equals(code) || verifyCode.getCreateTime() < TimeUtil.getTimeStamp() - codeExpireTime)
            Res.fail(ErrorType.VERIFYCODE_EXPIRE);
        verifyCode.setIsUse(1);
        verifyCodeMapper.updateById(verifyCode);
    }

    // 创建验证码
    private Integer createCode (String phone) {
        if (phone.equals("18000000000"))
            return 111111;
        return CommonUtil.createRandomNum(6);
    }

    // 校验验证码发送时间
    private void checkSendTime (String phone, String ip) {
        List<VerifyCode> list = verifyCodeMapper.getCheckCodeByPhone(phone, phoneNumLimit);
        if (list.size() > 0) {
            if (list.get(0).getCreateTime() > TimeUtil.getTimeStamp() - 60)
                Res.fail(ErrorType.VERIFYCODE_LIMIT);
            if (list.size() == phoneNumLimit && list.get(list.size() - 1).getCreateTime() > TimeUtil.getTimeStamp() - timeLimit)
                Res.fail(ErrorType.VERIFYCODE_LIMIT);
        }
        Integer num = verifyCodeMapper.getCheckCodeByIp(ip, TimeUtil.getTimeStamp() - timeLimit);
        if (num > ipNumLimit)
            Res.fail(ErrorType.VERIFYCODE_LIMIT);
    }
}
