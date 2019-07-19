package com.cloud.activity.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.dto.TableDto;

import java.util.List;
import java.util.Map;

public interface Activity1Service {
    void joinActivity (Long userId, Long activityId, List<String> imgList, String want, String remark);

    // userId 我的  friendId 我要投票的人
    void supportFriend (Long userId, Long friendId, Long activityId);

    Integer isSupport (Long userId, Long friendId, Long activityId);

    Page getSupportRankPage (Long activityId, Integer current);

    Map getUserSupportInfo (Long userId, Long activityId);

    /********* admin ********/
    Page getActivityUserPage (TableDto tableDto);
    List getActivityUserImgList (Long userId);
    void verifyActivityUser (Long id, Integer status);
}
