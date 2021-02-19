package com.cloud.common.parent;
import com.cloud.common.constant.DeviceConst;
import com.cloud.common.constant.RequestKeyConst;
import com.cloud.common.constant.TimeConst;
import com.cloud.common.dto.UserAuthDto;
import com.cloud.common.feign.UserFeign;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import com.cloud.common.util.TimeUtil;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created  by sun on 2017/9/15.
 */
public class ClientController extends BaseController {
    @Resource
    private UserFeign userFeign;

    protected List<String> noNeedLogin;

    @Override
    @ModelAttribute
    protected void init(){
        super.init();
        HttpServletRequest request = CommonUtil.getServletRequest();
        setAuth(request);
        checkLogin(request);
    }

    protected final Long getUserId () {
        HttpServletRequest request = CommonUtil.getServletRequest();
        Long userId = (Long) request.getAttribute(RequestKeyConst.userId);
        if (userId == null) userId = 0L;
        return userId;
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
        Long userId = getUserId();
        if (CommonUtil.isEmpty(userId)) {
            Res.fail(ErrorType.UNSAFE_LOGIN_FIRST);
        }
    }

    /*===================   Auth   ==================*/
    protected void setAuth(HttpServletRequest request){
        Long userId = 0L;
        String rules = "";
        Integer device;
        if (isDebug()){
            userId = getLong(RequestKeyConst.userId);
            device = DeviceConst.web;
            if (CommonUtil.isNotEmpty(userId)) {
                UserAuthDto userAuthDto = getUserAuthByUserId(userId);
                rules = userAuthDto.getRules();
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
                UserAuthDto userAuthDto = getUserAuthByToken(token);
                userId = userAuthDto.getUserId();
                rules = userAuthDto.getRules();
            }
        }
        request.setAttribute(RequestKeyConst.device, device);
        request.setAttribute(RequestKeyConst.userId, userId);
        request.setAttribute(RequestKeyConst.authRule, rules);
    }

    /*===================   Data   ==================*/
    protected UserAuthDto getUserAuthByUserId (Long userId) {
        return userFeign.getUserAuthByUserId(userId);
    }
    protected UserAuthDto getUserAuthByToken (String token) {
        return userFeign.getUserAuthByToken(token);
    }
}
