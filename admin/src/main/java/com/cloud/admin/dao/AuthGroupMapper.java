package com.cloud.admin.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.entity.AuthGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.common.dto.TableDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2019-06-10
 */
public interface AuthGroupMapper extends BaseMapper<AuthGroup> {

    @Select("select rules from ad_authgroup where id = #{groupId}")
    String getRulesByGroupId(Long groupId);

    @Select("select * from ad_authgroup where authType = #{authType} and id > 1")
    List<AuthGroup> getAuthGroupList(Integer authType);

    List<AuthGroup> getAuthGroupPage(@Param("table") TableDto tableDto, Page page);
}
