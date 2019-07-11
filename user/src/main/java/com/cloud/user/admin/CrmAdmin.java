package com.cloud.user.admin;

import com.cloud.common.parent.AdminController;
import com.cloud.common.response.Res;
import com.cloud.user.service.CrmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/crm")
public class CrmAdmin extends AdminController {
    @Resource
    private CrmService crmService;

    @RequestMapping("/getSaleUserPageByAdminId")
    public void getSaleUserPageByAdminId () {
        List<String> paramList = Arrays.asList("nickName", "trueName", "phone", "sex", "nextVisitTime");
        Res.success(crmService.getSaleUserPageByAdminId(getAdminId(), getTable(paramList)));
    }
}
