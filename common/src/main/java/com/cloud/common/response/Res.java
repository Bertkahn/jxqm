package com.cloud.common.response;

public class Res {
    public static void result(ResModel resModel){
        throw ResException.result(resModel);
    }

    public static void result(Integer code, Object data, String msg){
        throw ResException.result(code, data, msg);
    }

    public static void fail(ErrorType error){
        throw ResException.fail(error);
    }

    public static void fail(String error){
        throw ResException.fail(error);
    }

    public static void success(){
        throw ResException.success();
    }

    public static void success(Object data){
        throw ResException.success(data);
    }

    public static void view(String view){
        throw ResException.view(view);
    }

    public static void redirect(String redirect){
        throw ResException.redirect("redirect:"+redirect);
    }

    public static ResException error(String msg){
        return ResException.error(msg);
    }

    public static ResException error(String msg, String url){
        return ResException.error(msg, url);
    }

    public static ResException error(String msg, String url, int delay){
        return ResException.error(msg, url, delay);
    }
}
