package com.cloud.common.parent;

import com.cloud.common.constant.DeviceConst;
import com.cloud.common.constant.RequestKeyConst;
import com.cloud.common.constant.TimeConst;
import com.cloud.common.dto.AdminAuthDto;
import com.cloud.common.dto.TableDto;
import com.cloud.common.feign.AdminFeign;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import com.cloud.common.util.TimeUtil;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class AdminController extends BaseController {
    @Resource
    private AdminFeign adminFeign;

    protected List<String> noNeedLogin;

    @ModelAttribute
    protected void init(){
        super.init();
        HttpServletRequest request = CommonUtil.getServletRequest();
        setAuth(request);
        checkLogin(request);
    }

    protected TableDto getTable () {
        return getTable(new ArrayList<>());
    }

    protected TableDto getTable (List<String> paramList) {
        TableDto tableDto = postObject(RequestKeyConst.tableForm, TableDto.class);
        if (tableDto == null)
            return new TableDto();
        if (CommonUtil.isNotEmpty(tableDto.getSort())) {
            if (!paramList.contains(tableDto.getSort()))
                tableDto.setSort(null);
            if (!(tableDto.getSortType().equals("asc") || tableDto.getSortType().equals("desc")))
                tableDto.setSort(null);
        } else {
            tableDto.setSort(null);
        }
        List<String> symbolList = Arrays.asList("like", "between", "in", ">", "=", "<", ">=", "<=", "<>");
        List<Map<String, Object>> searchList = tableDto.getSearch();
        searchList.removeIf(item -> (!paramList.contains(item.get("key").toString()) || !symbolList.contains(item.get("symbol").toString())));
        return tableDto;
    }

    protected final Long getAdminId () {
        HttpServletRequest request = CommonUtil.getServletRequest();
        Long adminId = (Long) request.getAttribute(RequestKeyConst.adminId);
        if (adminId == null) adminId = 0L;
        return adminId;
    }

    protected final Long getInstId () {
        HttpServletRequest request = CommonUtil.getServletRequest();
        Long instId = (Long) request.getAttribute(RequestKeyConst.instId);
        if (instId == null) instId = 0L;
        return instId;
    }

    protected final Integer getAuthType () {
        HttpServletRequest request = CommonUtil.getServletRequest();
        Integer authType = (Integer) request.getAttribute(RequestKeyConst.authType);
        if (authType == null) authType = 0;
        return authType;
    }

    protected final String getRules () {
        HttpServletRequest request = CommonUtil.getServletRequest();
        String rules = (String) request.getAttribute(RequestKeyConst.authRule);
        if (rules == null) rules = "";
        return rules;
    }

    protected final Integer getDevice () {
        HttpServletRequest request = CommonUtil.getServletRequest();
        return (Integer) request.getAttribute(RequestKeyConst.device);
    }

    /*===================   Login   ==================*/
    protected void checkLogin (HttpServletRequest request) {
        if (!CommonUtil.isEmpty(noNeedLogin)) {
            if (noNeedLogin.contains("*")) {
                return;
            }
            String[] uri = request.getServletPath().split("/");
            String path = uri[uri.length - 1];
            if (noNeedLogin.contains(path)) {
                return;
            }
        }
        Long adminId = getAdminId();
        if (CommonUtil.isEmpty(adminId)) {
            Res.fail(ErrorType.UNSAFE_LOGIN_FIRST);
        }
    }

    /*===================   Auth   ==================*/
    protected void setAuth(HttpServletRequest request){
        Long adminId = 0L;
        Long instId = 0L;
        Integer authType = 0;
        String rules = "";
        Integer device;
        if (isDebug()){
            adminId = getLong(RequestKeyConst.adminId);
            device = DeviceConst.web;
            if (CommonUtil.isNotEmpty(adminId)) {
                AdminAuthDto adminAuthDto = getAdminAuthByAdminId(adminId);
                instId = adminAuthDto.getInstId();
                rules = adminAuthDto.getRules();
                authType = adminAuthDto.getAuthType();
            }
        }else {
            String token = request.getHeader(RequestKeyConst.token);
            device = postInt(RequestKeyConst.device);
            Integer time = postInt("time");
            if (CommonUtil.isEmpty(device) || CommonUtil.isEmpty(time))
                Res.fail(ErrorType.UNSAFE_EMPTY);
            if (Math.abs(time - TimeUtil.getTimeStamp()) > TimeConst.minute) {
                Res.fail(ErrorType.UNSAFE_TIME);
            }
            if (CommonUtil.isNotEmpty(token)) {
                AdminAuthDto adminAuthDto = getAdminAuthByToken(token);
                adminId = adminAuthDto.getAdminId();
                instId = adminAuthDto.getInstId();
                rules = adminAuthDto.getRules();
                authType = adminAuthDto.getAuthType();
            }
        }
        request.setAttribute(RequestKeyConst.device, device);
        request.setAttribute(RequestKeyConst.adminId, adminId);
        request.setAttribute(RequestKeyConst.instId, instId);
        request.setAttribute(RequestKeyConst.authRule, rules);
        request.setAttribute(RequestKeyConst.authType, authType);
    }

    /*===================   Data   ==================*/
    protected AdminAuthDto getAdminAuthByAdminId (Long adminId) {
        return adminFeign.getAdminAuthByAdminId(adminId);
    }
    protected AdminAuthDto getAdminAuthByToken (String token) {
        return adminFeign.getAdminAuthByToken(token);
    }
}
