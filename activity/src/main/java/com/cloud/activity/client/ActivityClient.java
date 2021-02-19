package com.cloud.activity.client;

import com.cloud.activity.service.ActivityService;
import com.cloud.common.parent.ClientController;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Arrays;

@Controller
@RequestMapping("/client/activity")
public class ActivityClient extends ClientController {
    ActivityClient () {
        noNeedLogin = Arrays.asList("getActivityInfo");
    }

    @Resource
    private ActivityService activityService;

    // 用户是否参加活动
    @RequestMapping("/getUserIsJoin")
    public void getUserIsJoin () {
        Long activityId = postLong("activityId");
        if (CommonUtil.isEmpty(activityId)) {
            Res.fail(ErrorType.PARAM_ERR);
        }
        Res.success(activityService.getUserIsJoin(getUserId(), activityId));
    }

    //
    @RequestMapping("/getActivityInfo")
    public void getActivityInfo () {
        Long activityId = postLong("activityId");
        if (CommonUtil.isEmpty(activityId)) {
            Res.fail(ErrorType.PARAM_ERR);
        }
        Res.success(activityService.getActivityInfo(activityId));
    }
}
