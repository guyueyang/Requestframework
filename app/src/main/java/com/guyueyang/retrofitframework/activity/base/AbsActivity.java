package com.guyueyang.retrofitframework.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import com.guyueyang.retrofitframework.R;
import com.guyueyang.retrofitframework.runtime.RuntimePermission;
import com.guyueyang.retrofitframework.utils.DisplayUtils;
import com.guyueyang.retrofitframework.utils.EventBusUtil;
import com.guyueyang.retrofitframework.utils.UdaUtils;
import com.guyueyang.retrofitframework.views.dialog.AlertDialog;
import com.guyueyang.retrofitframework.views.dialog.DialogCallback;
import com.guyueyang.retrofitframework.views.dialog.YXProgressDialog;
import com.guyueyang.retrofitframework.views.statusbar.StatusBarCompat;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import retrofit2.Call;


/**
 * Created by Administrator on 2017/10/23.
 */

public abstract class AbsActivity extends AppCompatActivity implements RuntimePermission.PermissionCallbacks, BGASwipeBackHelper.Delegate, DialogCallback {
    private static final String TAG = "ARP";
    private List<Call> mRequestCalls;
    private YXProgressDialog mYXDialog;
    protected BGASwipeBackHelper mSwipeBackHelper;
    private List<AlertDialog> alartDialogList = new ArrayList<>();

    protected boolean fullScreenMode() {
        return false;
    }

    protected boolean lightStatusBar() {
        return true;
    }

    protected String getPageName() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initSwipeBackLayout();
        super.onCreate(savedInstanceState);

    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, fullScreenMode(), lightStatusBar(), getResources().getColor(R.color.colorPrimary));
    }

    private void initSwipeBackLayout() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);
        mSwipeBackHelper.setSwipeBackEnable(true);
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        mSwipeBackHelper.setIsWeChatStyle(true);
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        mSwipeBackHelper.setIsNeedShowShadow(true);
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
        float maxAspect = (float) DisplayUtils.getDeviceHeight(this) / (float) DisplayUtils.getDeviceWidth(this);
        mSwipeBackHelper.setIsNavigationBarOverlap(Float.valueOf(getString(R.string.format_aspect, maxAspect)).floatValue() > 1.8f ? true : false);
    }

    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    @Override
    public void onSwipeBackLayoutCancel() {
    }

    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        UdaUtils.hideInputMethod(this, getCurrentFocus());
        FragmentManager manager = getSupportFragmentManager();
        int count = manager.getBackStackEntryCount();
        if (count == 0) {
            if (mSwipeBackHelper.isSliding()) {
                return;
            }
            finish();
        } else {
            try {
                manager.popBackStackImmediate();
            } catch (IllegalStateException ignored) {
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }



    public void addAlertDialog(AlertDialog alertDialog) {
        alartDialogList.add(alertDialog);
    }

    public void closeAlerDialog() {
        for (AlertDialog alertDialog : alartDialogList) {
            alertDialog.dismiss();
        }
        alartDialogList.clear();
    }


    protected void startNewActivity(Class<?> targetActivity) {
        startNewActivity(targetActivity, null);
    }

    protected void startNewActivity(Class<?> targetActivity, Bundle argument) {
        Intent intent = new Intent();
        intent.setClass(this, targetActivity);
        if (argument != null) {
            intent.putExtras(argument);
        }
        startActivity(intent);
    }



    public void onNewRequestCall(Call call) {
        addRequestCall(call);
    }

    private void addRequestCall(Call call) {
        if (mRequestCalls == null) {
            mRequestCalls = new ArrayList<>();
        }
        mRequestCalls.add(call);
    }

    public void cancelRequest() {
        if (mRequestCalls == null) return;
        for (int i = 0; i < mRequestCalls.size(); i++) {
            Call call = mRequestCalls.get(i);
            if (call == null) return;
            if (!call.isCanceled()) {
                call.cancel();
            }
        }
        mRequestCalls.clear();
    }

    @Override
    protected void onDestroy() {
        cancelRequest();
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        RuntimePermission.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> params) {
        Log.d(TAG, "PermissionsGranted");
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> params) {
        Log.d(TAG, "PermissionsDenied");
    }

    protected void showDialog(String message) {
        if (mYXDialog == null) {
            mYXDialog = new YXProgressDialog(this, message);
        }
        mYXDialog.show();
    }

    protected void showDialog() {
        if (mYXDialog == null) {
            mYXDialog = new YXProgressDialog(this);
        }
        mYXDialog.show();
    }

    protected void closeDialog() {
        if (mYXDialog != null) {
            mYXDialog.dismiss();
        }
    }

    @Override
    public void onCancelRequest() {
        cancelRequest();
    }
}
