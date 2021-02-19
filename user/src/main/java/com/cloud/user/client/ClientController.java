package com.cloud.user.client;

import com.cloud.common.dto.UserAuthDto;
import com.cloud.user.service.UserService;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;

public class ClientController extends com.cloud.common.parent.ClientController {
    @Resource
    private UserService userService;

    @Override
    @ModelAttribute
    protected void init(){
        super.init();
    }

    protected UserAuthDto getUserAuthByUserId (Long userId) {
        return userService.getUserAuthByUserId(userId);
    }

    protected UserAuthDto getUserAuthByToken (String token) {
        return userService.getUserAuthByToken(token);
    }
}
