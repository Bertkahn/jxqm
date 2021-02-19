package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.dao.InstMapper;
import com.cloud.admin.entity.Inst;
import com.cloud.admin.service.InstService;
import com.cloud.common.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 倚楼无言
 * @date 2019/7/23 16:51
 */
@Service
public class InstServiceImpl extends ServiceImpl<InstMapper, Inst> implements InstService {

    @Resource
    private InstMapper instMapper;

    @Override
    public List getInstNameListByIdList(List<Long> instIdList) {
        if (CommonUtil.isEmpty(instIdList)) {
            return new ArrayList();
        }
        return instMapper.getInstNameListByIdList(instIdList);
    }
}
