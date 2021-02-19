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
 * @since 2019-07-15
 */
@Data
@TableName("activity_1_user_img")
public class Activity1UserImg implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    @TableField("activityId")
    private Long activityId;

    @TableField("userId")
    private Long userId;

    private String img;

}
