package com.cloud.user.service.impl;

import com.cloud.common.constant.DeviceConst;
import com.cloud.common.constant.RedisConst;
import com.cloud.common.constant.TimeConst;
import com.cloud.common.constant.VerifyCodeConst;
import com.cloud.common.dto.UserAuthDto;
import com.cloud.common.redis.Redis;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import com.cloud.common.util.GlobUtil;
import com.cloud.common.util.TimeUtil;
import com.cloud.user.dao.*;
import com.cloud.user.entity.*;
import com.cloud.user.service.SmsService;
import com.cloud.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private Redis redis;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserLoginMapper userLoginMapper;
    @Resource
    private UserFromMapper userFromMapper;
    @Resource
    private UserBelongMapper userBelongMapper;
    @Resource
    private UserIncreaseMapper userIncreaseMapper;
    @Resource
    private SmsService smsService;

    private final String pwdPre = "saf#dqw!dDQqw2";
    private final String pwdAfter = "Dydw%dw12j@";
    private final Integer expire = TimeConst.hour;

    @Override
    @SuppressWarnings("unchecked")
    public Map wxMinBindPhone(String phone, Integer code, String unionId, String minOpenId, String nickName, String headPic, Integer sex, String inviteCode) {
        // 验证码
        Integer time = TimeUtil.getTimeStamp();
        smsService.verifyCode(phone, code, VerifyCodeConst.bindPhone);
        UserLogin userLogin = userLoginMapper.getUserLoginByUnionId(unionId);
        User user;
        if (userLogin == null) {
            // 没有绑过微信
            userLogin = userLoginMapper.getUserLoginByPhone(phone);
            if (userLogin == null) {
                // 都没有绑过
                // 注册
                user = new User();
                user.setId(createUserId());
                user.setSex(sex);
                user.setPhone(phone);
                user.setNickName(nickName);
                user.setCreateTime(time);
                user.setHeadPic(headPic);
                userMapper.insert(user);
                // 鉴权信息
                userLogin = new UserLogin();
                userLogin.setUserId(user.getId());
                userLogin.setUnionId(unionId);
                userLogin.setPhone(phone);
                userLogin.setMinOpenId(minOpenId);
                userLogin.setToken(CommonUtil.createToken());
                userLoginMapper.insert(userLogin);
                // from
                Long friendId = 0L;
                Long instId = 0L;
                Long saleId = 0L;
                Map<String, Object> fromInfo = redis.get(RedisConst.inviteInfoKey, Map.class);
                if (CommonUtil.isNotEmpty(fromInfo)) {
                    friendId = Long.parseLong(fromInfo.get("friendId").toString());
                    instId = Long.parseLong(fromInfo.get("instId").toString());
                    saleId = Long.parseLong(fromInfo.get("saleId").toString());
                }
                UserFrom userFrom = new UserFrom();
                userFrom.setUserId(user.getId());
                userFrom.setDevice(DeviceConst.wxMin);
                userFrom.setFriendId(friendId);
                userFrom.setInstId(instId);
                userFrom.setSaleId(saleId);
                userFromMapper.insert(userFrom);
                // belong
                UserBelong userBelong = new UserBelong();
                userBelong.setCreateTime(time);
                userBelong.setUserId(user.getId());
                userBelong.setInstId(instId);
                userBelong.setSaleId(saleId);
                userBelongMapper.insert(userBelong);
                // increase 不准确
                Integer day = Integer.parseInt(TimeUtil.formatTime("YMD"));
                UserIncrease userIncrease = userIncreaseMapper.selectById(day);
                if (userIncrease == null) {
                    userIncrease = new UserIncrease();
                    userIncrease.setNum(1);
                    userIncrease.setDayTime(day);
                    userIncreaseMapper.insert(userIncrease);
                } else {
                    userIncrease.setNum(userIncrease.getNum() + 1);
                    userIncreaseMapper.updateById(userIncrease);
                }
            } else {
                if (CommonUtil.isNotEmpty(userLogin.getUnionId())) {
                    // 微信已绑定
                    Res.fail(ErrorType.WX_BIND_ERR);
                }
                // 手机已绑定，微信未绑定
                // 绑定unionId和openId
                userLogin.setMinOpenId(minOpenId);
                userLogin.setUnionId(unionId);
                userLoginMapper.updateById(userLogin);
                user = userMapper.selectById(userLogin.getUserId());
            }
        } else {
            if (CommonUtil.isNotEmpty(userLogin.getPhone())) {
                // 已绑定手机
                Res.fail(ErrorType.PHONE_BIND_ERR);
            }
            // 该微信号未绑定手机号
            // 判断手机是否已被注册
            user = userMapper.getUserByPhone(phone);
            if (user != null)
                // 手机已被注册
                Res.fail(ErrorType.PHONE_EXIST);
            // 未被注册，绑定手机号
            userLogin.setPhone(phone);
            userLoginMapper.updateById(userLogin);
            user = userMapper.selectById(userLogin.getUserId());
        }
        // redis
        UserAuthDto userAuthDto = new UserAuthDto();
        userAuthDto.setUserId(user.getId());
        redis.set(RedisConst.userToken + userLogin.getToken(), userAuthDto, expire);
        return parseReturnUser(user, userLogin);
    }

    private Map parseReturnUser (User user, UserLogin userLogin) {
        Map<String, Object> result = new HashMap<>();
        result.put("userId", user.getId());
        result.put("phone", user.getPhone());
        result.put("nickName", user.getNickName());
        result.put("headPic", GlobUtil.parseOssImg(user.getHeadPic()));
        result.put("sex", user.getSex());
        result.put("token", userLogin.getToken());
        result.put("unionId", userLogin.getUnionId());
        return result;
    }

    @Override
    public UserAuthDto getUserAuthByToken(String token) {
        UserAuthDto userAuthDto;
        String key = RedisConst.userToken + token;
        userAuthDto = redis.get(key, UserAuthDto.class);
        if (userAuthDto != null) {
            redis.setExpire(key, expire);
            return userAuthDto;
        }
        UserLogin userLogin = userLoginMapper.getUserLoginByToken(token);
        if (userLogin == null) {
            Res.fail(ErrorType.UNSAFE_USER_NOT_EXIST);
        }
        userAuthDto = new UserAuthDto();
        userAuthDto.setUserId(userLogin.getUserId());
        redis.set(key, userAuthDto, expire);
        return userAuthDto;
    }

    @Override
    public UserAuthDto getUserAuthByUserId(Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            Res.fail(ErrorType.UNSAFE_USER_NOT_EXIST);
        }
        UserAuthDto userAuthDto = new UserAuthDto();
        userAuthDto.setUserId(user.getId());
        return userAuthDto;
    }

    // 创建userId
    private Long createUserId () {
        Long lastUserId = redis.get(RedisConst.lastUserId, Long.class);
        if (CommonUtil.isEmpty(lastUserId))
            lastUserId = userMapper.getLastUserId();
        if (CommonUtil.isEmpty(lastUserId))
            lastUserId = 100000L;
        Long userId = (lastUserId / 100 + 1) * 100 + CommonUtil.createRandomNum(2);
        redis.set(RedisConst.lastUserId, userId, TimeConst.day);
        return userId;
    }

//    @Override
//    public User getUserByUserId(Long userId) {
//        User user = userMapper.selectById(userId);
//        if (user == null) {
//            Res.fail(ErrorType.UNSAFE_USER_NOT_EXIST);
//        }
//        return user;
//    }
//
//    // sun
//    // 注册
//    @Override
//    @Transactional
//    public User register(String phone, String password, Integer code) {
//        // 验证码
//        smsService.verifyCode(phone, code, VerifyCodeConst.register);
//        // 用户
//        User user = userMapper.getUserByPhone(phone);
//        if (user != null)
//            Res.fail(ErrorType.PHONE_EXIST);
//        user = new User();
//        user.setNickName(CommonUtil.createRandomNum(8) + "");
//        user.setPhone(phone);
//        user.setSex(1);
//        user.setPassword(CommonUtil.encryptPassword(password, pwdPre, pwdAfter));
//        user.setToken(CommonUtil.createToken());
//        user.setCreateTime(TimeUtil.getTimeStamp());
//        userMapper.insert(user);
//        UserAuthDto userAuthDto = new UserAuthDto();
//        userAuthDto.setUserId(user.getId());
//        redis.set(RedisConst.userToken + user.getToken(), userAuthDto, expire);
//        // todo 登录日志
//        user.setPassword(null);
//        user.setHeadPic(GlobUtil.parseOssImg(user.getHeadPic()));
//        return user;
//    }
//
//    // sun
//    // 登录
//    @Override
//    public User login(String phone, String password) {
//        User user = userMapper.getUserByPhone(phone);
//        if (user == null)
//            Res.fail(ErrorType.PHONE_NOT_EXIST);
//        if (!CommonUtil.encryptPassword(password, pwdPre, pwdAfter).equals(user.getPassword()))
//            Res.fail(ErrorType.PASSWORD_ERR);
//        redis.delete(RedisConst.userToken + user.getToken());
//        user.setToken(CommonUtil.createToken());
//        userMapper.updateById(user);
//        UserAuthDto userAuthDto = new UserAuthDto();
//        userAuthDto.setUserId(user.getId());
//        redis.set(RedisConst.userToken + user.getToken(), userAuthDto, expire);
//        // todo 登录日志
//        user.setPassword(null);
//        user.setHeadPic(GlobUtil.parseOssImg(user.getHeadPic()));
//        return user;
//    }
//
//    // sun
//    // 验证码登录
//    @Override
//    public User loginByCode(String phone, Integer code) {
//        // 验证码
//        smsService.verifyCode(phone, code, VerifyCodeConst.login);
//        //
//        User user = userMapper.getUserByPhone(phone);
//        if (user == null)
//            Res.fail(ErrorType.PHONE_NOT_EXIST);
//        redis.delete(RedisConst.userToken + user.getToken());
//        user.setToken(CommonUtil.createToken());
//        userMapper.updateById(user);
//        UserAuthDto userAuthDto = new UserAuthDto();
//        userAuthDto.setUserId(user.getId());
//        redis.set(RedisConst.userToken + user.getToken(), userAuthDto, expire);
//        // todo 登录日志
//        user.setPassword(null);
//        user.setHeadPic(GlobUtil.parseOssImg(user.getHeadPic()));
//        return user;
//    }
//
//    // sun
//    // 修改密码
//    @Override
//    @Transactional
//    public Map changePassword(Long userId, String phone, String password, Integer code) {
//        User user = null;
//        if (CommonUtil.isNotEmpty(userId)) {
//            user = userMapper.selectById(userId);
//            phone = user.getPhone();
//        }
//        // 验证码
//        smsService.verifyCode(phone, code, VerifyCodeConst.changePassword);
//        // 更新密码
//        if (user == null)
//            user = userMapper.getUserByPhone(phone);
//        redis.delete(RedisConst.userToken + user.getToken());
//        user.setPassword(CommonUtil.encryptPassword(password, pwdPre, pwdAfter));
//        user.setToken(CommonUtil.createToken());
//        userMapper.updateById(user);
//        UserAuthDto userAuthDto = new UserAuthDto();
//        userAuthDto.setUserId(user.getId());
//        redis.set(RedisConst.userToken + user.getToken(), userAuthDto, expire);
//        Map<String, String> result = new HashMap<>();
//        result.put("token", user.getToken());
//        return result;
//    }
//
//    // sun
//    // 设置性别
//    @Transactional
//    public void setSex(Long userId, Integer sex) {
//        User user = userMapper.selectById(userId);
//        user.setSex(sex);
//        userMapper.updateById(user);
//    }
//
//    // sun
//    // 设置头像
//    @Override
//    @Transactional
//    public String setHeadPic(Long userId, String headPic) {
//        User user = userMapper.selectById(userId);
//        user.setHeadPic(headPic);
//        userMapper.updateById(user);
//        return GlobUtil.parseOssImg(headPic);
//    }
//
//    // sun
//    // 设置昵称
//    @Override
//    @Transactional
//    public void setNickName(Long userId, String nickName) {
//        User user = userMapper.selectById(userId);
//        user.setNickName(nickName);
//        userMapper.updateById(user);
//    }
//
//    // sun
//    // 获取用户基本信息
//    @Override
//    public User getUserInfo(Long userId) {
//        User user = userMapper.selectById(userId);
//        user.setPassword(null);
//        user.setHeadPic(GlobUtil.parseOssImg(user.getHeadPic()));
//        return user;
//    }
}