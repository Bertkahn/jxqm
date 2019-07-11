package com.cloud.common.feign.connect;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "admin", fallback = AdminConnectImpl.class)
public interface AdminConnect {
    String getAdminAuthByToken = "/server/admin/getAdminAuthByToken";
    String getAdminAuthByAdminId = "/server/admin/getAdminAuthByAdminId";
    @RequestMapping(value = getAdminAuthByToken, method = RequestMethod.POST, consumes = "application/json")
    String getAdminAuthByToken (@RequestBody Map map);
    @RequestMapping(value = getAdminAuthByAdminId, method = RequestMethod.POST, consumes = "application/json")
    String getAdminAuthByAdminId (@RequestBody Map map);

}