package com.cloud.analyse.server;

import com.cloud.common.parent.ServerController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/server/log")
public class AnalyseServer extends ServerController {
//    @Resource
//    private UserService userService;
//
//    @RequestMapping("/getUserAuthByToken")
//    public void getUserAuthByToken () {
//        String token = postString("token");
//        if (CommonUtil.isEmpty(token))
//            Res.fail(ErrorType.SERVER_PARAM_ERR);
//        Res.success(userService.getUserAuthByToken(token));
//    }
//
//    @RequestMapping("/getUserAuthByUserId")
//    public void getAdminAuthByAdminId () {
//        Long userId = postLong("userId");
//        if (CommonUtil.isEmpty(userId))
//            Res.fail(ErrorType.SERVER_PARAM_ERR);
//        Res.success(userService.getUserAuthByUserId(userId));
//    }
//
//    @RequestMapping("/getUserByUserId")
//    public void getUserByUserId () {
//        Long userId = postLong("userId");
//        if (CommonUtil.isEmpty(userId))
//            Res.fail(ErrorType.SERVER_PARAM_ERR);
//        Res.success(userService.getUserByUserId(userId));
//    }
}
