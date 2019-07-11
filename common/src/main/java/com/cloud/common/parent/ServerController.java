package com.cloud.common.parent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;

public class ServerController extends BaseController {
    @Value("${server.product}")
    private boolean product;

    @ModelAttribute
    protected void init(){
        super.init();
        checkAuth();
    }

    private void checkAuth () {
        if (product) {
            // todo ip
        }
    }
}
