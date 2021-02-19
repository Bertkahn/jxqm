package com.cloud.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.constant.DeviceConst;
import com.cloud.common.constant.RedisConst;
import com.cloud.common.constant.TimeConst;
import com.cloud.common.constant.VerifyCodeConst;
import com.cloud.common.dto.TableDto;
import com.cloud.common.dto.UserAuthDto;
import com.cloud.common.redis.Redis;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import com.cloud.common.util.AliUtil;
import com.cloud.common.util.TimeUtil;
import com.cloud.user.dao.*;
import com.cloud.user.entity.*;
import com.cloud.user.service.SmsService;
import com.cloud.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
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
    public Map completeUserInfo(Long userId, String trueName, Integer birth, Integer sex, Integer cityId, String city) {
        User user = userMapper.selectById(userId);
        if (user.getIsVerify() == 1)
            return parseReturnUser(user);
        user.setTrueName(trueName);
        user.setBirthDay(birth % 10000);
        user.setBirthYear(birth / 10000);
        user.setAge(Integer.parseInt(TimeUtil.formatTime("Y")) - birth / 10000);
        user.setSex(sex);
        user.setCity(city);
        user.setCityId(cityId);
        user.setIsVerify(1);
        userMapper.updateById(user);
        return parseReturnUser(user);
    }

    @Override
    public Map loginByUnionId(String unionId) {
        UserLogin userLogin = userLoginMapper.getUserLoginByUnionId(unionId);
        if (userLogin == null) {
            Res.fail(ErrorType.USER_NOT_EXIST);
            return null;
        }
        User user = userMapper.selectById(userLogin.getUserId());
        return parseReturnUser(user, userLogin);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public Map wxMinBindPhone(String phone, Integer code, String unionId, String minOpenId, String nickName, String headPic,
                              Integer sex, Long activityId, Long friendId, Long saleId) {
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
                // todo
                Long instId = 0L;
                UserFrom userFrom = new UserFrom();
                userFrom.setUserId(user.getId());
                userFrom.setDevice(DeviceConst.wxMin);
                userFrom.setFriendId(friendId);
                userFrom.setSaleId(saleId);
                if (CommonUtil.isNotEmpty(activityId)) {
                    userFrom.setFromType(1);// 活动来的
                    userFrom.setFromId(activityId);
                }
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
            if (CommonUtil.isNotEmpty(userLogin.getPhone()) && !userLogin.getPhone().equals(phone)) {
                // 已绑定手机且不是该手机号
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
        return parseReturnUser(user, userLogin);
    }

    private Map parseReturnUser(User user) {
        UserLogin userLogin = userLoginMapper.selectById(user.getId());
        return parseReturnUser(user, userLogin);
    }

    private Map parseReturnUser(User user, UserLogin userLogin) {
        // redis
        UserAuthDto userAuthDto = new UserAuthDto();
        userAuthDto.setUserId(user.getId());
        redis.set(RedisConst.userToken + userLogin.getToken(), userAuthDto, expire);
        // return
        Map<String, Object> result = new HashMap<>();
        result.put("userId", user.getId());
        result.put("saleId", user.getSaleId());
        result.put("phone", user.getPhone());
        result.put("nickName", user.getNickName());
        result.put("trueName", user.getTrueName());
        result.put("isVerify", user.getIsVerify());
        result.put("birth", CommonUtil.isNotEmpty(user.getBirthDay()) ? user.getBirthYear() * 10000 + user.getBirthDay() : 0);
        result.put("headPic", AliUtil.parseOssImg(user.getHeadPic()));
        result.put("sex", user.getSex());
        result.put("city", user.getCity());
        result.put("cityId", user.getCityId());
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
    private Long createUserId() {
        Long lastUserId = redis.get(RedisConst.lastUserIdKey, Long.class);
        if (CommonUtil.isEmpty(lastUserId))
            lastUserId = userMapper.getLastUserId();
        if (CommonUtil.isEmpty(lastUserId))
            lastUserId = 100000L;
        Long userId = (lastUserId / 100 + 1) * 100 + CommonUtil.createRandomNum(2);
        redis.set(RedisConst.lastUserIdKey, userId, TimeConst.day);
        return userId;
    }

    @Override
    public User getUserByUserId(Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            Res.fail(ErrorType.UNSAFE_USER_NOT_EXIST);
        }
        return user;
    }

    @Override
    public List<User> getUserListByUserIdList(List<Long> userIdList) {
        return userMapper.getUserListByUserIdList(userIdList);
    }

    @Override
    public Page getMyCustomerPage(Long saleId, TableDto tableDto) {
        Page<User> page = new Page<>(tableDto.getCurrent(), tableDto.getSize());
        page.setRecords(userMapper.getMyCustomerList(saleId, tableDto, page));
        return page;
    }

    @Override
    public void addMyUser(Long saleId, String trueName, String phone, String nickName, Integer age, Integer sex) {
        User u = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
        if (u != null) {
            Res.fail(ErrorType.PHONE_EXIST);
        }
        User user = new User();
        user.setSaleId(saleId);
        user.setTrueName(trueName);
        user.setPhone(phone);
        user.setNickName(nickName);
        user.setAge(age);
        user.setSex(sex);
        int insert = userMapper.insert(user);
        if (insert != 1) {
            Res.fail(ErrorType.OTHER_ERR);
        }
    }

    @Override
    public void delMyUser(Long id) {
        if (userMapper.deleteById(id) != 1) {
            Res.fail("删除失败！");
        }
    }

    @Override
    public void updateMyUser(Long id, String trueName, String phone, String nickName, Integer age, Integer sex) {
        User user = userMapper.selectById(id);
        user.setTrueName(trueName);
        user.setPhone(phone);
        user.setNickName(nickName);
        user.setAge(age);
        user.setSex(sex);
        if (userMapper.updateById(user) != 1) {
            Res.fail("更新失败！");
        }
    }

// 注册
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
