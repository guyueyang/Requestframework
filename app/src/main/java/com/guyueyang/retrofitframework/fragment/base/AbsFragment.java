package com.guyueyang.retrofitframework.fragment.base;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.guyueyang.retrofitframework.views.LoadingPage;
import com.guyueyang.retrofitframework.views.dialog.DialogCallback;
import com.guyueyang.retrofitframework.runtime.RuntimePermission;
import com.guyueyang.retrofitframework.views.dialog.YXProgressDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by Administrator on 2017/10/23.
 */

public abstract class AbsFragment<T extends ViewDataBinding> extends Fragment implements RuntimePermission.PermissionCallbacks, DialogCallback {
    private static final String TAG = "ARP";
    protected T mDataBinding;

    private List<Call> mRequestCalls;
    private YXProgressDialog mYXDialog;

    private LoadingPage mLoadingPage;
    private boolean mIsLoadingPageShown;

    protected abstract T bindRootView(LayoutInflater inflater, ViewGroup container, boolean attachToRoot);

    protected abstract void setupViews();

    protected String getPageName() {
        return null;
    }

    public void autoRefresh() {
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDataBinding = bindRootView(inflater, container, false);
        setupViews();
        return mDataBinding.getRoot();
    }

    protected void startNewActivity(Class<?> targetActivity) {
        startNewActivity(targetActivity, null);
    }

    protected void startNewActivity(Class<?> targetActivity, Bundle argument) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), targetActivity);
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
        if (mRequestCalls == null)
            return;
        for (int i = 0; i < mRequestCalls.size(); i++) {
            Call call = mRequestCalls.get(i);
            if (call == null)
                return;
            if (!call.isCanceled()) {
                call.cancel();
            }
        }
        mRequestCalls.clear();
    }

    @Override
    public void onDestroyView() {
        closeLoadingPage();
        closeDialog();
        cancelRequest();
        super.onDestroyView();
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
            mYXDialog = new YXProgressDialog(getContext(), message);
        }
        mYXDialog.show();
    }

    protected void showDialog() {
        if (mYXDialog == null) {
            mYXDialog = new YXProgressDialog(getContext());
        }
        mYXDialog.show();
    }

    protected void closeDialog() {
        if (mYXDialog != null) {
            mYXDialog.dismiss();
            mYXDialog = null;
        }
    }

    @Override
    public void onCancelRequest() {
        cancelRequest();
    }

    public boolean isLoadingPageShowing() {
        return mIsLoadingPageShown;
    }

    public void showLoadingPage() {
        showLoadingPage(View.NO_ID);
    }

    public void showLoadingPage(int layoutId) {
        if (!mIsLoadingPageShown) {
            mIsLoadingPageShown = true;
            if (mLoadingPage == null) {
                mLoadingPage = createLoadingPage();
            }
            mLoadingPage.loading();
            displayLoadingPage(layoutId);
        }
    }

    private boolean displayLoadingPage(int layoutId) {
        ViewGroup layout = null;
        if (getView() != null) {
            int index = -1;
            if (layoutId != View.NO_ID) {
                layout = (ViewGroup) getActivity().findViewById(layoutId);
                if (layout instanceof LinearLayout) {
                    index = 0;
                }
            } else {
                layout = ((ViewGroup) getView());
            }
            if (layout != null && mLoadingPage.getParent() == null) {
                layout.addView(mLoadingPage, index, new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
        }
        return layout != null;
    }

    //    public void showProgressDialogInLocation(int layoutId) {
    //    	showProgressDialog(null, layoutId);
    //    public void showProgressDialogInLocation(int resId,int layoutId) {
    //    	showProgressDialog(getString(resId),layoutId);
    private LoadingPage createLoadingPage() {
        final Context context = getActivity();
        LoadingPage loadingPage = new LoadingPage(context) {
            @Override
            protected void reload() {
                onReload(context);
            }
        };
        return loadingPage;
    }

    protected void onReload(Context context) {
    }

    public void showFailPage(String message) {
        if (mLoadingPage != null) {
            mLoadingPage.failed(message);
        }
    }

    public void showFailPage(int messageId) {
        if (mLoadingPage != null) {
            mLoadingPage.failed(messageId);
        }
    }

    public void showFailPage() {
        if (mLoadingPage != null) {
            mLoadingPage.failed(null);
        }
    }

    public void setLoadFailedMessage(String failedMessage) {
        if (mLoadingPage != null) {
            mLoadingPage.failed(failedMessage);
        }
    }

    public void setLoadFailedMessage(int failedStringId) {
        if (mLoadingPage != null) {
            mLoadingPage.failed(failedStringId);
        }
    }

    private boolean displayView(View view, int layoutId) {
        ViewGroup layout = null;
        if (view != null) {
            int index = -1;
            if (layoutId != View.NO_ID) {
                layout = (ViewGroup) getActivity().findViewById(layoutId);
            } else {
                layout = (ViewGroup) getActivity().getWindow().getDecorView();
            }
            if (layout != null) {
                if (layout instanceof LinearLayout) {
                    index = 0;
                }
                layout.addView(view, index, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
        }
        return layout != null;
    }

    public void closeLoadingPage() {
        mIsLoadingPageShown = false;
        removeLoadingPage();
    }

    private void removeLoadingPage() {
        if (mLoadingPage != null) {
            ViewGroup parent = (ViewGroup) mLoadingPage.getParent();
            if (parent != null) {
                parent.removeView(mLoadingPage);
            }
        }
    }
}
