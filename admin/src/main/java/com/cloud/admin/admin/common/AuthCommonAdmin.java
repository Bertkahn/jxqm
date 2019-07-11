package com.cloud.admin.admin.common;

import com.cloud.admin.admin.AdminController;
import com.cloud.admin.service.AuthService;
import com.cloud.common.response.Res;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/auth/common")
public class AuthCommonAdmin extends AdminController {
    @Resource
    private AuthService authService;

    // 获取权限组
    @RequestMapping("/getMenu")
    public void commonGetMenu () {
        Res.success(authService.getMenu(getRules()));
    }
}
