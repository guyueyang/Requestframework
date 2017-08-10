package com.guyueyang.retrofitframework.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.guyueyang.retrofitframework.R;
import com.guyueyang.retrofitframework.http.AHttpService;
import com.guyueyang.retrofitframework.http.action.CheckVersionAction;
import com.guyueyang.retrofitframework.http.bean.BaseEntity;
import com.guyueyang.retrofitframework.http.bean.VersionEntity;
import com.guyueyang.retrofitframework.ui.Base.BaseActivity;
import com.guyueyang.retrofitframework.utils.ToastUtils;

import retrofit2.Response;

public class MainActivity extends BaseActivity {

    Button mBtnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnCheck=(Button) findViewById(R.id.btn_check);
        mBtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNewRequestCall(CheckVersionAction.newInstance(getApplicationContext()).request(new AHttpService.IResCallback<BaseEntity<VersionEntity>>() {
                    @Override
                    public void onCallback(int resultCode, Response<BaseEntity<VersionEntity>> response) {
                        if(response==null){
                            return;
                        }
                        ToastUtils.show(getApplicationContext(),response.body().getBaseServerInfo().getMsg());
                    }
                }));
            }
        });
    }
}
