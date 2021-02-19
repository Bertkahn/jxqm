package com.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2019-07-15
 */
@Data
@TableName("user_from")
public class UserFrom implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    @TableId("userId")
    private Long userId;

    @TableField("friendId")
    private Long friendId;

    @TableField("saleId")
    private Long saleId;

    /**
     * 类型1：活动
     */
    @TableField("fromType")
    private Integer fromType;

    @TableField("fromId")
    private Long fromId;

    /**
     * 设备
     */
    private Integer device;
}
