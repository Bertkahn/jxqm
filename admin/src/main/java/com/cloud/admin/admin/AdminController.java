package com.cloud.admin.admin;

import com.cloud.admin.service.AdminService;
import com.cloud.common.dto.AdminAuthDto;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;

public class AdminController extends com.cloud.common.parent.AdminController {
    @Resource
    private AdminService adminService;

    @Override
    @ModelAttribute
    protected void init(){
        super.init();
    }

    @Override
    protected AdminAuthDto getAdminAuthByAdminId(Long adminId) {
        return adminService.getAdminAuthByAdminId(adminId);
    }

    @Override
    protected AdminAuthDto getAdminAuthByToken(String token) {
        return adminService.getAdminAuthByToken(token);
    }
}
