package com.cloud.tool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.constant.RedisConst;
import com.cloud.common.constant.TimeConst;
import com.cloud.common.redis.Redis;
import com.cloud.common.util.CommonUtil;
import com.cloud.common.util.TimeUtil;
import com.cloud.tool.dao.EncodeParamMapper;
import com.cloud.tool.entity.EncodeParam;
import com.cloud.tool.service.ParamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class ParamServiceImpl implements ParamService {
    @Resource
    private EncodeParamMapper encodeParamMapper;
    @Resource
    private Redis redis;

    @Override
    public String encodeParam(Map param) {
        String json = JSONObject.toJSONString(param);
        String key = RedisConst.encodeParamKey + json;
        String token = redis.get(key);
        if (CommonUtil.isNotEmpty(token))
            return token;
        token = encodeParamMapper.getTokenByParamString(json);
        if (CommonUtil.isNotEmpty(token)) {
            redis.set(key, token, TimeConst.hour);
            return token;
        }
        EncodeParam encodeParam = new EncodeParam();
        encodeParam.setToken(CommonUtil.createToken());
        encodeParam.setParam(json);
        encodeParam.setCreateTime(TimeUtil.getTimeStamp());
        encodeParamMapper.insert(encodeParam);
        redis.set(key, encodeParam.getToken(), TimeConst.hour);
        return encodeParam.getToken();
    }

    @Override
    public Map decodeParam(String token) {
        EncodeParam encodeParam = encodeParamMapper.selectById(token);
        if (encodeParam == null)
            return new HashMap();
        return JSONObject.parseObject(encodeParam.getParam(), Map.class);
    }
}
