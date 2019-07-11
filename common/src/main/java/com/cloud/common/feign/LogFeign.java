package com.cloud.common.feign;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.constant.RequestKeyConst;
import com.cloud.common.feign.connect.LogConnect;
import com.cloud.common.parent.BaseFeign;
import com.cloud.common.util.CommonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Service
public class LogFeign extends BaseFeign {

    @Resource
    private LogConnect logConnect;

    public void setSysLog (HttpServletRequest request, Exception exception) {
        String detail = "";
        Writer writer = null;
        PrintWriter printWriter = null;
        try {
            writer = new StringWriter();
            printWriter = new PrintWriter(writer);
            exception.printStackTrace(printWriter);
            detail = writer.toString();
        } finally {
            try {
                if (writer != null)
                    writer.close();
                if (printWriter != null)
                    printWriter.close();
            } catch (IOException e1) {
            }
        }
        Map<String, Object> sysLog = new HashMap<>();
        sysLog.put("url", request.getScheme() +"://" + request.getServerName() + ":" +request.getServerPort() + request.getServletPath());
        sysLog.put("userAgent", request.getHeader("User-Agent"));
        sysLog.put("msg", exception.getMessage());
        sysLog.put("detail", detail);
        sysLog.put("query", request.getQueryString());
        sysLog.put("param", ((JSONObject) request.getAttribute(RequestKeyConst.postParam)).toJSONString());
        sysLog.put("ip", CommonUtil.getIp());
        Map<String, Object> json = new HashMap<>();
        json.put("sysLog", sysLog);
        logConnect.setSysLog(json);
    }

    public void setApiLog (HttpServletRequest request, String remark) {
        Map<String, Object> apiLog = new HashMap<>();
        apiLog.put("userId", request.getAttribute(RequestKeyConst.userId));
        apiLog.put("token", request.getHeader(RequestKeyConst.token));
        apiLog.put("url", request.getScheme() +"://" + request.getServerName() + ":" +request.getServerPort() + request.getServletPath());
        apiLog.put("userAgent", request.getHeader("User-Agent"));
        apiLog.put("remark", remark);
        apiLog.put("query", request.getQueryString());
        apiLog.put("param", ((JSONObject) request.getAttribute(RequestKeyConst.postParam)).toJSONString());
        apiLog.put("ip", CommonUtil.getIp());
        Map<String, Object> json = new HashMap<>();
        json.put("apiLog", apiLog);
        logConnect.setApiLog(json);
    }

    public void setAdminLog (HttpServletRequest request, String remark) {
        Map<String, Object> adminLog = new HashMap<>();
        adminLog.put("adminId", request.getAttribute(RequestKeyConst.adminId));
        adminLog.put("token", request.getHeader(RequestKeyConst.token));
        adminLog.put("url", request.getScheme() +"://" + request.getServerName() + ":" +request.getServerPort() + request.getServletPath());
        adminLog.put("userAgent", request.getHeader("User-Agent"));
        adminLog.put("remark", remark);
        adminLog.put("query", request.getQueryString());
        adminLog.put("param", ((JSONObject) request.getAttribute(RequestKeyConst.postParam)).toJSONString());
        adminLog.put("ip", CommonUtil.getIp());
        Map<String, Object> json = new HashMap<>();
        json.put("adminLog", adminLog);
        logConnect.setAdminLog(json);
    }
}
