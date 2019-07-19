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
@TableName("auth")
public class Auth implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;

    private String alias;

    private String name;

    private String remark;

    @TableField("menuId")
    private Long menuId;

    @TableField("authType")
    private Integer authType;

    @TableField("isSystem")
    private Integer isSystem;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    @Override
    public String toString() {
        return "Auth{" +
        "id=" + id +
        ", alias=" + alias +
        ", name=" + name +
        ", menuId=" + menuId +
        ", authType=" + authType +
        "}";
    }

    public Integer getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Integer isSystem) {
        this.isSystem = isSystem;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
