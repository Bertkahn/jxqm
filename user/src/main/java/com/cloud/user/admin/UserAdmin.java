package com.cloud.user.admin;

import com.cloud.common.aop.Auth;
import com.cloud.common.constant.AuthConst;
import com.cloud.common.parent.AdminController;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.user.entity.User;
import com.cloud.user.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class UserAdmin extends AdminController {
    @Resource
    private UserService userService;

    // common
    // 我的客户，所有人都可以有
    @Auth(alias = AuthConst.common.myCustomer, level = Auth.query)
    @RequestMapping("/getMyCustomerPage")
    public void getMyCustomerPage() {
        List<String> paramList = Arrays.asList("trueName", "phone", "nickName");
        Res.success(userService.getMyCustomerPage(getAdminId(), getTable(paramList)));
    }

    @RequestMapping("/addMyUser")
    public void addMyUser() {
        String trueName = postString("trueName");
        String phone = postString("phone");
        String nickName = postString("nickName");
        Integer age = postInt("age");
        Integer sex = postInt("sex");
        if (trueName == null || phone == null) {
            Res.fail(ErrorType.PARAM_ERR);
        }
        userService.addMyUser(getAdminId(), trueName, phone, nickName, age, sex);
        Res.success();
    }

    @RequestMapping("/updateMyUser")
    public void updateMyUser() {
        Long id = postLong("id");
        String trueName = postString("trueName");
        String phone = postString("phone");
        String nickName = postString("nickName");
        Integer age = postInt("age");
        Integer sex = postInt("sex");
        if (trueName == null || phone == null) {
            Res.fail(ErrorType.PARAM_ERR);
        }
        userService.updateMyUser(id, trueName, phone, nickName, age, sex);
        Res.success();
    }

    @RequestMapping("/delMyUser")
    public void delMyUser() {
        Long id = postLong("id");
        if (id == null) {
            Res.fail(ErrorType.NOT_EXIST);
        }
        userService.delMyUser(id);
        Res.success();
    }

}
