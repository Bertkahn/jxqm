package com.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sun
 * @since 2019-07-11
 */
@TableName("user_login")
public class UserLogin implements Serializable {

private static final long serialVersionUID=1L;

    @TableId("userId")
    private Long userId;

    private String phone;

    private String password;

    private String token;

    @TableField("openId")
    private String openId;

    @TableField("minOpenId")
    private String minOpenId;

    @TableField("unionId")
    private String unionId;

    @TableField("aliId")
    private String aliId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMinOpenId() {
        return minOpenId;
    }

    public void setMinOpenId(String minOpenId) {
        this.minOpenId = minOpenId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getAliId() {
        return aliId;
    }

    public void setAliId(String aliId) {
        this.aliId = aliId;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
        "userId=" + userId +
        ", phone=" + phone +
        ", password=" + password +
        ", token=" + token +
        ", openId=" + openId +
        ", minOpenId=" + minOpenId +
        ", unionId=" + unionId +
        ", aliId=" + aliId +
        "}";
    }
}
