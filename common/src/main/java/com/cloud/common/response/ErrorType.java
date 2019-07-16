package com.cloud.common.response;

/**
 * Created  by sun on 2017/9/22.
 */
public enum ErrorType {
    /**
     * 1-1000 退出登录
     * 1000-9999 错误
     * 10000-99999 需要做处理
     */

    //
    ACTIVITY_HAS_JOIN       (9974, "已参加该活动，请直接进入活动页面"), // 已参加该活动，请直接进入活动页面
    USER_NOT_EXIST          (9975, "用户不存在"), // 用户不存在-- 不需要去登录
    PHONE_BIND_ERR          (9976, "已绑定过手机号"), // 已绑定过手机号
    WX_BIND_ERR             (9977, "已绑定过微信账号"), // 已绑定其他微信账号
    ALREADY_BIND            (9978, "用户已绑定"), // 用户已绑定
    ORDER_GOODS_INVALID     (9979, "产品已失效，请重新下单"), //产品已失效，请重新下单
    VERIFYCODE_LIMIT        (9980, "验证码获取频繁，请稍后再试"), //验证码获取频繁，请稍后再试
    VERIFYCODE_EXPIRE       (9981, "验证码已失效"), // 验证码已失效
    PHONE_NOT_EXIST         (9982, "手机号未注册"), // 手机号未注册
    VERIFYCODE_FAIL         (9983, "验证码发送失败"), // 验证码发送失败
    PASSWORD_ERR            (9984, "密码错误"), // 密码错误
    HAS_MEMBER              (9985, "请先删除成员"), // 请先删除成员
    HAS_CHILD               (9986, "请先删除子节点"), // 请先删除子节点
    EDIT_SELF               (9987, "信息无法修改"), // 信息无法修改
    ALIAS_EXIST             (9988, "alias已存在"), // alias已存在
    SUCCESS                 (9989, "SUCCESS"), // success
    ACCOUNT_CLOSE           (9990, "您的账号已被关闭，请联系管理员"), // status!=1
    PHONE_EXIST             (9991, "手机号已被注册"), // 手机号已被注册
    LOGIN_FAIL              (9992, "用户名或密码错误"), // 用户名或密码错误
    NOT_EXIST               (9993, "对象不存在"), // 对象不存在
    PARAM_ERR               (9994, "param error"), // 参数错误
    ADMIN_NO_AUTH           (9995, "没有操作权限"), // 没有操作权限
    SERVER_PARAM_ERR        (9996, "服务参数错误"), // 服务参数错误
    SERVER_CONNECT_ERR      (9997, "服务处理失败"), // 服务连接错误
    OTHER_ERR               (9998, "error"), // 自定义错误
    SERVER_ERR              (9999, "服务器处理失败"), // exception
    //
    LOGIN_EXPIRE            (9, "登录信息失效，请重新登录"),
    UNSAFE_DEVICE_NOT_EXIST (8, "设备错误"),
    UNSAFE_USER_NOT_EXIST   (7, "用户不存在"),
    UNSAFE_LOGIN_FIRST      (6, "请先登录"),
    UNSAFE_POST_DATA        (5, "获取请求参数失败"),
    UNSAFE_TIME             (4, "请确保客户端时间与网络时间一致"),
    UNSAFE_WRONG_TOKEN      (3, "不安全的请求"),// token不存在
    UNSAFE_DECODE_ERR       (2, "不安全的请求"),// 解密失败
    UNSAFE_EMPTY            (1, "不安全的请求");// 参数不存在

    private int code;
    private String msg;
    ErrorType(int code , String msg){
        this.code = code;
        this.msg = msg;
    }
    public int getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
}