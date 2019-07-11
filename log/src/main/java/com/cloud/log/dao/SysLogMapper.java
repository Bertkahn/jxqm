package com.cloud.log.dao;

import com.cloud.log.entity.SysLog;
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
public interface SysLogMapper extends BaseMapper<SysLog> {
    void insertSysLogList (@Param("list") List<SysLog> sysLogList);
}
