package com.cloud.admin.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.entity.Admin;
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
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("select * from admin where token = #{token} limit 1")
    Admin getAdminByToken(String token);

    @Select("select * from admin where phone = #{phone} limit 1")
    Admin getAdminByPhone(String phone);

    @Select("select * from admin where workId = #{workId} limit 1")
    Admin getAdminByWorkId(Integer workId);

    List<Map> getAdminList(@Param("authType") Integer authType, @Param("table") TableDto tableDto, Page page);

    void delAdminByIdAndAuthType(@Param("adminId") Long adminId, @Param("authType") Integer authType);

    @Select("select * from admin order by id desc limit 1")
    Admin getLastAdmin();

    @Select("select * from admin where groupId = #{groupId} limit 1")
    Admin getAdminByGroupId(Long groupId);

    List<Map> getAdminAndCompanyList (@Param("idList") List<Long> adminIdList);

    List<Map> getAdminListByIdList (@Param("idList") List<Long> idList);
}
