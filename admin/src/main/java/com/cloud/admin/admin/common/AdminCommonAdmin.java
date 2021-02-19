package com.cloud.admin.admin.common;

import com.cloud.admin.admin.AdminController;
import com.cloud.admin.service.AdminService;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Collections;

@Controller
@RequestMapping("/admin/admin/common")
public class AdminCommonAdmin extends AdminController {
    @Resource
    private AdminService adminService;

    AdminCommonAdmin () {
        noNeedLogin = Collections.singletonList("login");
    }

    // 登录
    @RequestMapping("/login")
    public void login () {
        String account = postString("account");
        String password = postString("password");
        if (CommonUtil.isEmpty(account) || !CommonUtil.checkString(password, 6, 16)) {
            Res.fail(ErrorType.PARAM_ERR);
        }
        Res.success(adminService.login(account, password));
    }

    // 修改登录密码
    @RequestMapping("/changePassword")
    public void changePassword () {
        String password = postString("password");
        String newPassword = postString("newPassword");
        if (!CommonUtil.checkString(newPassword, 6, 16) || !CommonUtil.checkString(password, 6, 16)) {
            Res.fail(ErrorType.PARAM_ERR);
        }
        Res.success(adminService.changePassword(getAdminId(), password, newPassword));
    }
}
