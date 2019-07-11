package com.cloud.user.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.dto.TableDto;
import com.cloud.user.entity.SaleUser;
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
public interface SaleUserMapper extends BaseMapper<SaleUser> {
    List<Map<String, Object>> getSaleUserPageByAdminId (@Param("adminId") Long adminId, @Param("table")TableDto tableDto, Page page);
}
