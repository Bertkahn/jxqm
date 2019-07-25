package com.cloud.common.feign.connect;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AdminConnectImpl implements AdminConnect {
    @Override
    public String getAdminAuthByToken(Map map) {
        return null;
    }

    @Override
    public String getAdminAuthByAdminId(Map map) {
        return null;
    }

    @Override
    public String getInstNameListByIdList(Map map) {
        return null;
    }
}
