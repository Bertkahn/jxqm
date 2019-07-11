package com.cloud.common.aop;

import com.cloud.common.constant.RequestKeyConst;
import com.cloud.common.feign.LogFeign;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthAspect {
    @Resource
    private LogFeign logFeign;

    @Pointcut("@annotation(com.cloud.common.aop.Auth)")
    public void auth(){

    }

    @Before("auth()")
    public void beforeAuth(JoinPoint point)
    {
        HttpServletRequest request = CommonUtil.getServletRequest();
        String authStr = (String) request.getAttribute(RequestKeyConst.authRule);
        if (CommonUtil.isEmpty(authStr))
            Res.fail(ErrorType.ADMIN_NO_AUTH);
        if (authStr.equals("*"))
            return;
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Auth auth = methodSignature.getMethod().getAnnotation(Auth.class);
        if (auth.level() > Auth.query) {
            logFeign.setAdminLog(request, "");
        }
        String[] authNameList = auth.alias();
        String[] authStrArr = authStr.split(",");
        for (String item : authStrArr) {
            for (String authName : authNameList) {
                if (item.contains(authName)) {
                    int authLevel = Integer.parseInt(item.replaceAll("[^0-9]", ""));
                    if (authLevel == 0)
                        Res.fail(ErrorType.ADMIN_NO_AUTH);
                    if (authLevel % auth.level() == 0)
                        return;
                }
            }
        }
        Res.fail(ErrorType.ADMIN_NO_AUTH);
    }
}
