package com.cloud.admin.entity;

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
 * @since 2019-06-10
 */
@Data
@TableName("ad_authgroup")
public class AuthGroup implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    private String name;

    private String remark;

    @TableField("authType")
    private Integer authType;

    private String rules;

    @TableField("instId")
    private Long instId;

}
