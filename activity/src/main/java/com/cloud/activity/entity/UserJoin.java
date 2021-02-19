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
@TableName("user_join")
public class UserJoin implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    @TableField("userId")
    private Long userId;

    @TableField("activityId")
    private Long activityId;

}
