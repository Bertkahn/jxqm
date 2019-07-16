package com.cloud.activity.dao;

import com.cloud.activity.entity.UserJoin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2019-07-15
 */
public interface UserJoinMapper extends BaseMapper<UserJoin> {
    @Select("select * from user_join where userId = #{userId} and activityId = #{activityId} limit 1")
    UserJoin getUserJoinByUserAndActivity (@Param("userId") Long userId, @Param("activityId") Long activityId);
}
