package com.cloud.common.parent;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;

import java.util.Map;

public class BaseFeign {
    protected <T> T parseResponse (String res, Class<T> clazz) {
        return parseResponse(res, clazz, true);
    }

    protected <T> T parseResponse (String res, Class<T> clazz, boolean shouldReturn) {
        if (res == null)
            Res.fail(ErrorType.SERVER_CONNECT_ERR);
        JSONObject jsonObject = JSONObject.parseObject(res);
        Integer code = jsonObject.getInteger("code");
        if (code != 0 && shouldReturn)
            Res.result(code, "", jsonObject.getString("msg"));
        return jsonObject.getObject("data", clazz);
    }
}
