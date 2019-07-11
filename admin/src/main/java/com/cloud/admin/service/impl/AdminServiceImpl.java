package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.dao.AuthGroupMapper;
import com.cloud.common.constant.RedisConst;
import com.cloud.common.constant.TimeConst;
import com.cloud.common.dto.AdminAuthDto;
import com.cloud.common.dto.TableDto;
import com.cloud.common.redis.Redis;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.admin.dao.AdminMapper;
import com.cloud.admin.entity.Admin;
import com.cloud.admin.service.AdminService;
import com.cloud.common.util.CommonUtil;
import com.cloud.common.util.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    private final String pwdPre = "da%1!@daq";
    private final String pwdAfter = "12!2e3#";
    private final Integer expire = TimeConst.hour * 4;
    @Resource
    private Redis redis;
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private AuthGroupMapper authGroupMapper;

    @Override
    public void delAdmin(Long adminId, Integer authType) {
        if (adminId == 1)
            Res.fail(ErrorType.SUCCESS);
        adminMapper.delAdminByIdAndAuthType(adminId, authType);
    }

    @Override
    public Page getAdminPage(Integer authType, TableDto tableDto) {
        Page<Map> page = new Page<>(tableDto.getCurrent(), tableDto.getSize());
        page.setRecords(adminMapper.getAdminList(authType, tableDto, page));
        return page;
    }

    @Override
    public void editAdmin(Long tradeAdminId, Long adminId, Long instId, String trueName, String phone, String password, Integer authType, Long groupId, Integer status) {
        if (adminId == 1 || groupId == 1)
            Res.fail(ErrorType.SUCCESS);
        Admin admin = adminMapper.selectById(adminId);
        if (admin == null)
            Res.fail(ErrorType.NOT_EXIST);
        // 清除redis
        redis.delete(RedisConst.adminToken + admin.getToken());
        admin.setInstId(instId);
        admin.setTrueName(trueName);
        admin.setPhone(phone);
        admin.setAuthType(authType);
        if (!tradeAdminId.equals(adminId))
            admin.setGroupId(groupId);
        admin.setStatus(status);
        if (CommonUtil.isNotEmpty(password)) {
            admin.setPassword(CommonUtil.encryptPassword(password, pwdPre, pwdAfter));
            admin.setToken(CommonUtil.createToken());
        }
        adminMapper.updateById(admin);
    }

    @Override
    public void addAdmin(Long instId, String trueName, String phone, String password, Integer authType, Long groupId) {
        if (groupId == 1)
            Res.fail(ErrorType.SUCCESS);
        Admin admin = adminMapper.getAdminByPhone(phone);
        if (admin != null)
            Res.fail(ErrorType.PHONE_EXIST);
        admin = new Admin();
        admin.setInstId(instId);
        admin.setTrueName(trueName);
        admin.setPhone(phone);
        admin.setPassword(CommonUtil.encryptPassword(password, pwdPre, pwdAfter));
        // 工号
        String redisKey = "workId";
        String workId;
        Integer num;
        num = redis.get(redisKey, Integer.class);
        if (CommonUtil.isEmpty(num)) num = Integer.parseInt(adminMapper.getLastAdmin().getWorkId()) % 1000;
        num++;
        if (num < 10) workId = "00" + num;
        else if (num < 100) workId = "0" + num;
        else if (num < 1000) workId = num.toString();
        else workId = "001";
        workId = (new java.text.SimpleDateFormat("yyMMdd")).format(new Date()) + workId;
        // end
        admin.setWorkId(workId);
        admin.setToken(CommonUtil.createToken());
        admin.setAuthType(authType);
        admin.setStatus(1);
        admin.setResetPassword(1);
        admin.setGroupId(groupId);
        admin.setCreateTime(TimeUtil.getTimeStamp());
        adminMapper.insert(admin);
        // 存入redis
        redis.set(redisKey, num, TimeConst.day);
    }

    @Override
    public Map login(String account, String password) {
        Admin admin;
        if (CommonUtil.checkPhone(account)) {
            admin = adminMapper.getAdminByPhone(account);
        } else {
            admin = adminMapper.getAdminByWorkId(Integer.parseInt(account));
        }
        if (admin == null || !admin.getPassword().equals(CommonUtil.encryptPassword(password, pwdPre, pwdAfter)))
            Res.fail(ErrorType.LOGIN_FAIL);
        // 判断状态
        if (admin.getStatus() != 1)
            Res.fail(ErrorType.ACCOUNT_CLOSE);
        // 清除redis
        redis.delete(RedisConst.adminToken + admin.getToken());
        // 更新token
        admin.setToken(CommonUtil.createToken());
        adminMapper.updateById(admin);
        // 缓存redis
        String key = RedisConst.adminToken + admin.getToken();
        String rules = authGroupMapper.getRulesByGroupId(admin.getGroupId());
        AdminAuthDto adminAuthDto = new AdminAuthDto();
        adminAuthDto.setAdminId(admin.getId());
        adminAuthDto.setInstId(admin.getInstId());
        adminAuthDto.setAuthType(admin.getAuthType());
        adminAuthDto.setRules(rules == null ? "" : rules);
        redis.set(key, adminAuthDto, expire);
        // 返回数据
        Map<String, Object> result = new HashMap<>();
        result.put("id", admin.getId());
        result.put("trueName", admin.getTrueName());
        result.put("phone", admin.getPhone());
        result.put("workId", admin.getWorkId());
        result.put("resetPassword", admin.getResetPassword());
        result.put("token", admin.getToken());
        return result;
    }

    @Override
    public Map changePassword(Long adminId, String password, String newPassword) {
        Admin admin = adminMapper.selectById(adminId);
        if (!admin.getPassword().equals(CommonUtil.encryptPassword(password, pwdPre, pwdAfter)))
            Res.fail(ErrorType.PASSWORD_ERR);
        admin.setPassword(CommonUtil.encryptPassword(newPassword, pwdPre, pwdAfter));
        admin.setResetPassword(0);
        // 清除redis
        redis.delete(RedisConst.adminToken + admin.getToken());
        // 更新token
        admin.setToken(CommonUtil.createToken());
        adminMapper.updateById(admin);
        // 缓存redis
        String key = RedisConst.adminToken + admin.getToken();
        String rules = authGroupMapper.getRulesByGroupId(admin.getGroupId());
        AdminAuthDto adminAuthDto = new AdminAuthDto();
        adminAuthDto.setAdminId(admin.getId());
        adminAuthDto.setInstId(admin.getInstId());
        adminAuthDto.setAuthType(admin.getAuthType());
        adminAuthDto.setRules(rules == null ? "" : rules);
        redis.set(key, adminAuthDto, expire);
        // 返回数据
        Map<String, Object> result = new HashMap<>();
        result.put("id", admin.getId());
        result.put("trueName", admin.getTrueName());
        result.put("phone", admin.getPhone());
        result.put("workId", admin.getWorkId());
        result.put("resetPassword", admin.getResetPassword());
        result.put("token", admin.getToken());
        return result;
    }

    @Override
    public AdminAuthDto getAdminAuthByToken(String token) {
        AdminAuthDto adminAuthDto;
        String key = RedisConst.adminToken + token;
        adminAuthDto = redis.get(key, AdminAuthDto.class);
        if (adminAuthDto == null)
            Res.fail(ErrorType.LOGIN_EXPIRE);
        redis.setExpire(key, expire);
        return adminAuthDto;
    }

    @Override
    public AdminAuthDto getAdminAuthByAdminId(Long adminId) {
        Admin admin = adminMapper.selectById(adminId);
        if (admin == null) {
            Res.fail(ErrorType.UNSAFE_USER_NOT_EXIST);
        }
        String rules = authGroupMapper.getRulesByGroupId(admin.getGroupId());
        AdminAuthDto adminAuthDto = new AdminAuthDto();
        adminAuthDto.setAdminId(admin.getId());
        adminAuthDto.setInstId(admin.getInstId());
        adminAuthDto.setAuthType(admin.getAuthType());
        adminAuthDto.setRules(rules == null ? "" : rules);
        return adminAuthDto;
    }

    @Override
    public List getAdminAndCompanyList(List<Long> adminIdList) {
        return adminMapper.getAdminAndCompanyList(adminIdList);
    }

    @Override
    public List getAdminListByIdList(List<Long> idList) {
        return adminMapper.getAdminListByIdList(idList);
    }
}
