package com.cloud.tool.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2019-07-17
 */
@Data
@TableName("encode_param")
public class EncodeParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @TableId
    private String token;

    private String param;

    @TableField("createTime")
    private Integer createTime;

}
