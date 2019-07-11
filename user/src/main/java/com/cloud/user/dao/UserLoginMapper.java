package com.cloud.user.dao;

import com.cloud.user.entity.UserLogin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2019-07-11
 */
public interface UserLoginMapper extends BaseMapper<UserLogin> {
    @Select("select * from user_login where unionId = #{unionId} limit 1")
    UserLogin getUserLoginByUnionId (String unionId);

    @Select("select * from user_login where phone = #{phone} limit 1")
    UserLogin getUserLoginByPhone (String phone);

    @Select("select * from user_login where token = #{token} limit 1")
    UserLogin getUserLoginByToken (String token);


}
