package com.cloud.activity.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.activity.dao.ActivityMapper;
import com.cloud.activity.dao.UserJoinMapper;
import com.cloud.activity.entity.Activity;
import com.cloud.activity.entity.UserJoin;
import com.cloud.activity.service.ActivityService;
import com.cloud.common.dto.TableDto;
import com.cloud.common.feign.AdminFeign;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
    @Resource
    private UserJoinMapper userJoinMapper;
    @Resource
    private ActivityMapper activityMapper;
    @Resource
    private AdminFeign adminFeign;

    @Override
    public Integer getUserIsJoin(Long userId, Long activityId) {
        UserJoin userJoin = userJoinMapper.getUserJoinByUserAndActivity(userId, activityId);
        return userJoin == null ? 0 : 1;
    }

    @Override
    public Map getActivityInfo(Long activityId) {
        return activityMapper.getActivityInfo(activityId);
    }

    //jiang
    //全部活动分页
    @Override
    public Page getActivityPage(TableDto tableDto) {
        Page<Map<String, Object>> page = new Page<>(tableDto.getCurrent(), tableDto.getSize());
        page.setRecords(activityMapper.getActivityList(tableDto, page));
        List<Long> instIdList = new ArrayList<>();
        page.getRecords().forEach(item->{
            instIdList.add(Long.parseLong(item.get("instId").toString()));
        });
        List<Map<String, Object>> instList = adminFeign.getInstNameListByIdList(instIdList);
        page.getRecords().forEach(item->{
            for (Map<String, Object> inst : instList) {
                if (item.get("instId").toString().equals(inst.get("id").toString())) {
                    item.put("instName", inst.get("name"));
                    break;
                }
            }
        });
        return page;
    }
}
