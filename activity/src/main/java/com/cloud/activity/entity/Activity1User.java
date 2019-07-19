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
@TableName("activity_1_user")
public class Activity1User implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    @TableField("activityId")
    private Long activityId;

    @TableField("userId")
    private Long userId;

    private String want;

    private String remark;

    @TableField("supportNum")
    private Integer supportNum;

    // 0：待审核，1：入选，2：淘汰
    private Integer status;

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

    public String getWant() {
        return want;
    }

    public void setWant(String want) {
        this.want = want;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Activity1User{" +
        "id=" + id +
        ", activityId=" + activityId +
        ", userId=" + userId +
        ", want=" + want +
        ", remark=" + remark +
        ", createTime=" + createTime +
        "}";
    }

    public Integer getSupportNum() {
        return supportNum;
    }

    public void setSupportNum(Integer supportNum) {
        this.supportNum = supportNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
