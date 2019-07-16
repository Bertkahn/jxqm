package com.cloud.tool.client;

import com.cloud.common.parent.ClientController;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import com.cloud.tool.service.WxService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/client/wx")
public class WxClient extends ClientController {
    WxClient () {
        noNeedLogin = new ArrayList<>(Arrays.asList("getWxMinOpenId"));
    }

    @Resource
    private WxService wxService;

    @RequestMapping("/getWxMinOpenId")
    public void getWxMinOpenId () {
        String code = postString("code");
        if (CommonUtil.isEmpty(code))
            Res.fail(ErrorType.PARAM_ERR);
        Res.success(wxService.getWxMinOpenIdAndUnionId(code));
    }
}
