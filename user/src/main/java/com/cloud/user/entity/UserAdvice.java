package com.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sun
 * @since 2019-07-01
 */
@TableName("user_advice")
public class UserAdvice implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    @TableField("userId")
    private Integer userId;

    @TableField("projectId")
    private Long projectId;

    @TableField("createTime")
    private Integer createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserAdvice{" +
        "id=" + id +
        ", userId=" + userId +
        ", projectId=" + projectId +
        ", createTime=" + createTime +
        "}";
    }
}
