package com.guyueyang.retrofitframework.activity.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.guyueyang.retrofitframework.R;
import com.guyueyang.retrofitframework.databinding.ActivityAbsToolbarBinding;
import com.guyueyang.retrofitframework.fragment.base.AbsFragment;
import com.guyueyang.retrofitframework.utils.DisplayUtils;


/**
 * Created by Administrator on 2017/10/23.
 */

public abstract class AbsToolbarActivity extends AbsActivity {
    protected abstract AbsFragment onCreateContentView();

    protected AbsFragment mFragment;
    protected ActivityAbsToolbarBinding mDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_abs_toolbar);
        setupToolbar();
        setupContentView();
    }

    protected void setupToolbar() {
        mDataBinding.toolbar.setTitle("");
        mDataBinding.setEnableShareButton(enableShareButton());
        mDataBinding.setEnableOperateText(enableOperateText());
        mDataBinding.setEnableDivider(enableDivider());
        setSupportActionBar(mDataBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(enableDisplayHomeAsUp());
        getSupportActionBar().setHomeButtonEnabled(enableHomeButton());
        mDataBinding.toolbarTitle.setText(getToolBarTitle());
        LinearLayout.LayoutParams flParams = (LinearLayout.LayoutParams) mDataBinding.toolbar.getLayoutParams();
        flParams.height += DisplayUtils.getStatusBarHeight(this);
        mDataBinding.toolbar.setLayoutParams(flParams);
        mDataBinding.toolbar.setPadding(0,DisplayUtils.getStatusBarHeight(this),0,0);
    }

    protected void setToolBarTitle(String title) {
        if (!TextUtils.isEmpty(title)) mDataBinding.toolbarTitle.setText(title);
    }

    protected void setToolBarRightText(String text) {
        if (enableOperateText()) mDataBinding.tvOperateText.setText(text);
    }

    @Override
    protected boolean fullScreenMode() {
        return true;
    }

    protected String getToolBarTitle() {
        return getString(R.string.app_name);
    }

    protected Bundle onPutArguments() {
        return new Bundle();
    }

    protected boolean enableDisplayHomeAsUp() {
        return false;
    }

    protected boolean enableHomeButton() {
        return false;
    }

    protected boolean enableShareButton() {
        return false;
    }

    protected boolean enableOperateText() {
        return false;
    }

    protected boolean enableDivider() {
        return true;
    }

    public void onToolbarViewClick(View view) {
        if (enableShareButton()) {
            onOneKeyShareClick(view.getContext());
        } else if (enableOperateText()) {
            onToolbarRightClick(view.getContext());
        } else {

        }
    }

    protected void onToolbarRightClick(Context context) {

    }

    protected void onOneKeyShareClick(Context context) {

    }

    private void setupContentView() {
        mFragment = onCreateContentView();
        if (mFragment != null) {
            Bundle args = onPutArguments();
            if (args != null) {
                mFragment.setArguments(args);
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentLayout, mFragment, null).commitAllowingStateLoss();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
