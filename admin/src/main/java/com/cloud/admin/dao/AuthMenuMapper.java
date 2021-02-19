package com.cloud.admin.dao;

import com.cloud.admin.entity.AuthMenu;
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
 * @since 2019-06-13
 */
public interface AuthMenuMapper extends BaseMapper<AuthMenu> {

    @Select("select * from ad_authmenu where authType = #{authType}")
    List<Map<String, Object>> getMenuListByAuth(@Param("authType") Integer authType);

    List<Map<String, Object>> getMenuListByAliasList(@Param("aliasList") String[] aliasList);

    List<Map<String, Object>> getMenuListByIdList(@Param("idList") List<Long> idList);

    List<Map<String, Object>> getSelectMenuListByAuth(@Param("authType") Integer authType, @Param("isParent") boolean isParent);

    List<Map<String, Object>> getAuthMenuList(@Param("table") TableDto tableDto);

    @Select("select * from authMenu where pid = #{pid} limit 1")
    AuthMenu getAuthMenuByPid(Long pid);
}
