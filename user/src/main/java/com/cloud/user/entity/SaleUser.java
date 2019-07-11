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
 * @since 2019-07-01
 */
@TableName("sale_user")
public class SaleUser implements Serializable {

private static final long serialVersionUID=1L;

    @TableId("userId")
    private Integer userId;

    @TableField("saleId")
    private Long saleId;

    @TableField("nextVisitTime")
    private Integer nextVisitTime;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Integer getNextVisitTime() {
        return nextVisitTime;
    }

    public void setNextVisitTime(Integer nextVisitTime) {
        this.nextVisitTime = nextVisitTime;
    }

    @Override
    public String toString() {
        return "SaleUser{" +
        "userId=" + userId +
        ", saleId=" + saleId +
        ", visitTime=" + nextVisitTime +
        "}";
    }
}
