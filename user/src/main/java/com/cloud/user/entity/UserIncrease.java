package com.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sun
 * @since 2019-07-11
 */
@TableName("user_increase")
public class UserIncrease implements Serializable {

private static final long serialVersionUID=1L;

    @TableId("dayTime")
    private Integer dayTime;

    private Integer num;


    public Integer getDayTime() {
        return dayTime;
    }

    public void setDayTime(Integer dayTime) {
        this.dayTime = dayTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "UserIncrease{" +
        "dayTime=" + dayTime +
        ", num=" + num +
        "}";
    }
}
