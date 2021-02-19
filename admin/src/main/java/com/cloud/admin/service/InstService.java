package com.cloud.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.admin.entity.Inst;

import java.util.List;

/**
 * @author 倚楼无言
 * @date 2019/7/23 16:51
 */
public interface InstService extends IService<Inst> {
    List getInstNameListByIdList(List<Long> InstIdList);
}
