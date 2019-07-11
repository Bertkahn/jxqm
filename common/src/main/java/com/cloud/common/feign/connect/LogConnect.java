package com.cloud.common.feign.connect;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "log", fallback = LogConnectImpl.class)
public interface LogConnect {
    String setSysLog = "/server/log/setSysLog";
    @RequestMapping(value = setSysLog, method = RequestMethod.POST, consumes = "application/json")
    void setSysLog (@RequestBody Map map);

    String setApiLog = "/server/log/setApiLog";
    @RequestMapping(value = setApiLog, method = RequestMethod.POST, consumes = "application/json")
    void setApiLog (@RequestBody Map map);

    String setAdminLog = "/server/log/setAdminLog";
    @RequestMapping(value = setAdminLog, method = RequestMethod.POST, consumes = "application/json")
    void setAdminLog (@RequestBody Map map);
}
