package com.cloud.activity.admin;

import com.cloud.activity.service.ActivityFilesService;
import com.cloud.common.parent.AdminController;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author 倚楼无言
 * @date 2019/7/24 15:40
 */
@Controller
@RequestMapping("/admin/activityFiles")
public class ActivityFilesAdmin extends AdminController {

    @Resource
    private ActivityFilesService activityFilesService;
    //jiang
    //获取活动文件
    @RequestMapping("/getActivityFiles")
    public void getActivityFiles(){
        Long activityId = postLong("activityId");
        if(CommonUtil.isEmpty(activityId))
            Res.fail(ErrorType.PARAM_ERR);
        Res.success(activityFilesService.getActivityFiles(getTable(),activityId));
    }
    //jiang
    //修改活动文件
    @RequestMapping("/editActivityFiles")
    public void editActivityFiles(){
        Long id = postLong("id");
        String url = postString("url");
        Long activityId = postLong("activityId");
        String name = postString("name");
        if(CommonUtil.isEmpty(id) || CommonUtil.isEmpty(url) || CommonUtil.isEmpty(name) ||
                CommonUtil.isEmpty(activityId))
            Res.fail(ErrorType.PARAM_ERR);
        activityFilesService.editActivityFiles(id, url, activityId, name);
    }

}
