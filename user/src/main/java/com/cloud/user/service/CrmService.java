package com.cloud.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.dto.TableDto;

import java.util.Map;

public interface CrmService {
    Page<Map<String, Object>> getSaleUserPageByAdminId (Long adminId, TableDto tableDto);
}
