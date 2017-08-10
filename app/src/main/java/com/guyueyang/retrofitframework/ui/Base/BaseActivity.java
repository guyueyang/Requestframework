package com.guyueyang.retrofitframework.ui.Base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/8/10.
 */

public class BaseActivity extends AppCompatActivity {
    private List<Call> requestCalls;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
    public void onNewRequestCall(Call call) {
        addRequestCall(call);
    }

    private void addRequestCall(Call call) {
        if (requestCalls == null) {
            requestCalls = new ArrayList<>();
        }
        requestCalls.add(call);
    }

    public void cancelRequest() {
        if (requestCalls == null)
            return;
        for (int i = 0; i < requestCalls.size(); i++) {
            Call call = requestCalls.get(i);
            if (call == null)
                return;
            if (!call.isCanceled()) {
                call.cancel();
            }
        }
        requestCalls.clear();
    }

}
