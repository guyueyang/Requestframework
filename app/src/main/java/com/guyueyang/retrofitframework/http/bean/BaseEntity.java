package com.guyueyang.retrofitframework.http.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/9.
 * 控制数据格式 这个根据需求更改
 *
 *
 */

public class BaseEntity<T>  implements Serializable {
    @Expose
    private T baseServerInfo;
    @Expose
    private boolean isUpdate;
    @Expose
    private Number buildNum;
    @Expose
    private boolean isForce;

    public T getBaseServerInfo() {
        return baseServerInfo;
    }

    public void setBaseServerInfo(T baseServerInfo) {
        this.baseServerInfo = baseServerInfo;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    public Number getBuildNum() {
        return buildNum;
    }

    public void setBuildNum(Number buildNum) {
        this.buildNum = buildNum;
    }

    public boolean isForce() {
        return isForce;
    }

    public void setForce(boolean force) {
        isForce = force;
    }
}
