package com.cloud.tool.client;

import com.cloud.common.parent.ClientController;
import com.cloud.common.response.Res;
import com.cloud.tool.service.OssService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


@Controller
@RequestMapping("/client/oss")
public class OssClient extends ClientController {
    @Resource
    private OssService ossService;

    @RequestMapping("/getUploadParam")
    public void getUploadPath () {
        Res.success(ossService.getUploadParam());
    }
}
