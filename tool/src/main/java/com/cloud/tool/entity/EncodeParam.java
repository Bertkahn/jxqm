package com.cloud.tool.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sun
 * @since 2019-07-17
 */
@TableName("encode_param")
public class EncodeParam implements Serializable {

private static final long serialVersionUID=1L;

    @TableId
    private String token;

    private String param;

    @TableField("createTime")
    private Integer createTime;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "EncodeParam{" +
        "token=" + token +
        ", param=" + param +
        ", createTime=" + createTime +
        "}";
    }
}
