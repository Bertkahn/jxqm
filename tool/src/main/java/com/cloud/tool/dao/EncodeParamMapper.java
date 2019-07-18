package com.cloud.tool.dao;

import com.cloud.tool.entity.EncodeParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2019-07-17
 */
public interface EncodeParamMapper extends BaseMapper<EncodeParam> {
    @Select("select token from encode_param where param = #{param} limit 1")
    String getTokenByParamString (String param);
}
