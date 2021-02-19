package com.cloud.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.admin.entity.Auth;
import com.cloud.common.dto.TableDto;

import java.util.List;

public interface AuthService extends IService<Auth> {
    List getMenu(String rules);

    void addAuth(String alias, String name, Long menuId, Integer authType);

    void editAuth(Long authId, String name, Long menuId);

    void delAuth(Long authId);

    Page getAuthPage(TableDto tableDto);

    List getAuthGroupList(Integer authType);

    List getMenuListByAuth(Integer authType);

    List getSelectMenuTreeList(Integer authType);

    List getMenuTreeList(TableDto tableDto);

    void delMenu(Long menuId);

    void addMenu(String name, String path, String icon, Long pid, Integer authType, String remark);

    void editMenu(Long menuId, String name, String path, String icon, Long pid, String remark);

    Page getAuthGroupPage(TableDto tableDto);

    void addAuthGroup(String name, Integer authType, String rules, Long instId, String adminRules);

    void editAuthGroup(Long groupId, String name, String rules, String adminRules);

    void delAuthGroup(Long groupId);

    List getChooseAuthList(Integer authType, String adminRules);
}
