package com.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("user_increase")
public class UserIncrease implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    @TableId("dayTime")
    private Integer dayTime;

    private Integer num;

}
