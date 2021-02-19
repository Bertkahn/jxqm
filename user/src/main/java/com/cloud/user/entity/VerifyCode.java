package com.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sun
 * @since 2019-07-10
 */
@Data
@TableName("verify_code")
public class VerifyCode implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    private String phone;

    private Integer code;

    private Integer type;

    private Integer isUse;

    private String ip;

    private Integer createTime;

}
