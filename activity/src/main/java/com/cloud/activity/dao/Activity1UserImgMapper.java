package com.cloud.activity.dao;

import com.cloud.activity.entity.Activity1UserImg;
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
 * @since 2019-07-15
 */
public interface Activity1UserImgMapper extends BaseMapper<Activity1UserImg> {
    void insertAll (@Param("list")List<Activity1UserImg> list);

    @Select("select * from activity_1_user_img where userId = #{userId} and activityId = 1")
    List<Activity1UserImg> getImgListByUserId (Long userId);
}
