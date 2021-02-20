package com.cloud.user.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.dto.TableDto;
import com.cloud.user.dao.SaleUserMapper;
import com.cloud.user.dao.UserAdviceMapper;
import com.cloud.user.service.CrmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CrmServiceImpl implements CrmService {
    @Resource
    private SaleUserMapper saleUserMapper;
    @Resource
    private UserAdviceMapper userAdviceMapper;

    @Override
    public Page<Map<String, Object>> getSaleUserPageByAdminId(Long adminId, TableDto tableDto) {
        Page<Map<String, Object>> page = new Page<>(tableDto.getCurrent(), tableDto.getSize());
        page.setRecords(saleUserMapper.getSaleUserPageByAdminId(adminId, tableDto, page));
        for (Map<String, Object> user : page.getRecords()) {
            List<String> projectList = userAdviceMapper.getUserAdviceByUserIdList(Integer.parseInt(user.get("id").toString()));
            user.put("projects", String.join(",", projectList));
        }
        return page;
    }
}
