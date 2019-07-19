package com.cloud.admin.admin.master;

import com.cloud.admin.admin.AdminController;
import com.cloud.admin.service.AuthService;
import com.cloud.common.aop.Auth;
import com.cloud.common.constant.AuthConst;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/auth/master")
public class AuthMasterAdmin extends AdminController {
    @Resource
    private AuthService authService;

    // 管理员权限编辑时获取权限组
    @RequestMapping("/getAuthGroupList")
    @Auth(alias = AuthConst.masterMasterAdmin, level = Auth.query)
    public void getAuthGroupList () {
        Res.success(authService.getAuthGroupList(Auth.masterAuthType));
    }

    // 权限规则列表
    @RequestMapping("/getAuthPage")
    @Auth(alias = AuthConst.masterAuth, level = Auth.query)
    public void getAuthPage () {
        List<String> paramList = Arrays.asList("alias", "name", "menuName", "authType");
        Res.success(authService.getAuthPage(getTable(paramList)));
    }

    // 添加权限规则
    @RequestMapping("/addAuth")
    @Auth(alias = AuthConst.masterAuth, level = Auth.add)
    public void addAuth () {
        String alias = postString("alias");
        String name = postString("name");
        Long menuId = postLong("menuId", 0L);
        Integer authType = postInt("authType");
        if (CommonUtil.isEmpty(alias) || CommonUtil.isEmpty(name) || CommonUtil.isEmpty(authType))
            Res.fail(ErrorType.PARAM_ERR);
        authService.addAuth(alias, name, menuId, authType);
        Res.success();
    }

    // 编辑权限规则
    @RequestMapping("/editAuth")
    @Auth(alias = AuthConst.masterAuth, level = Auth.edit)
    public void editAuth () {
        Long authId = postLong("id");
        String name = postString("name");
        Long menuId = postLong("menuId", 0L);
        if (CommonUtil.isEmpty(authId) || CommonUtil.isEmpty(name))
            Res.fail(ErrorType.PARAM_ERR);
        authService.editAuth(authId, name, menuId);
        Res.success();
    }

    // 权限规则对应菜单
    @RequestMapping("/getMenuListByAuth")
    @Auth(alias = AuthConst.masterAuth, level = Auth.query)
    public void getMenuListByAuth () {
        Integer authType = postInt("authType");
        if (CommonUtil.isEmpty(authType))
            Res.success(new ArrayList<>());
        Res.success(authService.getMenuListByAuth(authType));
    }

    // 删除权限规则
    @RequestMapping("/delAuth")
    @Auth(alias = AuthConst.masterAuth, level = Auth.delete)
    public void delAuth () {
        Long authId = postLong("authId");
        if (CommonUtil.isEmpty(authId))
            Res.fail(ErrorType.PARAM_ERR);
        authService.delAuth(authId);
        Res.success();
    }

    // 菜单列表
    @RequestMapping("/getMenuTreeList")
    @Auth(alias = AuthConst.masterAuthMenu, level = Auth.query)
    public void getMenuTreeList () {
        List<String> paramList = Arrays.asList("path", "name", "authType");
        Res.success(authService.getMenuTreeList(getTable(paramList)));
    }

    // 选择父级菜单列表
    @RequestMapping("/getSelectMenuTreeList")
    @Auth(alias = AuthConst.masterAuthMenu, level = Auth.query)
    public void getSelectMenuTreeList () {
        Integer authType = postInt("authType");
        if (CommonUtil.isEmpty(authType))
            Res.success(new ArrayList<>());
        Res.success(authService.getSelectMenuTreeList(authType));
    }

    // 添加菜单
    @RequestMapping("/addMenu")
    @Auth(alias = AuthConst.masterAuthMenu, level = Auth.add)
    public void addMenu () {
        String name = postString("name");
        String path = postString("path", "");
        String icon = postString("icon");
        Long pid = postLong("pid", 0L);
        Integer authType = postInt("authType");
        String remark = postString("remark", "");
        if (CommonUtil.isEmpty(name) || CommonUtil.isEmpty(authType))
            Res.fail(ErrorType.PARAM_ERR);
        authService.addMenu(name, path, icon, pid, authType, remark);
        Res.success();
    }

    // 编辑菜单
    @RequestMapping("/editMenu")
    @Auth(alias = AuthConst.masterAuthMenu, level = Auth.edit)
    public void editMenu () {
        Long menuId = postLong("id");
        String name = postString("name");
        String path = postString("path", "");
        String icon = postString("icon");
        Long pid = postLong("pid", 0L);
        String remark = postString("remark", "");
        if (CommonUtil.isEmpty(menuId) || CommonUtil.isEmpty(name))
            Res.fail(ErrorType.PARAM_ERR);
        authService.editMenu(menuId, name, path, icon, pid, remark);
        Res.success();
    }

    // 删除菜单
    @RequestMapping("/delMenu")
    @Auth(alias = AuthConst.masterAuthMenu, level = Auth.delete)
    public void delMenu () {
        Long menuId = postLong("menuId");
        if (CommonUtil.isEmpty(menuId))
            Res.fail(ErrorType.PARAM_ERR);
        authService.delMenu(menuId);
        Res.success();
    }

    // 权限组列表
    @RequestMapping("/getAuthGroupPage")
    @Auth(alias = AuthConst.masterAuthGroup, level = Auth.query)
    public void getAuthGroupPage () {
        List<String> paramList = Arrays.asList("name", "authType");
        Res.success(authService.getAuthGroupPage(getTable(paramList)));
    }

    // 添加权限组
    @RequestMapping("/addAuthGroup")
    @Auth(alias = AuthConst.masterAuthGroup, level = Auth.add)
    public void addAuthGroup () {
        String name = postString("name");
        Integer authType = postInt("authType");
        String rules = postString("rules");
        if (CommonUtil.isEmpty(name) || CommonUtil.isEmpty(authType) || CommonUtil.isEmpty(rules))
            Res.fail(ErrorType.PARAM_ERR);
        authService.addAuthGroup(name, authType, rules, 0L, getRules());
        Res.success();
    }

    // 编辑权限组
    @RequestMapping("/editAuthGroup")
    @Auth(alias = AuthConst.masterAuthGroup, level = Auth.edit)
    public void editAuthGroup () {
        Long groupId = postLong("id");
        String name = postString("name");
        String rules = postString("rules");
        if (CommonUtil.isEmpty(name) || CommonUtil.isEmpty(groupId) || CommonUtil.isEmpty(rules))
            Res.fail(ErrorType.PARAM_ERR);
        authService.editAuthGroup(groupId, name, rules, getRules());
        Res.success();
    }

    // 删除权限组
    @RequestMapping("/delAuthGroup")
    @Auth(alias = AuthConst.masterAuthGroup, level = Auth.delete)
    public void delAuthGroup () {
        Long groupId = postLong("groupId");
        if (CommonUtil.isEmpty(groupId))
            Res.fail(ErrorType.PARAM_ERR);
        authService.delAuthGroup(groupId);
        Res.success();
    }

    // 获取可选权限
    @RequestMapping("/getChooseAuthList")
    @Auth(alias = AuthConst.masterAuthGroup, level = Auth.query)
    public void getChooseAuthList () {
        Integer authType = postInt("authType");
        if (CommonUtil.isEmpty(authType))
            Res.fail(ErrorType.PARAM_ERR);
        Res.success(authService.getChooseAuthList(authType, getRules()));
    }
}
