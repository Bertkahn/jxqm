package com.cloud.user.server;

import com.cloud.common.parent.ServerController;
import com.cloud.common.response.ErrorType;
import com.cloud.common.response.Res;
import com.cloud.common.util.CommonUtil;
import com.cloud.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/server/user")
public class UserServer extends ServerController {
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
