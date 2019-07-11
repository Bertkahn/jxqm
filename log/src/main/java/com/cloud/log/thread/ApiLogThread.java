package com.cloud.log.thread;

import com.cloud.common.constant.RedisConst;
import com.cloud.common.redis.Redis;
import com.cloud.common.util.CommonUtil;
import com.cloud.log.dao.ApiLogMapper;
import com.cloud.log.entity.ApiLog;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApiLogThread extends Thread {
    @Resource
    private ApiLogMapper apiLogMapper;
    @Resource
    private Redis redis;
    private ApiLogThread() {
        start();
    }

    @Override
    public void run() {
        while (true) {
            int count = 0;
            List<ApiLog> list = new ArrayList<>();
            if (redis != null) {
                while (true) {
                    ApiLog log = (ApiLog) redis.rightPop(RedisConst.apiLogKey);
                    if (log == null) {
                        break;
                    } else {
                        list.add(log);
                        count++;
                        if (count == 100)
                            break;
                    }
                }
                if (CommonUtil.isNotEmpty(list))
                    apiLogMapper.insertApiLogList(list);
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
