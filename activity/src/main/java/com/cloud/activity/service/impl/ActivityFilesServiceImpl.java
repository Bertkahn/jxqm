package com.cloud.activity.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.activity.dao.ActivityFilesMapper;
import com.cloud.activity.dao.ActivityMapper;
import com.cloud.activity.entity.ActivityFiles;
import com.cloud.activity.service.ActivityFilesService;
import com.cloud.activity.service.ActivityService;
import com.cloud.common.dto.TableDto;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 倚楼无言
 * @date 2019/7/24 15:42
 */
@Service
public class ActivityFilesServiceImpl implements ActivityFilesService {

    @Resource
    private ActivityFilesMapper activityFilesMapper;
    @Override
    public List<Map<String,Object>> getActivityFiles(TableDto tableDto, Long activityId) {
        List<Map<String, Object>> maps = activityFilesMapper.selectActivityFiles(activityId, tableDto);
        return maps;
    }

    @Override
    public void editActivityFiles(Long id, String url, Long activityId, String name) {
        ActivityFiles activityFiles = activityFilesMapper.selectById(id);
        if(activityFiles==null)
            Res.fail(ErrorType.NOT_EXIST);
        activityFiles.setUrl(url);
        activityFiles.setActivityId(activityId);
        activityFiles.setName(name);
        activityFilesMapper.updateById(activityFiles);

    }

}
