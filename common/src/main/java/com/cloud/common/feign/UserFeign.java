package com.cloud.common.feign;

import com.cloud.common.dto.UserAuthDto;
import com.cloud.common.feign.connect.UserConnect;
import com.cloud.common.parent.BaseFeign;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserFeign extends BaseFeign {
    @Resource
    private UserConnect userConnect;

    public UserAuthDto getUserAuthByToken (String token) {
        Map<String, Object> json = new HashMap<>();
        json.put("token", token);
        return parseResponse(userConnect.getUserAuthByToken(json), UserAuthDto.class);
    }

    public UserAuthDto getUserAuthByUserId (Long userId) {
        Map<String, Object> json = new HashMap<>();
        json.put("userId", userId);
        return parseResponse(userConnect.getUserAuthByUserId(json), UserAuthDto.class);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getUserByUserId (Long userId) {
        Map<String, Object> json = new HashMap<>();
        json.put("userId", userId);
        return parseResponse(userConnect.getUserByUserId(json), Map.class);
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getUserListByUserIdList (List<Long> userIdList) {
        Map<String, Object> json = new HashMap<>();
        json.put("idList", userIdList);
        return parseResponse(userConnect.getUserListByUserIdList(json), List.class);
    }

}
