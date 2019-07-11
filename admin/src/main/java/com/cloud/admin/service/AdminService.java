package com.cloud.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.dto.AdminAuthDto;
import com.cloud.common.dto.TableDto;

import java.util.List;
import java.util.Map;

public interface AdminService {
    void delAdmin(Long adminId, Integer authType);
    Page getAdminPage(Integer authType, TableDto tableDto);
    void editAdmin(Long tradeAdminId, Long adminId, Long instId, String trueName, String phone, String password, Integer authType, Long groupId, Integer status);
    void addAdmin(Long instId, String trueName, String phone, String password, Integer authType, Long groupId);
    Map login(String account, String password);
    Map changePassword(Long adminId, String password, String newPassword);
    AdminAuthDto getAdminAuthByToken(String token);
    AdminAuthDto getAdminAuthByAdminId(Long adminId);
    List getAdminAndCompanyList (List<Long> adminIdList);
    List getAdminListByIdList (List<Long> idList);
}
