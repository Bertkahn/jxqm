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
@TableName("sys_log")
public class SysLog implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    private String url;

    @TableField("userAgent")
    private String userAgent;

    private String ip;

    private String msg;

    private String detail;

    private String query;

    private String param;

    @TableField("createTime")
    private Integer createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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
        return "SysLog{" +
        "id=" + id +
        ", url=" + url +
        ", userAgent=" + userAgent +
        ", msg=" + msg +
        ", detail=" + detail +
        ", query=" + query +
        ", param=" + param +
        ", createTime=" + createTime +
        "}";
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
