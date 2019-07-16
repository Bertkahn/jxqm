package com.cloud.activity.service.impl;

import com.cloud.activity.dao.ActivityMapper;
import com.cloud.activity.dao.UserJoinMapper;
import com.cloud.activity.entity.UserJoin;
import com.cloud.activity.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private UserJoinMapper userJoinMapper;
    @Resource
    private ActivityMapper activityMapper;

    @Override
    public Integer getUserIsJoin(Long userId, Long activityId) {
        UserJoin userJoin = userJoinMapper.getUserJoinByUserAndActivity(userId, activityId);
        return userJoin == null ? 0 : 1;
    }

    @Override
    public Map getActivityInfo(Long activityId) {
        return activityMapper.getActivityInfo(activityId);
    }
}
