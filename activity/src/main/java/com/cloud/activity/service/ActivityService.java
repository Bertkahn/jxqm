package com.cloud.activity.service;

import java.util.Map;

public interface ActivityService {
    Integer getUserIsJoin (Long userId, Long activityId);

    Map getActivityInfo (Long activityId);
}
