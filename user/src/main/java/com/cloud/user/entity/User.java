package com.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("user")
public class User implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    @TableField("nickName")
    private String nickName;

    @TableField("trueName")
    private String trueName;

    @TableField("isVerify")
    private Integer isVerify;

    private String phone;

    @TableField("headPic")
    private String headPic;

    @TableField("birthYear")
    private Integer birthYear;

    @TableField("birthDay")
    private Integer birthDay;

    @TableField("idCard")
    private String idCard;

    private Integer age;

    private Integer sex;

    @TableField("cityId")
    private Integer cityId;

    private String city;

    @TableField("createTime")
    private Integer createTime;

    @TableField("saleId")
    private Long saleId;

}
