package com.cloud.admin.service;


import java.util.List;

/**
 * @author 倚楼无言
 * @date 2019/7/23 16:51
 */
public interface InstService {
    List getInstNameListByIdList(List<Long> InstIdList);
}
