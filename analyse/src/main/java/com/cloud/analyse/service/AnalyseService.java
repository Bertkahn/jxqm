package com.cloud.analyse.service;

import java.util.Map;

public interface AnalyseService {
    // 返回openid和unionid
    Map getWxMinOpenIdAndUnionId (String code);
}
