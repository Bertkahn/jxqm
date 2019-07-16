package com.cloud.log.thread;

import com.cloud.common.constant.RedisConst;
import com.cloud.common.redis.Redis;
import com.cloud.common.util.CommonUtil;
import com.cloud.log.dao.SysLogMapper;
import com.cloud.log.entity.SysLog;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SysLogThread extends Thread {
    @Resource
    private SysLogMapper sysLogMapper;
    @Resource
    private Redis redis;
    private SysLogThread() {
        start();
    }

    @Override
    public void run() {
        while (true) {
            int count = 0;
            List<SysLog> list = new ArrayList<>();
            if (redis != null) {
                while (true) {
                    SysLog log = (SysLog) redis.rightPop(RedisConst.sysLogKey);
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
                    sysLogMapper.insertAll(list);
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
