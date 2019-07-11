package com.cloud.log.thread;

import com.cloud.common.constant.RedisConst;
import com.cloud.common.redis.Redis;
import com.cloud.common.util.CommonUtil;
import com.cloud.log.dao.AdminLogMapper;
import com.cloud.log.entity.AdminLog;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminLogThread extends Thread {
    @Resource
    private AdminLogMapper adminLogMapper;
    @Resource
    private Redis redis;
    private AdminLogThread() {
        start();
    }

    @Override
    public void run() {
        while (true) {
            int count = 0;
            List<AdminLog> list = new ArrayList<>();
            if (redis != null) {
                while (true) {
                    AdminLog log = (AdminLog) redis.rightPop(RedisConst.adminLogKey);
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
                    adminLogMapper.insertAdminLogList(list);
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
