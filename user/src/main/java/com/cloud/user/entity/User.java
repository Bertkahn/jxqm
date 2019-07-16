package com.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("user")
public class User implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;

    @TableField("nickName")
    private String nickName;

    @TableField("trueName")
    private String trueName;

    @TableField("isVerify")
    private Integer isVerify;

    private String phone;

    @TableField("headPic")
    private String headPic;

    @TableField("birthYear")
    private Integer birthYear;

    @TableField("birthDay")
    private Integer birthDay;

    @TableField("idCard")
    private String idCard;

    private Integer age;

    private Integer sex;

    @TableField("cityId")
    private Integer cityId;

    private String city;

    @TableField("createTime")
    private Integer createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Integer birthDay) {
        this.birthDay = birthDay;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", nickName=" + nickName +
        ", trueName=" + trueName +
        ", phone=" + phone +
        ", headPic=" + headPic +
        ", birthYear=" + birthYear +
        ", birthDay=" + birthDay +
        ", idCard=" + idCard +
        ", age=" + age +
        ", sex=" + sex +
        ", createTime=" + createTime +
        "}";
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }
}
