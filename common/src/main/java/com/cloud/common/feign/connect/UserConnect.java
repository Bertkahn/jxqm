package com.cloud.common.feign.connect;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "user", fallback = UserConnectImpl.class)
public interface UserConnect {
    String getUserAuthByToken = "/server/user/getUserAuthByToken";
    String getUserAuthByUserId = "/server/user/getUserAuthByUserId";
    String getUserByUserId = "/server/user/getUserByUserId";
    String getUserListByUserIdList = "/server/user/getUserListByUserIdList";
    @RequestMapping(value = getUserAuthByToken, method = RequestMethod.POST, consumes = "application/json")
    String getUserAuthByToken(@RequestBody Map map);

    @RequestMapping(value = getUserAuthByUserId, method = RequestMethod.POST, consumes = "application/json")
    String getUserAuthByUserId(@RequestBody Map map);

    @RequestMapping(value = getUserByUserId, method = RequestMethod.POST, consumes = "application/json")
    String getUserByUserId(@RequestBody Map map);

    @RequestMapping(value = getUserListByUserIdList, method = RequestMethod.POST, consumes = "application/json")
    String getUserListByUserIdList(@RequestBody Map map);

}