package com.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sun
 * @since 2019-07-10
 */
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "VerifyCode{" +
        "id=" + id +
        ", phone=" + phone +
        ", code=" + code +
        ", type=" + type +
        ", isUse=" + isUse +
        ", ip=" + ip +
        ", createTime=" + createTime +
        "}";
    }
}
