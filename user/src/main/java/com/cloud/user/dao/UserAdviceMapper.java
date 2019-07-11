package com.cloud.user.dao;

import com.cloud.user.entity.UserAdvice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2019-07-01
 */
public interface UserAdviceMapper extends BaseMapper<UserAdvice> {
    List<String> getUserAdviceByUserIdList (@Param("userId") Integer userId);
}
