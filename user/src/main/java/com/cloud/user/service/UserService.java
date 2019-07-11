package com.cloud.user.service;

import com.cloud.common.dto.UserAuthDto;
import com.cloud.user.entity.User;

import java.util.Map;

public interface UserService {
    // 小程序绑定手机号
    Map wxMinBindPhone (String phone, Integer code, String unionId, String minOpenId, String nickName, String headPic, Integer sex, String inviteCode);
    // system
    UserAuthDto getUserAuthByToken (String token);
    // system
    UserAuthDto getUserAuthByUserId (Long userId);
//    User getUserByUserId (Long userId);
//    // sun
//    // 注册
//    User register (String phone, String password, Integer code);
//    // sun
//    // 登录
//    User login (String phone, String password);
//    // sun
//    // 验证码登录
//    User loginByCode (String phone, Integer code);
//    // sun
//    // 修改密码
//    Map changePassword (Long userId, String phone, String password, Integer code);
//    // sun
//    // 设置性别
//    void setSex(Long userId, Integer sex);
//    // sun
//    // 设置头像
//    String setHeadPic (Long userId, String headPic);
//    // sun
//    // 设置昵称
//    void setNickName (Long userId, String nickName);
//    // sun
//    // 获取用户基本信息
//    User getUserInfo (Long userId);
}
