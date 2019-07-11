package com.cloud.admin.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.entity.Auth;
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
 * @since 2019-06-10
 */
public interface AuthMapper extends BaseMapper<Auth> {

    @Select("select * from auth where alias = #{alias} limit 1")
    Auth getAuthByAlias(String alias);

    @Select("select * from auth where authType = #{authType}")
    List<Map<String, Object>> getAuthListByAuthType(Integer authType);

    List<Map> getAuthList(@Param("table") TableDto tableDto, Page page);

    List<Map<String, Object>> getAuthByAliasListAndSystem(@Param("aliasList") String[] aliasList);
}
