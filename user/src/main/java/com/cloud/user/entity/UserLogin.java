package com.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sun
 * @since 2019-07-11
 */
@Data
@TableName("user_login")
public class UserLogin implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

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

}
