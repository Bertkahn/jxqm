package com.cloud.activity.entity;

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
 * @since 2019-07-16
 */
@Data
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

}
