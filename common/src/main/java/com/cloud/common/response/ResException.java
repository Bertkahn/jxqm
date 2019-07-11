package com.cloud.common.response;

/**
 * Created  by sun on 2017/9/13.
 */
public class ResException extends RuntimeException {
    public static final int TYPE_JSON = 1;
    public static final int TYPE_VIEW = 2;
    public static final int TYPE_REDIRECT = 3;
    public static final int TYPE_ERROR = 4;

    private int type;//1:json，2:view

    private int code;
    private Object data;
    private String msg;

    private String view;

    private String redirect;

    private String url;

    private int delay;

    private ResException(int code, Object data, String msg) {
        this.type = TYPE_JSON;
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    private ResException(int type, String param) {
        this.type = type;
        if (type == TYPE_VIEW){
            this.view = param;
        }else {
            this.redirect = param;
        }
    }

    private ResException(String msg, String url, int delay) {
        this.type = TYPE_ERROR;
        this.msg = msg;
        this.url = url;
        this.delay = delay;
    }

    private ResException(String msg, String url) {
        this.type = TYPE_ERROR;
        this.msg = msg;
        this.url = url;
        this.delay = 3;
    }

    private ResException(String msg) {
        this.type = TYPE_ERROR;
        this.msg = msg;
        this.url = "";
        this.delay = 3;
    }


    public static ResException view(String view){
        return new ResException(TYPE_VIEW, view);
    }

    public static ResException redirect(String redirect){
        return new ResException(TYPE_REDIRECT, redirect);
    }

    public static ResException result(ResModel resModel){
        return new ResException(resModel.getCode(), resModel.getData(), resModel.getMsg());
    }

    public static ResException result(int code, Object data, String msg){
        return new ResException(code, data, msg);
    }

    public static ResException fail(ErrorType error){
        return new ResException(error.getCode(), "", error.getMsg());
    }

    public static ResException fail(String error){
        return new ResException(ErrorType.OTHER_ERR.getCode(), "", error);
    }

    public static ResException success(){
        return new ResException(0, "", "");
    }

    public static ResException success(Object data){
        return new ResException(0, data, "");
    }

    public static ResException error(String msg){
        return new ResException(msg);
    }

    public static ResException error(String msg, String url){
        return new ResException(msg, url);
    }

    public static ResException error(String msg, String url, int delay){
        return new ResException(msg, url, delay);
    }


    public int getType() {
        return type;
    }
    public int getCode() {
        return code;
    }
    public Object getData() {
        return data;
    }
    public String getMsg() {
        return msg;
    }
    public String getRedirect() {
        return redirect;
    }
    public String getView(){
        return view;
    }
    public String getUrl(){
        return url;
    }
    public int getDelay(){
        return delay;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
