package com.cloud.activity.admin;

import com.cloud.activity.service.Activity1Service;
import com.cloud.common.aop.Auth;
import com.cloud.common.constant.AuthConst;
import com.cloud.common.parent.AdminController;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/activity1")
public class Activity1Admin extends AdminController {
    @Resource
    private Activity1Service activity1Service;

    // plat
    // 参加活动的用户
    @Auth(alias = AuthConst.plat.activity1, level = Auth.query)
    @RequestMapping("/getActivityUserPage")
    public void getActivityUserPage () {
        List<String> paramList = Arrays.asList("status");
        Res.success(activity1Service.getActivityUserPage(getTable(paramList)));
    }

    // plat
    // 用户照片
    @Auth(alias = AuthConst.plat.activity1, level = Auth.query)
    @RequestMapping("/getActivityUserImgList")
    public void getActivityUserImgList () {
        Long userId = postLong("userId");
        if (CommonUtil.isEmpty(userId))
            Res.fail(ErrorType.PARAM_ERR);
        Res.success(activity1Service.getActivityUserImgList(userId));
    }

    // plat
    // 审核
    @Auth(alias = AuthConst.plat.activity1, level = Auth.edit)
    @RequestMapping("/verifyActivityUser")
    public void verifyActivityUser () {
        Long id = postLong("id");
        Integer status = postInt("status");
        if (CommonUtil.isEmpty(id) || !(status == 1 || status == 2))
            Res.fail(ErrorType.PARAM_ERR);
        activity1Service.verifyActivityUser(id, status);
        Res.success();
    }
}
