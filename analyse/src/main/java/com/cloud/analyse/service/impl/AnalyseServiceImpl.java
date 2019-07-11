package com.cloud.analyse.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.constant.WxConst;
import com.cloud.common.util.HttpUtil;
import com.cloud.analyse.service.AnalyseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AnalyseServiceImpl implements AnalyseService {
    @Override
    public Map getWxMinOpenIdAndUnionId(String code) {
        Map<String, Object> param = new HashMap<>();
        param.put("appid", WxConst.minAppId);
        param.put("secret", WxConst.minSecret);
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        String json = HttpUtil.get("https://api.weixin.qq.com/sns/jscode2session", param);
        return JSONObject.parseObject(json, Map.class);
    }
}