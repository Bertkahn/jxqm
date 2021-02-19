package com.cloud.admin.admin.master;

import com.cloud.admin.admin.AdminController;
import com.cloud.admin.service.AdminService;
import com.cloud.common.aop.Auth;
import com.cloud.common.constant.AuthConst;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/admin/master")
public class AdminMasterAdmin extends AdminController {

    @Resource
    private AdminService adminService;

    // 删除管理员
    @RequestMapping("/delAdmin")
    @Auth(alias = AuthConst.masterMasterAdmin, level = Auth.delete)
    public void delAdmin () {
        Long adminId = postLong("adminId");
        if (CommonUtil.isEmpty(adminId)) {
            Res.fail(ErrorType.PARAM_ERR);
        }
        adminService.delAdmin(adminId, Auth.masterAuthType);
        Res.success();
    }

    // 获取管理员
    @RequestMapping("/getAdminPage")
    @Auth(alias = AuthConst.masterMasterAdmin, level = Auth.query)
    public void getAdminPage () {
        List<String> paramList = Arrays.asList("trueName", "phone", "workId", "status", "createTime");
        Res.success(adminService.getAdminPage(Auth.masterAuthType, getTable(paramList)));
    }

    // 编辑管理员
    @RequestMapping("/editAdmin")
    @Auth(alias = AuthConst.masterMasterAdmin, level = Auth.edit)
    public void editAdmin () {
        Long adminId = postLong("id");
        String trueName = postString("trueName");
        String phone = postString("phone");
        String password = postString("password");
        Integer status = postInt("status", 0);
        Long groupId = postLong("groupId", 0L);
        if (CommonUtil.isEmpty(adminId) || CommonUtil.isEmpty(trueName) || CommonUtil.isEmpty(phone)) {
            Res.fail(ErrorType.PARAM_ERR);
        }
        if (CommonUtil.isNotEmpty(password) && !CommonUtil.checkString(password, 6, 16)) {
            Res.fail(ErrorType.PARAM_ERR);
        }
        if (getAdminId().equals(adminId)) {
            Res.fail(ErrorType.EDIT_SELF);
        }
        adminService.editAdmin(getAdminId(), adminId, 0L, trueName, phone, password, Auth.masterAuthType, groupId, status);
        Res.success();
    }

    // 添加管理员
    @RequestMapping("/addAdmin")
//    @Auth(alias = AuthConst.masterMasterAdmin, level = Auth.add)
    public void addAdmin () {
        String trueName = postString("trueName");
        String phone = postString("phone");
        String password = postString("password");
        Long groupId = postLong("groupId", 0L);
        if (!CommonUtil.checkPhone(phone) || !CommonUtil.checkString(password, 6, 16) || CommonUtil.isEmpty(trueName)) {
            Res.fail(ErrorType.PARAM_ERR);
        }
        adminService.addAdmin(0L, trueName, phone, password, Auth.masterAuthType, groupId);
        Res.success();
    }
}
