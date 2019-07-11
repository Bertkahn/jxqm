package com.cloud.common.feign;

import com.cloud.common.dto.AdminAuthDto;
import com.cloud.common.feign.connect.AdminConnect;
import com.cloud.common.parent.BaseFeign;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminFeign extends BaseFeign {
    @Resource
    private AdminConnect adminConnect;

    public AdminAuthDto getAdminAuthByToken (String token) {
        Map<String, Object> json = new HashMap<>();
        json.put("token", token);
        return parseResponse(adminConnect.getAdminAuthByToken(json), AdminAuthDto.class);
    }

    public AdminAuthDto getAdminAuthByAdminId (Long adminId) {
        Map<String, Object> json = new HashMap<>();
        json.put("adminId", adminId);
        return parseResponse(adminConnect.getAdminAuthByAdminId(json), AdminAuthDto.class);
    }


}
