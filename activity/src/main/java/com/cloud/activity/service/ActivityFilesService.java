package com.cloud.activity.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.dto.TableDto;

import java.util.List;
import java.util.Map;

/**
 * @author 倚楼无言
 * @date 2019/7/24 16:02
 */
public interface ActivityFilesService  {
    //jiang
    //获取活动文件
    List<Map<String,Object>> getActivityFiles(TableDto tableDto, Long activityId);

    //jiang
    //修改活动文件
    void editActivityFiles(Long id, String url, String name);

    //jiang
    //上传活动文件
    void insertActivityFiles(Long activityId, String name, Integer type, String url);
}
