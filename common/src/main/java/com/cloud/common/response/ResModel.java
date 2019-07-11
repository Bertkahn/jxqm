package com.cloud.common.response;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created  by sun on 2017/9/13.
 */

public class ResModel implements Serializable {
    private Integer code;
    private Object data;
    private String msg;
    public ResModel(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public void check () {
        if (this.code != 0) {
            Res.result(this);
        }
    }

    public static ResModel result(Integer code, Object data, String msg){
        return new ResModel(code, data, msg);
    }

    public static ResModel fail(ErrorType error){
        return new ResModel(error.getCode(), "", error.getMsg());
    }

    public static ResModel fail(Integer code, String msg){
        return new ResModel(code, "", msg);
    }

    public static ResModel success(){
        return new ResModel(0, "", "");
    }

    public static ResModel success(Object data){
        return new ResModel(0, data, "");
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

    @SuppressWarnings("unchecked")
	public <T> T getData(Class<T> clazz) {
        String json = JSONObject.toJSONString(this.data);
        return JSONObject.parseObject(json, clazz);
    }
}
