package com.cloud.log.server;

import com.cloud.common.constant.RedisConst;
import com.cloud.common.parent.ServerController;
import com.cloud.common.redis.Redis;
import com.cloud.common.response.Res;
import com.cloud.common.util.IdUtil;
import com.cloud.common.util.TimeUtil;
import com.cloud.log.entity.AdminLog;
import com.cloud.log.entity.ApiLog;
import com.cloud.log.entity.SysLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/server/log")
public class LogServer extends ServerController {
    @Resource
    private Redis redis;

    @RequestMapping("/setSysLog")
    public void setSysLog () {
        SysLog sysLog = postObject("sysLog", SysLog.class);
        sysLog.setId(IdUtil.getId());
        sysLog.setCreateTime(TimeUtil.getTimeStamp());
        redis.leftPush(RedisConst.sysLogKey, sysLog);
        Res.success();
    }

    @RequestMapping("/setApiLog")
    public void setApiLog () {
        ApiLog apiLog = postObject("apiLog", ApiLog.class);
        apiLog.setId(IdUtil.getId());
        apiLog.setCreateTime(TimeUtil.getTimeStamp());
        redis.leftPush(RedisConst.apiLogKey, apiLog);
        Res.success();
    }

    @RequestMapping("/setAdminLog")
    public void setAdminLog () {
        AdminLog adminLog = postObject("adminLog", AdminLog.class);
        adminLog.setId(IdUtil.getId());
        adminLog.setCreateTime(TimeUtil.getTimeStamp());
        redis.leftPush(RedisConst.adminLogKey, adminLog);
        Res.success();
    }
}
