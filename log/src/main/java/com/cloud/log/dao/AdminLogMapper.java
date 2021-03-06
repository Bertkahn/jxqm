package com.cloud.log.dao;

import com.cloud.log.entity.AdminLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2019-07-10
 */
public interface AdminLogMapper extends BaseMapper<AdminLog> {
    void insertAll (@Param("list") List<AdminLog> sysLogList);
}
