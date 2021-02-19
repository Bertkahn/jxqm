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

}
