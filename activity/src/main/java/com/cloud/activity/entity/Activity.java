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
@TableName("activity")
public class Activity implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    @TableField("instId")
    private Long instId;

    /**
     * 活动类型
     */
    private Integer type;

    private String name;

    private String img;

    private String description;

    /**
     * 0：未启用(不展示在用户列表)，1：未开始，2：正常，3：结束，4：主动关闭
     */
    private Integer status;

    @TableField("startTime")
    private Integer startTime;

    @TableField("endTime")
    private Integer endTime;

    @TableField("createTime")
    private Integer createTime;


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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Activity{" +
        "id=" + id +
        ", instId=" + instId +
        ", type=" + type +
        ", name=" + name +
        ", img=" + img +
        ", description=" + description +
        ", status=" + status +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", createTime=" + createTime +
        "}";
    }
}
