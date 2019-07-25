package com.cloud.activity.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.activity.entity.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.common.dto.TableDto;
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
 * @since 2019-07-16
 */
public interface ActivityMapper extends BaseMapper<Activity> {
    @Select("select id, instId, type, name, img, status, startTime, endTime from activity where id = #{activityId}")
    Map getActivityInfo (Long activityId);
    // jiang
    //获取全部活动
    List<Map<String, Object>> getActivityList(@Param("table") TableDto tableDto, Page page);
}
