package com.cloud.user.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.dto.TableDto;
import com.cloud.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2019-07-11
 */
public interface UserMapper extends BaseMapper<User> {
    /*******  client  *******/
    @Select("select * from user where phone = #{phone} limit 1")
    User getUserByPhone (String phone);

    @Select("select id from user order by id desc limit 1")
    Long getLastUserId ();

    List<User> getUserListByUserIdList (@Param("idList") List<Long> isList);

    /*******  admin  *******/
    List<Map> getMyCustomerList (@Param("saleId") Long saleId, @Param("table")TableDto tableDto, Page page);
}
