package com.guyueyang.retrofitframework.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.guyueyang.retrofitframework.R;
import com.guyueyang.retrofitframework.databinding.FragmentMainBinding;
import com.guyueyang.retrofitframework.fragment.base.AbsFragment;
import com.guyueyang.retrofitframework.http.AHttpService;
import com.guyueyang.retrofitframework.http.action.CheckVersionAction;
import com.guyueyang.retrofitframework.http.bean.BaseEntity;
import com.guyueyang.retrofitframework.http.bean.VersionEntity;
import com.guyueyang.retrofitframework.utils.ToastUtils;
import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.SaveCallback;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/6/6.
 */

public class MainFragment extends AbsFragment<FragmentMainBinding> implements View.OnClickListener{


    public static MainFragment instance() {
        MainFragment view = new MainFragment();
        return view;
    }

    @Override
    protected FragmentMainBinding bindRootView(LayoutInflater inflater, ViewGroup container, boolean attachToRoot) {
        return FragmentMainBinding.inflate(inflater,container,attachToRoot);
    }

    @Override
    protected void setupViews() {
        mDataBinding.btonSelect.setOnClickListener(this);
        mDataBinding.btonUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bton_update:
                clickCheck();
                break;
            case R.id.bton_select:
                clickSelect();
                break;
        }
    }

    private void clickCheck(){
        showDialog();
        onNewRequestCall(CheckVersionAction.newInstance(getContext()).request(new AHttpService.IResCallback<BaseEntity<VersionEntity>>() {
            @Override
            public void onCallback(int resultCode, Response<BaseEntity<VersionEntity>> response) {
                if(getView()!=null){
                    if (response == null) {
                        closeDialog();
                        return;
                    }
                    closeDialog();
                    DataSupport.deleteAll(VersionEntity.class);
                    List<VersionEntity> newsList = new ArrayList<VersionEntity>();
                    newsList.add(response.body().getBaseServerInfo());
                    DataSupport.saveAll(newsList);
                    ToastUtils.show(getContext(), response.body().getBaseServerInfo().getMsg());

                    DataSupport.saveAllAsync(newsList).listen(new SaveCallback() {
                        @Override
                        public void onFinish(boolean success) {

                        }
                    });
                }
            }
        }));

    }

    private void clickSelect(){
        List<VersionEntity> newsList=DataSupport.findAll(VersionEntity.class);
        mDataBinding.tvData.setText(newsList.get(0).getMsg());
    }
}
