package com.guyueyang.retrofitframework.views.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import com.guyueyang.retrofitframework.R;
import com.guyueyang.retrofitframework.databinding.DialogProgressBinding;
import com.guyueyang.retrofitframework.utils.StringUtils;


/**
 * Created by Administrator on 2017/6/13.
 */

public class YXProgressDialog extends BaseDialog implements DialogInterface.OnDismissListener{
    private DialogCallback mDialogCallback;
    private DialogProgressBinding mDataBinding;
    private String mMessage;


    public YXProgressDialog(Context context){
        super(context, R.style.dimless_dialog_style);
        setCanceledOnTouchOutside(false);
        setOnDismissListener(this);
        this.mDialogCallback = (DialogCallback) context;
    }

    public YXProgressDialog(Context context, String message) {
        this(context);
        this.mMessage = message;
    }

    @Override
    protected View onInflateView(LayoutInflater inflater) {
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_progress, null, false);
        return mDataBinding.getRoot();
    }

    @Override
    protected void onInitView(View view) {
        String defMsg= StringUtils.isNullEmpty(mMessage)?getContext().getString(R.string.progressbar_wait):mMessage;
        mDataBinding.message.setText(StringUtils.filterNullAndTrim(defMsg));
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if(mDialogCallback!=null){
            mDialogCallback.onCancelRequest();
        }
    }
}
