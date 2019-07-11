package com.cloud.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.dto.TableDto;

public interface CrmService {
    Page getSaleUserPageByAdminId (Long adminId, TableDto tableDto);
}
