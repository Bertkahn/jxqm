package com.cloud.log.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sun
 * @since 2019-07-10
 */
@TableName("api_log")
public class ApiLog implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    @TableField("userId")
    private Long userId;

    private String ip;

    @TableField("userAgent")
    private String userAgent;

    private String token;

    private String param;

    private String query;

    private String url;

    private String remark;

    @TableField("createTime")
    private Integer createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ApiLog{" +
        "id=" + id +
        ", userId=" + userId +
        ", ip=" + ip +
        ", userAgent=" + userAgent +
        ", token=" + token +
        ", param=" + param +
        ", query=" + query +
        ", url=" + url +
        ", remark=" + remark +
        ", createTime=" + createTime +
        "}";
    }
}
