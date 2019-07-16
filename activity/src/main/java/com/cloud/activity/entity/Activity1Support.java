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
 * @since 2019-07-16
 */
@TableName("activity_1_support")
public class Activity1Support implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    @TableField("activityId")
    private Long activityId;

    @TableField("userId")
    private Long userId;

    @TableField("friendId")
    private Long friendId;

    @TableField("createTime")
    private Integer createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Activity1Support{" +
        "id=" + id +
        ", activityId=" + activityId +
        ", userId=" + userId +
        ", friendId=" + friendId +
        ", createTime=" + createTime +
        "}";
    }
}
