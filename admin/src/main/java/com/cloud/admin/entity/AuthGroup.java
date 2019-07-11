package com.cloud.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sun
 * @since 2019-06-10
 */
@TableName("authGroup")
public class AuthGroup implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    private String name;

    @TableField("authType")
    private Integer authType;

    private String rules;

    @TableField("instId")
    private Long instId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    @Override
    public String toString() {
        return "AuthGroup{" +
        "id=" + id +
        ", name=" + name +
        ", authType=" + authType +
        ", rules=" + rules +
        ", instId=" + instId +
        "}";
    }
}
