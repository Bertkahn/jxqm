package com.cloud.activity.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.activity.entity.Activity1User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.common.dto.TableDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2019-07-16
 */
public interface Activity1UserMapper extends BaseMapper<Activity1User> {
    @Select("select userId, supportNum from activity_1_user where activityId = #{activityId} order by supportNum desc")
    List<Map<String, Object>> getSupportRankList (@Param("activityId") Long activityId, Page page);

    @Select("select * from activity_1_user where userId = #{userId} and activityId = #{activityId}")
    Activity1User getByUserIdAndActivityId (@Param("userId") Long userId, @Param("activityId") Long activityId);

    /******* admin *******/
    List<Map<String, Object>> getActivityUserList (@Param("table")TableDto tableDto, Page page);

}
