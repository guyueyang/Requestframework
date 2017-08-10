package com.guyueyang.retrofitframework.http;

import android.content.Context;

import com.guyueyang.retrofitframework.utils.NETUtils;
import com.guyueyang.retrofitframework.utils.ToastUtils;

import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/4/18.
 */

public abstract class AHttpService<T> {
    protected Context context;
    protected ApiService apiService;

    public AHttpService(Context context) {
        this.context = context;
        apiService = ApiServiceModule.getInstance().providerApiFileUploadService(context);
    }
    /**
     * 发送请求数据
     *
     * @param iResCallback
     * @return
     */
    public Call request(final IResCallback<T> iResCallback) {
        Call call = null;
        if (!NETUtils.isNetworkConnected(context)) {
            //处理无网络情况
            if (iResCallback != null) {
                iResCallback.onCallback(IResCallback.RESULT_NOT_NET_ERROR, null);
            }
            return call;
        }
        try {
            call = newRetrofitCall(apiService);
            call.enqueue(new retrofit2.Callback<T>() {

                @Override
                public void onResponse(Call<T> call, Response<T> response) {
                    int responseCode = response.code();
                    if (responseCode == 200) {
                        if (iResCallback != null) {
                            iResCallback.onCallback(IResCallback.RESULT_OK,response);
                        }
                    } else {
                        try {
                            String msg = new String(response.errorBody().bytes());
                            ToastUtils.show(context, "onResponse responseCode:" + responseCode + "," + msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                            if (iResCallback != null) {
                                iResCallback.onCallback(IResCallback.RESULT_NET_ERROR, null);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<T> call, Throwable t) {
                    if (iResCallback != null) {
                        iResCallback.onCallback(IResCallback.RESULT_NET_ERROR, null);
                    }
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
            if (iResCallback != null) {
                iResCallback.onCallback(IResCallback.RESULT_OTHER_ERROR, null);
            }
        }
        return call;
    }
    //子类继承实现
    protected abstract Call newRetrofitCall(ApiService apiService);

    public interface IResCallback<T> {
        int RESULT_OK = 0;

        int RESULT_NOT_NET_ERROR = 1;

        int RESULT_NET_ERROR = 2;

        int RESULT_SERVICE_ERROR = 3;

        int RESULT_OTHER_ERROR = 4;

        void onCallback(int resultCode, Response<T> response);
    }
}
