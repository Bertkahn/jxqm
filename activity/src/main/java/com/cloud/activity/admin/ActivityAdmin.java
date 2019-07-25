package com.cloud.activity.admin;

import com.cloud.activity.service.ActivityService;
import com.cloud.common.aop.Auth;
import com.cloud.common.constant.AuthConst;
import com.cloud.common.parent.AdminController;
import com.cloud.common.response.Res;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author 倚楼无言
 * @date 2019/7/23 15:15
 */
@Controller
@RequestMapping("/admin/activity")
public class ActivityAdmin extends AdminController {

    @Resource
    private ActivityService activityService;
    // jiang
    // plat
    // 活动管理分页
    @Auth(alias = AuthConst.plat.activityList, level = Auth.query)
    @RequestMapping("/plat/getActivityPage")
    public void getActivityPagePlat(){
        List<String> paramList = Arrays.asList("instId", "status", "type", "name", "startTime", "endTime");
        Res.success(activityService.getActivityPage(getTable(paramList)));
    }

    // jiang
    // inst
    // 活动管理分页
    @Auth(alias = AuthConst.inst.activityList, level = Auth.query)
    @RequestMapping("/inst/getActivityPage")
    public void getActivityPageInst(){
//        List<String> paramList = Arrays.asList("status", "type", "name", "startTime", "endTime");
//        Res.success(activityService.getActivityPage(getTable(paramList)));
    }

}
