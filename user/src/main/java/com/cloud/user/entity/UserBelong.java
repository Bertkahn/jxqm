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
 * @since 2019-07-11
 */
@Data
@TableName("user_belong")
public class UserBelong implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    @TableId("userId")
    private Long userId;

    @TableField("saleId")
    private Long saleId;

    @TableField("instId")
    private Long instId;

    @TableField("createTime")
    private Integer createTime;

}
