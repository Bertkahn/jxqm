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
 * @since 2019-07-24
 */
@Data
@TableName("activity_files")
public class ActivityFiles implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

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

}
