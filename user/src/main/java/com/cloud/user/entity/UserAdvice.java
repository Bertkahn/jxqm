package com.cloud.user.entity;

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
 * @since 2019-07-01
 */
@Data
@TableName("user_advice")
public class UserAdvice implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    @TableField("userId")
    private Long userId;

    @TableField("projectId")
    private Long projectId;

    @TableField("createTime")
    private Integer createTime;
}
