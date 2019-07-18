package com.cloud.activity.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.activity.dao.Activity1SupportMapper;
import com.cloud.activity.dao.Activity1UserImgMapper;
import com.cloud.activity.dao.Activity1UserMapper;
import com.cloud.activity.dao.UserJoinMapper;
import com.cloud.activity.entity.Activity1Support;
import com.cloud.activity.entity.Activity1User;
import com.cloud.activity.entity.Activity1UserImg;
import com.cloud.activity.entity.UserJoin;
import com.cloud.activity.service.Activity1Service;
import com.cloud.common.constant.RedisConst;
import com.cloud.common.constant.TimeConst;
import com.cloud.common.feign.UserFeign;
import com.cloud.common.redis.Redis;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.AliUtil;
import com.cloud.common.util.CommonUtil;
import com.cloud.common.util.IdUtil;
import com.cloud.common.util.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Activity1ServiceImpl implements Activity1Service {
    @Resource
    private Activity1UserMapper activity1UserMapper;
    @Resource
    private Activity1UserImgMapper activity1UserImgMapper;
    @Resource
    private UserJoinMapper userJoinMapper;
    @Resource
    private Activity1SupportMapper activity1SupportMapper;
    @Resource
    private UserFeign userFeign;
    @Resource
    private Redis redis;

    @Override
    @Transactional
    public void joinActivity(Long userId, Long activityId, List<String> imgList, String want, String remark) {
        UserJoin userJoin = userJoinMapper.getUserJoinByUserAndActivity(userId, activityId);
        if (userJoin != null)
            Res.fail(ErrorType.ACTIVITY_HAS_JOIN);
        //
        Activity1User activity1User = new Activity1User();
        activity1User.setRemark(remark);
        activity1User.setUserId(userId);
        activity1User.setWant(want);
        activity1User.setActivityId(activityId);
        activity1User.setCreateTime(TimeUtil.getTimeStamp());
        activity1UserMapper.insert(activity1User);
        // join
        userJoin = new UserJoin();
        userJoin.setUserId(userId);
        userJoin.setActivityId(activityId);
        userJoinMapper.insert(userJoin);
        // img
        List<Map<String, String>> imgMoveList = new ArrayList<>();
        List<Activity1UserImg> list = new ArrayList<>();
        for (String img : imgList) {
            Activity1UserImg activity1UserImg = new Activity1UserImg();
            activity1UserImg.setId(IdUtil.getId());
            activity1UserImg.setActivityId(activityId);
            activity1UserImg.setUserId(userId);
            String imgPath = "p/activity/activity1/user/" + TimeUtil.formatTime("YMD") + "/" + CommonUtil.createToken();
            activity1UserImg.setImg(imgPath);
            list.add(activity1UserImg);
            Map<String, String> map = new HashMap<>();
            map.put("from", img);
            map.put("to", imgPath);
            imgMoveList.add(map);
        }
        activity1UserImgMapper.insertAll(list);
        // 转移图片
        AliUtil.moveImgList(imgMoveList);
    }

    @Override
    @Transactional
    public void supportFriend(Long userId, Long friendId, Long activityId) {
        if (userId.equals(friendId))
            Res.fail(ErrorType.SUPPORT_SELF);
        // 注意这里的逻辑,非常重要，并没有写错
        if (isSupport(userId, friendId, activityId) == 1)
            Res.fail(ErrorType.HAS_SUPPORT);
        Activity1Support activity1Support = new Activity1Support();
        activity1Support.setUserId(friendId);
        activity1Support.setFriendId(userId);
        activity1Support.setActivityId(activityId);
        activity1Support.setCreateTime(TimeUtil.getTimeStamp());
        activity1SupportMapper.insert(activity1Support);
        //
        Activity1User activity1User = activity1UserMapper.getByUserIdAndActivityId(friendId, activityId);
        activity1User.setSupportNum(activity1User.getSupportNum() + 1);
        activity1UserMapper.updateById(activity1User);
    }

    @Override
    public Integer isSupport(Long userId, Long friendId, Long activityId) {
        // 是否已经投过票
        Long isSupport = activity1SupportMapper.isSupport(friendId, userId, activityId);
        return CommonUtil.isEmpty(isSupport) ? 0 : 1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page getSupportRankPage(Long activityId, Integer current) {
        String redisKey = RedisConst.activityKey + activityId + current;
        Page<Map<String, Object>> page = redis.get(redisKey, Page.class);
        if (page != null)
            return page;
        page = new Page<>(current, 10);
        if (current > 10)
            return page;
        page.setRecords(activity1UserMapper.getSupportRankList(activityId, page));
        List<Long> userIdList = new ArrayList<>();
        for (Map<String, Object> item : page.getRecords()) {
            userIdList.add(Long.parseLong(item.get("userId").toString()));
        }
        List<Map<String, Object>> userList = userFeign.getUserListByUserIdList(userIdList);
        for (Map<String, Object> item : page.getRecords()) {
            for (Map<String, Object> user : userList) {
                if (item.get("userId").toString().equals(user.get("id").toString())) {
                    item.put("nickName", user.get("nickName"));
                    item.put("headPic", user.get("headPic"));
                    break;
                }
            }
        }
        redis.set(redisKey, page, 5);
        return page;
    }

    @Override
    public Map getUserSupportInfo(Long userId, Long activityId) {
        Activity1User support = activity1UserMapper.getByUserIdAndActivityId(userId, activityId);
        Map<String, Object> user = userFeign.getUserByUserId(support.getUserId());
        Map<String, Object> result = new HashMap<>();
        result.put("supportNum", support.getSupportNum());
        result.put("nickName", user.get("nickName"));
        result.put("headPic", user.get("headPic"));
        return result;
    }
}
