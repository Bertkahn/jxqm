package com.cloud.admin.server;

import com.cloud.admin.service.InstService;
import com.cloud.common.parent.ServerController;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 倚楼无言
 * @date 2019/7/23 16:05
 */
@Controller
@RequestMapping("/server/inst")
public class InstServer extends ServerController {
    @Resource
    private InstService instService;

    @RequestMapping("/getInstNameListByIdList")
    public void getInstNameListByIdList() {
        List<Long> instIdList = postList("idList", Long.class);
        if (CommonUtil.isEmpty(instIdList))
            Res.success(new ArrayList<>());
        Res.success(instService.getInstNameListByIdList(instIdList));
    }

}
