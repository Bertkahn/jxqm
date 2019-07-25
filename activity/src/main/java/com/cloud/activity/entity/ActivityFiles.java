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
 * @since 2019-07-24
 */
@TableName("activity_files")
public class ActivityFiles implements Serializable {

private static final long serialVersionUID=1L;

    private Integer id;

    private String url;

    @TableField("activityId")
    private Long activityId;

    private String name;

    /**
     * 0：other，1：img，2：audio， 3：video，4：txt，5：word，6：pdf，
     */
    private Integer type;

    @TableField("createTime")
    private Integer createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ActivityFiles{" +
        "id=" + id +
        ", url=" + url +
        ", activityId=" + activityId +
        ", name=" + name +
        ", type=" + type +
        ", createTime=" + createTime +
        "}";
    }
}
