package com.cloud.user.service;

import java.util.Map;

public interface WxService {
    // 返回openid和unionid
    Map getWxMinOpenIdAndUnionId (String code);
}
