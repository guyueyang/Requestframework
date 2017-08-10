package com.guyueyang.retrofitframework.http.action;

import android.content.Context;

import com.guyueyang.retrofitframework.http.AHttpService;
import com.guyueyang.retrofitframework.http.ApiService;
import com.guyueyang.retrofitframework.http.bean.BaseEntity;
import com.guyueyang.retrofitframework.http.bean.VersionEntity;
import com.guyueyang.retrofitframework.utils.ConfigUtils;

import retrofit2.Call;


/**
 * Created by Administrator on 2017/6/8.
 */

public class CheckVersionAction extends AHttpService<BaseEntity<VersionEntity>> {

    public static CheckVersionAction newInstance(Context context) {
        return new CheckVersionAction(context);
    }

    public CheckVersionAction(Context context) {
        super(context);
        ConfigUtils.setUploadUrl(context,"http://oyyx.oybus.com");
    }

    @Override
    protected Call newRetrofitCall(ApiService apiService) {
        return apiService.checkVersion();
    }
}
