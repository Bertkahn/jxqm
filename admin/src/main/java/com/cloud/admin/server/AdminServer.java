package com.cloud.admin.server;

import com.cloud.admin.service.AdminService;
import com.cloud.common.parent.ServerController;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/server/admin")
public class AdminServer extends ServerController {
    @Resource
    private AdminService adminService;

    @RequestMapping("/getAdminAuthByToken")
    public void getAdminAuthByToken () {
        String token = postString("token");
        if (CommonUtil.isEmpty(token))
            Res.fail(ErrorType.SERVER_PARAM_ERR);
        Res.success(adminService.getAdminAuthByToken(token));
    }

    @RequestMapping("/getAdminAuthByAdminId")
    public void getAdminAuthByAdminId () {
        Long adminId = postLong("adminId");
        if (CommonUtil.isEmpty(adminId))
            Res.fail(ErrorType.SERVER_PARAM_ERR);
        Res.success(adminService.getAdminAuthByAdminId(adminId));
    }

    @RequestMapping("/getAdminAndCompanyList")
    public void getAdminAndCompanyList () {
        List<Long> adminIdList = postList("adminIdList", Long.class);
        if (CommonUtil.isEmpty(adminIdList))
            Res.success(new ArrayList<>());
        Res.success(adminService.getAdminAndCompanyList(adminIdList));
    }

    @RequestMapping("/getAdminListByIdList")
    public void getAdminListByIdList () {
        List<Long> idList = postList("idList", Long.class);
        if (CommonUtil.isEmpty(idList))
            Res.success(new ArrayList<>());
        Res.success(adminService.getAdminListByIdList(idList));
    }



}
