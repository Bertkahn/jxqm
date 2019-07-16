package com.cloud.activity.dao;

import com.cloud.activity.entity.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2019-07-16
 */
public interface ActivityMapper extends BaseMapper<Activity> {
    @Select("select id, instId, type, name, img, status, startTime, endTime from activity where id = #{activityId}")
    Map getActivityInfo (Long activityId);
}
