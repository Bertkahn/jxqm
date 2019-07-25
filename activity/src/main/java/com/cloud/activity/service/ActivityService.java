package com.cloud.activity.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.dto.TableDto;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    Integer getUserIsJoin (Long userId, Long activityId);

    Map getActivityInfo (Long activityId);

    Page getActivityPage(TableDto table);
}
