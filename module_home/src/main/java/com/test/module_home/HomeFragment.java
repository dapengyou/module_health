package com.test.module_home;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.test.baselibrary.Utils.RouteUtils;
import com.test.baselibrary.base.BaseFragment;

@Route(path = RouteUtils.Home_Fragment_Main)
public class HomeFragment extends BaseFragment {
    public static final String TAG = HomeFragment.class.getSimpleName();//得到类名

    /**
     * 使用单例
     *
     * @return
     */
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
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
