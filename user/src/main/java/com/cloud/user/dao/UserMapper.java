package com.cloud.user.dao;

import com.cloud.user.entity.User;
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
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where phone = #{phone} limit 1")
    User getUserByPhone (String phone);

    @Select("select id from user order by id desc limit 1")
    Long getLastUserId ();
}
