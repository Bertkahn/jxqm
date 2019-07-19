package com.cloud.common.aop;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {
    //
    int commonAuthType = 0;
    int masterAuthType = 1;
    int platAuthType = 2;
    int instAuthType = 3;
    //
    int query = 1;
    int add = 2;
    int edit = 3;
    int delete = 5;
    //
    String[] alias();
    int level();
}