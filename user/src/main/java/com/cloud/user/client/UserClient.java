package com.cloud.user.client;

import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import com.cloud.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("/client/user")
public class UserClient extends ClientController {
    UserClient() {
        noNeedLogin = new ArrayList<>(Arrays.asList("wxMinBindPhone", "loginByUnionId"));
    }

    @Resource
    private UserService userService;

    // 实名信息
    @RequestMapping("/completeUserInfo")
    public void completeUserInfo () {
        String trueName = postString("trueName");
        Integer birth = postInt("birth");
        Integer sex = postInt("sex");
        Integer cityId = postInt("cityId");
        String city = postString("city");
        if (CommonUtil.isEmpty(trueName) || CommonUtil.isEmpty(birth) || CommonUtil.isEmpty(sex) || CommonUtil.isEmpty(cityId) || CommonUtil.isEmpty(city))
            Res.fail(ErrorType.PARAM_ERR);
        Res.success(userService.completeUserInfo(getUserId(), trueName, birth, sex, cityId, city));
    }

    // unionId登录
    @RequestMapping("/loginByUnionId")
    public void loginByUnionId () {
        String unionId = postString("unionId");
        if (CommonUtil.isEmpty(unionId))
            Res.fail(ErrorType.PARAM_ERR);
        Res.success(userService.loginByUnionId(unionId));
    }

    // 小程序绑定手机号
    @RequestMapping("/wxMinBindPhone")
    public void wxMinBindPhone () {
        String phone = postString("phone");
        String unionId = postString("unionId");
        String minOpenId = postString("openId");
        String nickName = postString("nickName");
        String headPic = postString("headPic");
        Integer sex = postInt("sex", 2);
        Integer code = postInt("code");
        Long activityId = postLong("activityId", 0L);
        Long friendId = postLong("friendId", 0L);
        Long saleId = postLong("saleId", 0L);
        if (CommonUtil.isEmpty(phone) || CommonUtil.isEmpty(unionId) || CommonUtil.isEmpty(minOpenId) || CommonUtil.isEmpty(code) || CommonUtil.isEmpty(nickName))
            Res.fail(ErrorType.PARAM_ERR);
        Res.success(userService.wxMinBindPhone(phone, code, unionId, minOpenId, nickName, headPic, sex, activityId, friendId, saleId));
    }


//    // sun
//    // 注册
//    @RequestMapping("/register")
//    public void register () {
//        String phone = postString("phone");
//        String password = postString("password");
//        Integer code = postInt("code");
//        if (!CommonUtil.checkPhone(phone) || !CommonUtil.checkString(password, 6, 16) || CommonUtil.isEmpty(code))
//            Res.fail(ErrorType.PARAM_ERR);
//        Res.success(userService.register(phone, password, code));
//    }
//
//    // sun
//    // 登录
//    @RequestMapping("/login")
//    public void login () {
//        String phone = postString("phone");
//        String password = postString("password");
//        if (!CommonUtil.checkPhone(phone) || !CommonUtil.checkString(password, 6, 16))
//            Res.fail(ErrorType.PARAM_ERR);
//        Res.success(userService.login(phone, password));
//    }
//
//    // sun
//    // 验证码登录
//    @RequestMapping("/loginByCode")
//    public void loginByCode () {
//        String phone = postString("phone");
//        Integer code = postInt("code");
//        if (!CommonUtil.checkPhone(phone) || CommonUtil.isEmpty(code))
//            Res.fail(ErrorType.PARAM_ERR);
//        Res.success(userService.loginByCode(phone, code));
//    }
//
//    // sun
//    // 修改密码
//    @RequestMapping("/changePassword")
//    public void changePassword () {
//        String phone = postString("phone");
//        String password = postString("password");
//        Integer code = postInt("code");
//        if (!CommonUtil.checkPhone(phone) || !CommonUtil.checkString(password, 6, 16) || CommonUtil.isEmpty(code))
//            Res.fail(ErrorType.PARAM_ERR);
//        Map result = userService.changePassword(getUserId(), phone, password, code);
//        Res.success(result);
//    }
//    // sun
//    // 设置性别
//    @RequestMapping("/setSex")
//    public void setSex () {
//        Integer sex = postInt("sex", 1);
//        userService.setSex(getUserId(), sex);
//        Res.success();
//    }
//
//    // sun
//    // 设置头像
//    @RequestMapping("/setHeadPic")
//    public void setHeadPic () {
//        String headPic = postString("headPic");
//        if (CommonUtil.isEmpty(headPic))
//            Res.fail(ErrorType.PARAM_ERR);
//        String newHeadPic = userService.setHeadPic(getUserId(), headPic);
//        Res.success(newHeadPic);
//    }
//
//    // sun
//    // 设置昵称
//    @RequestMapping("/setNickName")
//    public void setNickName () {
//        String nickName = postString("nickName");
//        if (!CommonUtil.checkString(nickName, 2, 10))
//            Res.fail(ErrorType.PARAM_ERR);
//        userService.setNickName(getUserId(), nickName);
//        Res.success();
//    }
//
//    // sun
//    // 获取用户基本信息
//    @RequestMapping("/getUserInfo")
//    public void getUserInfo () {
//        Res.success(userService.getUserInfo(getUserId()));
//    }
}
