package com.cloud.activity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sun
 * @since 2019-07-15
 */
@TableName("user_join")
public class UserJoin implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    @TableField("userId")
    private Long userId;

    @TableField("activityId")
    private Long activityId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "UserJoin{" +
        "id=" + id +
        ", userId=" + userId +
        ", activityId=" + activityId +
        "}";
    }
}
