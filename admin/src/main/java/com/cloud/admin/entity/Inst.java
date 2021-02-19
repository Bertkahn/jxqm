package com.cloud.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sun
 * @since 2019-07-23
 */
@Data
@TableName("ad_inst")
public class Inst implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    @TableField("cityId")
    private Integer cityId;

    private String address;

    @TableField("addressDetail")
    private String addressDetail;

    @TableField("createTime")
    private Integer createTime;

}
