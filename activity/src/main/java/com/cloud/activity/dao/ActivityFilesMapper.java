package com.cloud.activity.dao;

import com.cloud.activity.entity.ActivityFiles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.common.dto.TableDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2019-07-24
 */
public interface ActivityFilesMapper extends BaseMapper<ActivityFiles> {

    List<Map<String, Object>> selectActivityFiles(@Param("activityId") Long activityId, @Param("table") TableDto tableDto);

}
