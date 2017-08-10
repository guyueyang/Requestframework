package com.guyueyang.retrofitframework.http;

import com.guyueyang.retrofitframework.http.bean.BaseEntity;
import com.guyueyang.retrofitframework.http.bean.VersionEntity;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * 类说明：接口定义
 */
public interface ApiService {

    String CHECK_UPDATE_URL="/api?resprotocol=json&class=System&method=CheckVersion";

    @POST(CHECK_UPDATE_URL)
    Call<BaseEntity<VersionEntity>> checkVersion();

}
