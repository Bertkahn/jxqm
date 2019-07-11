package com.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sun
 * @since 2019-07-11
 */
@TableName("user_belong")
public class UserBelong implements Serializable {

private static final long serialVersionUID=1L;

    @TableId("userId")
    private Long userId;

    @TableField("saleId")
    private Long saleId;

    @TableField("instId")
    private Long instId;

    @TableField("createTime")
    private Integer createTime;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserBelong{" +
        "userId=" + userId +
        ", saleId=" + saleId +
        ", instId=" + instId +
        ", createTime=" + createTime +
        "}";
    }
}
