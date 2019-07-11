package com.cloud.common.redis;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.constant.TimeConst;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class Redis {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public void set (String key, Object value) {
        set(key, value, TimeConst.day);
    }

    public void set (String key, Object value, Integer time){
        String input;
        if (value instanceof String) {
            input = (String) value;
        } else {
            input = JSONObject.toJSONString(value);
        }
        try {
            redisTemplate.opsForValue().set(key, input, time, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get (String key) {
        try {
            return (String) redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            return null;
        }
    }

    public <T> T get (String key, Class<T> clazz) {
        try {
            String json = (String) redisTemplate.opsForValue().get(key);
            return JSONObject.parseObject(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    public void setExpire(String key, Integer time){
        try {
            redisTemplate.boundValueOps(key).expire(time, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Long getExpire(String key){
        try {
            return redisTemplate.boundValueOps(key).getExpire();
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(String key){
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Long leftPush(String key, Object value){
        try {
            return redisTemplate.opsForList().leftPush(key, value);
        } catch (Exception e) {
            return null;
        }
    }

    public Object leftPop(String key){
        try {
            return redisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            return null;
        }
    }

    public Long rightPush(String key,Object value){
        try {
            return redisTemplate.opsForList().rightPush(key, value);
        } catch (Exception e) {
            return null;
        }
    }

    public Object range(String key, int start, int end){
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            return null;
        }
    }

    public Object rightPop(String key){
        try {
            return redisTemplate.opsForList().rightPop(key);
        } catch (Exception e) {
            return null;
        }
    }

    public Object popIndex(String key,long index){
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            return null;
        }
    }

    public Long listSize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            return null;
        }
    }
}
