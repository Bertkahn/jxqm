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
 * @since 2019-07-15
 */
@TableName("user_from")
public class UserFrom implements Serializable {

private static final long serialVersionUID=1L;

    @TableId("userId")
    private Long userId;

    @TableField("friendId")
    private Long friendId;

    @TableField("saleId")
    private Long saleId;

    /**
     * 类型1：活动
     */
    @TableField("fromType")
    private Integer fromType;

    @TableField("fromId")
    private Long fromId;

    /**
     * 设备
     */
    private Integer device;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
        this.fromType = fromType;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Integer getDevice() {
        return device;
    }

    public void setDevice(Integer device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "UserFrom{" +
        "userId=" + userId +
        ", friendId=" + friendId +
        ", saleId=" + saleId +
        ", fromType=" + fromType +
        ", fromId=" + fromId +
        ", device=" + device +
        "}";
    }
}
