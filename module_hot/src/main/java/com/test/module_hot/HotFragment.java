package com.test.module_hot;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.baselibrary.Utils.RouteUtils;
import com.test.baselibrary.base.BaseFragment;

@Route(path = RouteUtils.Hot_Fragment_Main)
public class HotFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle arguments, Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onViewClick(View v) {

    }
}
