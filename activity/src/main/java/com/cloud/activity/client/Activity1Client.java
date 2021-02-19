package com.cloud.activity.client;

import com.cloud.activity.service.Activity1Service;
import com.cloud.common.parent.ClientController;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/client/activity1")
public class Activity1Client extends ClientController {
    Activity1Client () {
        noNeedLogin = Arrays.asList("getUserSupportInfo", "getSupportRankPage");
    }

    @Resource
    private Activity1Service activity1Service;

    @RequestMapping("/joinActivity")
    public void joinActivity () {
        List<String> imgList = postList("imgList", String.class);
        Long activityId = postLong("activityId");
        String want = postString("want", "");
        String remark = postString("remark", "");
        if (CommonUtil.isEmpty(activityId) || imgList == null || imgList.size() < 3 || imgList.size() > 9) {
            Res.fail(ErrorType.PARAM_ERR);
        }
        activity1Service.joinActivity(getUserId(), activityId, imgList, want, remark);
        Res.success();
    }

    @RequestMapping("/isSupport")
    public void isSupport () {
        Long friendId = postLong("friendId");
        Long activityId = postLong("activityId");
        if (CommonUtil.isEmpty(friendId) || CommonUtil.isEmpty(activityId)) {
            Res.fail(ErrorType.PARAM_ERR);
        }
        Res.success(activity1Service.isSupport(getUserId(), friendId, activityId));
    }

    @RequestMapping("/supportFriend")
    public void supportFriend () {
        Long friendId = postLong("friendId");
        Long activityId = postLong("activityId");
        if (CommonUtil.isEmpty(friendId) || CommonUtil.isEmpty(activityId)) {
            Res.fail(ErrorType.PARAM_ERR);
        }
        activity1Service.supportFriend(getUserId(), friendId, activityId);
        Res.success();
    }

    // 获取投票列表
    @RequestMapping("/getSupportRankPage")
    public void getSupportRankPage () {
        Integer current = postInt("current", 1);
        Long activityId = postLong("activityId");
        if (CommonUtil.isEmpty(activityId)) {
            Res.fail(ErrorType.PARAM_ERR);
        }
        Res.success(activity1Service.getSupportRankPage(activityId, current));
    }

    // 获取用户的投票信息
    @RequestMapping("/getUserSupportInfo")
    public void getUserSupportInfo () {
        Long userId = postLong("userId");
        Long activityId = postLong("activityId");
        if (CommonUtil.isEmpty(userId) || CommonUtil.isEmpty(activityId)) {
            Res.fail(ErrorType.PARAM_ERR);
        }
        Res.success(activity1Service.getUserSupportInfo(userId, activityId));
    }
}
