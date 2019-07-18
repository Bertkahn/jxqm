package com.cloud.activity.dao;

import com.cloud.activity.entity.Activity1Support;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2019-07-16
 */
public interface Activity1SupportMapper extends BaseMapper<Activity1Support> {
    @Select("select id from activity_1_support where userId = #{userId} and friendId = #{friendId} and activityId = #{activityId} limit 1")
    Long isSupport(@Param("userId") Long userId, @Param("friendId") Long friendId, @Param("activityId") Long activityId);
}
