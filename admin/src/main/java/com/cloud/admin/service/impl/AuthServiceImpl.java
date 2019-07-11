package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.dao.AdminMapper;
import com.cloud.admin.dao.AuthGroupMapper;
import com.cloud.admin.dao.AuthMapper;
import com.cloud.admin.dao.AuthMenuMapper;
import com.cloud.admin.entity.Admin;
import com.cloud.admin.entity.Auth;
import com.cloud.admin.entity.AuthGroup;
import com.cloud.admin.entity.AuthMenu;
import com.cloud.admin.service.AuthService;
import com.cloud.common.dto.TableDto;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private AuthMapper authMapper;
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private AuthGroupMapper authGroupMapper;
    @Resource
    private AuthMenuMapper authMenuMapper;

    @Override
    public List getMenu(String rules) {
        List<Map<String, Object>> menuList;
        if (CommonUtil.isEmpty(rules)) {
            return new ArrayList();
        } else if (rules.equals("*")) {
            menuList = authMenuMapper.getMenuListByAuth(com.cloud.common.aop.Auth.masterAuthType);
            List<Map<String, Object>> masterMenuList = authMenuMapper.getMenuListByAuth(com.cloud.common.aop.Auth.platAuthType);
            if (CommonUtil.isNotEmpty(masterMenuList))
                menuList.addAll(masterMenuList);
        } else {
            String[] ruleList = rules.replaceAll("[0-9]", "").split(",");
            menuList = authMenuMapper.getMenuListByAliasList(ruleList);
            List<Map<String, Object>> findList = new ArrayList<>(menuList);
            boolean find;
            while (true) {
                find = false;
                List<Long> idList = new ArrayList<>();
                for (Map<String, Object> menu : findList) {
                    Long pid = Long.parseLong(menu.get("pid").toString());
                    if (CommonUtil.isNotEmpty(pid)) {
                        idList.add(pid);
                        find = true;
                    }
                }
                if (!find)
                    break;
                findList = authMenuMapper.getMenuListByIdList(idList);
                menuList.addAll(0, findList);
            }
        }
        return CommonUtil.listToTree(menuList, "id", "pid", "childlist");
    }

    @Override
    public void addAuth(String alias, String name, Long menuId, Integer authType) {
        Auth auth = authMapper.getAuthByAlias(alias);
        if (auth != null)
            Res.fail(ErrorType.ALIAS_EXIST);
        auth = new Auth();
        auth.setAlias(alias);
        auth.setName(name);
        auth.setAuthType(authType);
        auth.setMenuId(menuId);
        authMapper.insert(auth);
    }

    @Override
    public void editAuth(Long authId, String name, Long menuId) {
        Auth auth = new Auth();
        auth.setId(authId);
        auth.setName(name);
        auth.setMenuId(menuId);
        authMapper.updateById(auth);
    }

    @Override
    public void delAuth(Long authId) {
        authMapper.deleteById(authId);
    }

    @Override
    public Page getAuthPage(TableDto tableDto) {
        Page<Map> page = new Page<>(tableDto.getCurrent(), tableDto.getSize());
        page.setRecords(authMapper.getAuthList(tableDto, page));
        return page;
    }

    @Override
    public List getAuthGroupList(Integer authType) {
        return authGroupMapper.getAuthGroupList(authType);
    }

    @Override
    public List getMenuListByAuth(Integer authType) {
        return authMenuMapper.getSelectMenuListByAuth(authType, false);
    }

    @Override
    public List getSelectMenuTreeList(Integer authType) {
        List<Map<String, Object>> list = authMenuMapper.getSelectMenuListByAuth(authType, true);
        return CommonUtil.listToTreeList(list, "id", "pid");
    }

    @Override
    public List getMenuTreeList(TableDto tableDto) {
        List<Map<String, Object>> list = authMenuMapper.getAuthMenuList(tableDto);
        return CommonUtil.listToTreeList(list, "id", "pid");
    }

    @Override
    public void delMenu(Long menuId) {
        AuthMenu authMenu = authMenuMapper.getAuthMenuByPid(menuId);
        if (authMenu != null)
            Res.fail(ErrorType.HAS_CHILD);
        authMenuMapper.deleteById(menuId);
    }

    @Override
    public void addMenu(String name, String path, String icon, Long pid, Integer authType, String remark) {
        AuthMenu menu = new AuthMenu();
        menu.setName(name);
        menu.setPath(path);
        menu.setIcon(icon);
        menu.setPid(pid);
        menu.setAuthType(authType);
        menu.setRemark(remark);
        authMenuMapper.insert(menu);
    }

    @Override
    public void editMenu(Long menuId, String name, String path, String icon, Long pid, String remark) {
        AuthMenu menu = new AuthMenu();
        menu.setId(menuId);
        menu.setName(name);
        menu.setPath(path);
        menu.setIcon(icon);
        menu.setPid(pid);
        menu.setRemark(remark);
        authMenuMapper.updateById(menu);
    }

    @Override
    public Page getAuthGroupPage(TableDto tableDto) {
        Page<AuthGroup> page = new Page<>(tableDto.getCurrent(), tableDto.getSize());
        page.setRecords(authGroupMapper.getAuthGroupPage(tableDto, page));
        return page;
    }

    @Override
    public void addAuthGroup(String name, Integer authType, String rules, Long instId, String adminRules) {
        verifyRules(rules, adminRules);
        AuthGroup authGroup = new AuthGroup();
        authGroup.setName(name);
        authGroup.setAuthType(authType);
        authGroup.setRules(rules);
        authGroup.setInstId(instId);
        authGroupMapper.insert(authGroup);
    }

    @Override
    public void editAuthGroup(Long groupId, String name, String rules, String adminRules) {
        if (groupId == 1)
            Res.fail(ErrorType.SUCCESS);
        verifyRules(rules, adminRules);
        AuthGroup authGroup = new AuthGroup();
        authGroup.setId(groupId);
        authGroup.setName(name);
        authGroup.setRules(rules);
        authGroupMapper.updateById(authGroup);
    }

    @Override
    public void delAuthGroup(Long groupId) {
        if (groupId == 1)
            Res.fail(ErrorType.SUCCESS);
        Admin admin = adminMapper.getAdminByGroupId(groupId);
        if (admin != null)
            Res.fail(ErrorType.HAS_MEMBER);
        authGroupMapper.deleteById(groupId);
    }

    @Override
    public List getChooseAuthList(Integer authType, String adminRules) {
        List<Map<String, Object>> list = authMapper.getAuthListByAuthType(authType);
        System.out.println(list);
        if (adminRules.equals("*")) {
            for (Map<String, Object> auth : list) {
                auth.put("num", 30);
            }
        } else {
            String[] ruleList = adminRules.split(",");
            for (Map<String, Object> auth : list) {
                if (Integer.parseInt(auth.get("isSystem").toString()) == 0) {
                    auth.put("num", 30);
                    continue;
                }
                boolean has = false;
                for (String item : ruleList) {
                    if (item.contains(auth.get("alias").toString())) {
                        has = true;
                        auth.put("num", Integer.parseInt(item.replaceAll("[^0-9]", "")));
                        break;
                    }
                }
                if (!has)
                    auth.put("num", 0);
            }
        }
        return list;
    }

    private void verifyRules (String rules, String adminRules) {
        if (rules.equals("*"))
            Res.fail(ErrorType.SUCCESS);
        if (adminRules.equals("*"))
            return;
        String[] adminStringList = adminRules.split(",");
        List<Map<String, Object>> adminRuleList = new ArrayList<>();
        for (String str : adminStringList) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", str.replaceAll("[0-9]", ""));
            map.put("num", str.replaceAll("[^0-9]", ""));
            adminRuleList.add(map);
        }
        //
        String[] aliasList = rules.replaceAll("[0-9]", "").split(",");
        List<Map<String, Object>> authList = authMapper.getAuthByAliasListAndSystem(aliasList);
        //
        String[] ruleList = rules.split(",");
        for (String rule : ruleList) {
            String name = rule.replaceAll("[0-9]", "");
            Integer num = Integer.parseInt(rule.replaceAll("[^0-9]", ""));
            // 去掉非系统权限
            boolean isSystem = true;
            for (Map<String, Object> auth : authList) {
                if (auth.get("alias").equals(name) && Integer.parseInt(auth.get("isSystem").toString()) == 0) {
                    isSystem = false;
                    break;
                }
            }
            if (!isSystem)
                continue;
            // 验证权限
            boolean has = false;
            for (Map<String, Object> adminRule : adminRuleList) {
                if (adminRule.get("name").equals(name) && Integer.parseInt(adminRule.get("num").toString()) % num == 0) {
                    has = true;
                    break;
                }
            }
            if (!has)
                Res.fail(ErrorType.SUCCESS);
        }
    }
}