package com.cloud.common.feign.connect;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserConnectImpl implements UserConnect {
    @Override
    public String getUserAuthByToken(Map map) {
        return null;
    }

    @Override
    public String getUserAuthByUserId(Map map) {
        return null;
    }

    @Override
    public String getLevelList(Map map) {
        return null;
    }

    @Override
    public String getUserByUserId(Map map) {
        return null;
    }

    @Override
    public String getAddressById(Map map) {
        return null;
    }
}
