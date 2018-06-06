package com.guyueyang.retrofitframework.activity;

import android.os.Bundle;
import com.guyueyang.retrofitframework.R;
import com.guyueyang.retrofitframework.activity.base.AbsActivity;
import com.guyueyang.retrofitframework.fragment.MainFragment;

public class MainActivity extends AbsActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerId, MainFragment.instance(), null).commitAllowingStateLoss();

    }

}
