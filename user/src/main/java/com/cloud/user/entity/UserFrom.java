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
@TableName("user_from")
public class UserFrom implements Serializable {

private static final long serialVersionUID=1L;

    @TableId("userId")
    private Long userId;

    @TableField("friendId")
    private Long friendId;

    @TableField("saleId")
    private Long saleId;

    @TableField("instId")
    private Long instId;

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

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
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
        ", instId=" + instId +
        ", device=" + device +
        "}";
    }
}
