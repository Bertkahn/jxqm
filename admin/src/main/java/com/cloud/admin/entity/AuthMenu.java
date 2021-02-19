package com.cloud.admin.entity;

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
 * @since 2019-06-13
 */
@Data
@TableName("ad_authmenu")
public class AuthMenu implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    private String path;

    private String name;

    private String icon;

    private Long pid;

    @TableField("authType")
    private Integer authType;

    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AuthMenu{" +
        "id=" + id +
        ", path=" + path +
        ", name=" + name +
        ", icon=" + icon +
        ", pid=" + pid +
        ", authType=" + authType +
        ", remark=" + remark +
        "}";
    }
}
