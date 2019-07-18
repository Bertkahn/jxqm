package com.cloud.tool.client;

import com.cloud.common.parent.ClientController;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import com.cloud.tool.service.ParamService;
import com.cloud.tool.service.WxService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/client/param")
public class ParamClient extends ClientController {
    ParamClient() {
        noNeedLogin = new ArrayList<>(Arrays.asList("encodeParam", "decodeParam"));
    }

    @Resource
    private ParamService paramService;

    @RequestMapping("/encodeParam")
    public void encodeParam () {
        Map param = postMap("param", new HashMap<>());
        Res.success(paramService.encodeParam(param));
    }

    @RequestMapping("/decodeParam")
    public void decodeParam () {
        String token = postString("token");
        if (CommonUtil.isEmpty(token))
            Res.fail(ErrorType.PARAM_ERR);
        Res.success(paramService.decodeParam(token));
    }
}
