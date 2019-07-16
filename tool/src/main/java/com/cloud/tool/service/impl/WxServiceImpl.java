package com.cloud.tool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.constant.WxConst;
import com.cloud.common.util.HttpUtil;
import com.cloud.tool.service.WxService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WxServiceImpl implements WxService {
    @Override
    @SuppressWarnings("unchecked")
    public Map getWxMinOpenIdAndUnionId(String code) {
        Map<String, Object> param = new HashMap<>();
        param.put("appid", WxConst.minAppId);
        param.put("secret", WxConst.minSecret);
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        String json = HttpUtil.get("https://api.weixin.qq.com/sns/jscode2session", param);
        Map<String, Object> result = JSONObject.parseObject(json, Map.class);
        assert result != null;
        result.put("openId", result.get("openid"));
        // todo
        result.put("unionId", result.get("openid"));
        result.remove("openid");
        result.remove("unionid");
        return result;
    }
}
