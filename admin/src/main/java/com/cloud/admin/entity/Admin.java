package com.cloud.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sun
 * @since 2019-06-10
 */
@TableName("admin")
public class Admin implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    /**
     * 组织id
     */
    @TableField("instId")
    private Long instId;

    @TableField("userId")
    private Long userId;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
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

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public Integer getResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(Integer resetPassword) {
        this.resetPassword = resetPassword;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Integer deleteTime) {
        this.deleteTime = deleteTime;
    }

    @Override
    public String toString() {
        return "Admin{" +
        "id=" + id +
        ", instId=" + instId +
        ", trueName=" + trueName +
        ", phone=" + phone +
        ", password=" + password +
        ", workId=" + workId +
        ", token=" + token +
        ", authType=" + authType +
        ", resetPassword=" + resetPassword +
        ", groupId=" + groupId +
        ", createTime=" + createTime +
        ", deleteTime=" + deleteTime +
        "}";
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
