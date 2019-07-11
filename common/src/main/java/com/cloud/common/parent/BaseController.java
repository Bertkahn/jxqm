package com.cloud.common.parent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cloud.common.constant.RequestKeyConst;
import com.cloud.common.feign.LogFeign;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.response.ResException;
import com.cloud.common.response.ResModel;
import com.cloud.common.util.CommonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created  by sun on 2017/9/13.
 */
public class BaseController {
    @Value("${server.product}")
    private boolean product;

    @Resource
    private LogFeign logFeign;

    @ModelAttribute
    protected void init(){
        HttpServletRequest request = CommonUtil.getServletRequest();
        System.out.println("base");
    }
    /*=======================get ip=========================*/
    protected final String getIp(){
        return CommonUtil.getIp();
    }
    /*========================取参方法=========================*/
    protected final boolean isDebug () {
        if (product)
            return false;
        return getInt(RequestKeyConst.test) != null;
    }
    /*=======================post 方法=========================*/
    // clazz
    protected <T> T postObject (String key, Class<T> clazz) {
        return postObject(key, clazz, null);
    }
    protected <T> T postObject (String key, Class<T> clazz, T defaultVal) {
        if (isDebug())
            return getObject(key, clazz, defaultVal);
        try {
            T result = postParams().getObject(key, clazz);
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }

    }
    // list
    protected List<Object> postList (String key) {
        return postList(key, Object.class);
    }
    protected List<Object> postList (String key, List<Object> defaultVal) {
        return postList(key, Object.class, defaultVal);
    }

    protected <T> List<T> postList (String key, Class<T> clazz) {
        return postList(key, clazz, null);
    }

    protected <T> List<T> postList (String key, Class<T> clazz, List<T> defaultVal) {
        if (isDebug())
            return getList(key, clazz, defaultVal);
        try {
            JSONArray jsonArray = postParams().getJSONArray(key);
            if (jsonArray == null)
                return defaultVal;
            List<T> result = jsonArray.toJavaList(clazz);
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }

    }
    // map
    protected <K,V> Map<K,V> postMap (String key) {
        return postMap(key, null);
    }
    protected <K,V> Map<K,V> postMap (String key, Map<K,V> defaultVal) {
        if (isDebug())
            return getMap(key, defaultVal);
        try {
            Map<K,V> result = postParams().getObject(key, new TypeReference<Map<K, V>>(){});
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }

    }
    // bigDecimal
    protected BigDecimal postDecimal (String key) {
        return postDecimal(key, null);
    }
    protected BigDecimal postDecimal (String key, BigDecimal defaultVal) {
        if (isDebug())
            return getDecimal(key, defaultVal);
        try {
            BigDecimal result = postParams().getBigDecimal(key);
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }

    }
    // long
    protected Long postLong (String key) {
        return postLong(key, null);
    }
    protected Long postLong (String key, Long defaultVal) {
        if (isDebug())
            return getLong(key, defaultVal);
        try {
            Long result = postParams().getLong(key);
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }

    }
    // float
    protected Double postDouble (String key) {
        return postDouble(key, null);
    }
    protected Double postDouble (String key, Double defaultVal) {
        if (isDebug())
            return getDouble(key, defaultVal);
        try {
            Double result = postParams().getDouble(key);
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }
    }
    // float
    protected Float postFloat (String key) {
        return postFloat(key, null);
    }
    protected Float postFloat (String key, Float defaultVal) {
        if (isDebug())
            return getFloat(key, defaultVal);
        try {
            Float result = postParams().getFloat(key);
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }
    }
    // int
    protected Integer postInt (String key) {
        return postInt(key, null);
    }
    protected Integer postInt (String key, Integer defaultVal) {
        if (isDebug())
            return getInt(key, defaultVal);
        try {
            Integer result = postParams().getInteger(key);
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }

    }
    // string
    protected String postString (String key) {
        return postString(key, null);
    }
    protected String postString (String key, String defaultVal) {
        if (isDebug())
            return getString(key, defaultVal);
        try {
            String result = postParams().getString(key);
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }
    }
    /*=======================get 方法=========================*/
    // clazz
    protected <T> T getObject (String key, Class<T> clazz) {
        return getObject(key, clazz, null);
    }
    protected <T> T getObject (String key, Class<T> clazz, T defaultVal) {
        try {
            String json = getParams().getString(key);
            if (json == null)
                return defaultVal;
            T result = JSON.parseObject(json, clazz);
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }
    }
    // list
    protected List<Object> getList (String key) {
        return getList(key, Object.class);
    }
    protected List<Object> getList (String key, List<Object> defaultVal) {
        return getList(key, Object.class, defaultVal);
    }
    protected <T> List<T> getList (String key, Class<T> clazz) {
        return getList(key, clazz, null);
    }
    protected <T> List<T> getList (String key, Class<T> clazz, List<T> defaultVal) {
        try {
            String json = getParams().getString(key);
            if (json == null)
                return defaultVal;
            JSONArray jsonArray = JSON.parseArray(json);
            if (jsonArray == null)
                return defaultVal;
            List<T> result = jsonArray.toJavaList(clazz);
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }

    }
    // map
    protected <K,V> Map<K,V> getMap (String key) {
        return getMap(key, null);
    }
    protected <K,V> Map<K,V> getMap (String key, Map<K,V> defaultVal) {
        try {
            String json = getParams().getString(key);
            if (json == null)
                return defaultVal;
            Map<K, V> result = JSONObject.parseObject(json, new TypeReference<Map<K, V>>(){});
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }

    }
    // bigDecimal
    protected BigDecimal getDecimal (String key) {
        return getDecimal(key, null);
    }
    protected BigDecimal getDecimal (String key, BigDecimal defaultVal) {
        try {
            BigDecimal result = getParams().getBigDecimal(key);
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }
    }
    // long
    protected Long getLong (String key) {
        return getLong(key, null);
    }
    protected Long getLong (String key, Long defaultVal) {
        try {
            Long result = getParams().getLong(key);
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }
    }
    // double
    protected Double getDouble (String key) {
        return getDouble(key, null);
    }
    protected Double getDouble (String key, Double defaultVal) {
        try {
            Double result = getParams().getDouble(key);
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }
    }
    // float
    protected Float getFloat (String key) {
        return getFloat(key, null);
    }
    protected Float getFloat (String key, Float defaultVal) {
        try {
            Float result = getParams().getFloat(key);
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }

    }
    // int
    protected Integer getInt (String key) {
        return getInt(key, null);
    }
    protected Integer getInt (String key, Integer defaultVal) {
        try {
            Integer result = getParams().getInteger(key);
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }

    }
    // string
    protected String getString (String key) {
        return getString(key, null);
    }
    protected String getString (String key, String defaultVal) {
        try {
            String result = getParams().getString(key);
            if (result == null)
                return defaultVal;
            return result;
        }catch (Exception e) {
            return defaultVal;
        }

    }
    /*=======================系统获取param方法=========================*/
    private JSONObject getParams(){
        HttpServletRequest request = CommonUtil.getServletRequest();
        JSONObject getParams = (JSONObject) request.getAttribute(RequestKeyConst.getParam);
        if (getParams == null){
            String queryString = request.getQueryString();
            if (queryString == null) {
                queryString = "";
            }
            try {
                queryString = URLDecoder.decode(queryString, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (queryString == null){
                queryString = "";
            }
            getParams = (JSONObject) JSON.parse(JSON.toJSONString(CommonUtil.stringToMap(queryString)));
            if (getParams == null) {
                getParams = new JSONObject();
            }
            request.setAttribute(RequestKeyConst.getParam, getParams);
        }
        return getParams;
    }
    private JSONObject postParams(){
        HttpServletRequest request = CommonUtil.getServletRequest();
        JSONObject postParams = (JSONObject) request.getAttribute(RequestKeyConst.postParam);
        if (postParams == null){
            String json = getPostJson(request);
            postParams = (JSONObject) JSON.parse(json);
            request.setAttribute(RequestKeyConst.postParam, postParams);
        }
        return postParams;
    }
    private String getPostJson (HttpServletRequest request) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            Res.fail(ErrorType.UNSAFE_POST_DATA);
        }
        return null;
    }
    /*=======================ResultException处理器=========================*/
    @ResponseBody
    @ExceptionHandler
    public Object handleResultException(HttpServletRequest request, Exception exception) {
        if (exception instanceof ResException) {
            ResException resException = (ResException) exception;
            switch (resException.getType()){
                case ResException.TYPE_JSON:{//返回json
                    return new ResModel(resException.getCode(), resException.getData(), resException.getMsg());
                }
                case ResException.TYPE_VIEW:{//返回视图
                    return new ModelAndView(resException.getView());
                }
                case ResException.TYPE_REDIRECT:{//返回跳转
                    return new ModelAndView(resException.getRedirect());
                }
                case ResException.TYPE_ERROR:{//返回错误页面
                    // TODO 返回错误页面
                    return new ModelAndView(resException.getRedirect());
                }
                default:{
                    return "ret ok";
                }
            }
        } else {
            logFeign.setSysLog(request, exception);
            if (!product)
                exception.printStackTrace();
            return new ResModel(ErrorType.SERVER_ERR.getCode(), "", ErrorType.SERVER_ERR.getMsg());
        }
    }
}
