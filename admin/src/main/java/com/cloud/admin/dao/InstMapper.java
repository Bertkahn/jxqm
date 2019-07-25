package com.cloud.admin.dao;

import com.cloud.admin.entity.Inst;
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
 * @since 2019-07-23
 */
public interface InstMapper extends BaseMapper<Inst> {

    List<Map> getInstNameListByIdList (@Param("idList") List<Long> idList);
}
