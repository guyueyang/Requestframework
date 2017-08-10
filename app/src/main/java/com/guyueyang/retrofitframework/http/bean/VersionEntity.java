package com.guyueyang.retrofitframework.http.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/10.
 * 数据格式  服务器返回具体值
 * 继承baseServerInfo
 */

public class VersionEntity implements Serializable {
    @Expose
    private String code;
    @Expose
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
