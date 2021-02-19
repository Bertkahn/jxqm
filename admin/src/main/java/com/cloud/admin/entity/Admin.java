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
@TableName("ad_admin")
public class Admin implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    /**
     * 组织id
     */
    @TableField("instId")
    private Long instId;

    @TableField("trueName")
    private String trueName;

    private String phone;

    private String password;

    /**
     * 工号
     */
    @TableField("workId")
    private String workId;

    private String token;

    /**
     * 1：正常，2：离职，3：关闭
     */
    private Integer status;

    @TableField("authType")
    private Integer authType;

    /**
     * 1：需要重置密码,0:不需要
     */
    @TableField("resetPassword")
    private Integer resetPassword;

    @TableField("groupId")
    private Long groupId;

    @TableField("createTime")
    private Integer createTime;

    @TableField("deleteTime")
    private Integer deleteTime;


}
