package com.cloud.user.admin;

import com.cloud.common.aop.Auth;
import com.cloud.common.constant.AuthConst;
import com.cloud.common.parent.AdminController;
import com.cloud.common.response.Res;
import com.cloud.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/user")
public class UserAdmin extends AdminController {
    @Resource
    private UserService userService;

    // common
    // 我的客户，所有人都可以有
    @Auth(alias = AuthConst.common.myCustomer, level = Auth.query)
    @RequestMapping("/getMyCustomerPage")
    public void getMyCustomerPage () {
        Res.success(userService.getMyCustomerPage(getAdminId(), getTable()));
    }
}
