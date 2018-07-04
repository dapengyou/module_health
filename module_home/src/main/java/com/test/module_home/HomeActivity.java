package com.test.module_home;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.test.baselibrary.Utils.RouteUtils;
import com.test.baselibrary.base.BaseActivity;

public class HomeActivity extends BaseActivity {
    public static final String TAG_FRAGMENT_HOME = "home";
    public static final String TAG_FRAGMENT_HOSPITAL = "hospital";
    public static final String TAG_FRAGMENT_HOT = "hot";
    public static final String TAG_FRAGMENT_REPORT = "report";
    public static final String TAG_FRAGMENT_MINE = "mine";

    private FrameLayout mLlMain;
    private RadioButton mRbHome;
    private RadioButton mRbReport;
    private RadioButton mRbHot;
    private RadioButton mRbMine;
    private RadioButton mRbHospital;
    private RadioGroup mRgTab;

    private FragmentManager mFragmentManager;
    private Fragment mHomeFragment;
    private Fragment mHospitalFragment;
    private Fragment mHotFragment;
    private Fragment mReportFragment;
    private Fragment mMineFragment;
    private Fragment curFragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //有可能是在无可用的Activity或screen情况下，获取当前的窗口 并将窗口设置为透明色
        getWindow().setFormat(PixelFormat.TRANSLUCENT);//解决地图黑屏
        mFragmentManager = getSupportFragmentManager();

        mRbHome = findViewById(R.id.rb_home);
        mRbHospital = findViewById(R.id.rb_hospital);
        mRbHot = findViewById(R.id.rb_hot);
        mRbReport = findViewById(R.id.rb_report);
        mRbMine = findViewById(R.id.rb_mine);
        mRgTab = findViewById(R.id.rg_tab);
    }

    @Override
    protected void initData(Intent intent, Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {
        mRgTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switchTab(checkedId);
            }
        });
        switchTab(R.id.rb_home);
    }

    private void switchTab(int checkedId) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        hideAllFragment(ft);
        if (checkedId == R.id.rb_home) {
            mHomeFragment = mFragmentManager.findFragmentByTag(TAG_FRAGMENT_HOME);
            if (mHomeFragment == null) {
                mHomeFragment = RouteUtils.getHomeFragment();
                if (mHomeFragment != null) {
                    ft.add(R.id.ll_main, mHomeFragment, TAG_FRAGMENT_HOME);
                }
            }
            curFragment = mHomeFragment;
        } else if (checkedId == R.id.rb_hospital) {
            mHospitalFragment = mFragmentManager.findFragmentByTag(TAG_FRAGMENT_HOSPITAL);
            if (mHospitalFragment == null) {
                mHospitalFragment = RouteUtils.getHospitalFragment();
                if (mHospitalFragment != null) {
                    ft.add(R.id.ll_main, mHospitalFragment, TAG_FRAGMENT_HOSPITAL);
                }
            }
            curFragment = mHospitalFragment;
        } else if (checkedId == R.id.rb_hot) {
            mHotFragment = mFragmentManager.findFragmentByTag(TAG_FRAGMENT_HOT);
            if (mHotFragment == null) {
                mHotFragment = RouteUtils.getHotFragment();
                if (mHotFragment != null) {
                    ft.add(R.id.ll_main, mHotFragment, TAG_FRAGMENT_HOT);
                }
            }
            curFragment = mHotFragment;
        } else if (checkedId == R.id.rb_report) {
            mReportFragment = mFragmentManager.findFragmentByTag(TAG_FRAGMENT_REPORT);
            if (mReportFragment == null) {
                mReportFragment = RouteUtils.getReportFragment();
                if (mReportFragment != null) {
                    ft.add(R.id.ll_main, mReportFragment, TAG_FRAGMENT_REPORT);
                }
            }
            curFragment = mReportFragment;
        } else if (checkedId == R.id.rb_mine) {
            mMineFragment = mFragmentManager.findFragmentByTag(TAG_FRAGMENT_MINE);
            if (mMineFragment == null) {
                mMineFragment = RouteUtils.getMineFragment();
                if (mMineFragment != null) {
                    ft.add(R.id.ll_main, mMineFragment, TAG_FRAGMENT_MINE);
                }
            }
            curFragment = mMineFragment;
        }
        if (curFragment != null) {
            ft.show(curFragment).commit();
        }
    }

    private void hideAllFragment(FragmentTransaction ft) {
        Fragment fragment;
        fragment = mFragmentManager.findFragmentByTag(TAG_FRAGMENT_HOME);
        if (fragment != null) {
            ft.hide(fragment);
        }
        fragment = mFragmentManager.findFragmentByTag(TAG_FRAGMENT_HOSPITAL);
        if (fragment != null) {
            ft.hide(fragment);
        }
        fragment = mFragmentManager.findFragmentByTag(TAG_FRAGMENT_HOT);
        if (fragment != null) {
            ft.hide(fragment);
        }
        fragment = mFragmentManager.findFragmentByTag(TAG_FRAGMENT_REPORT);
        if (fragment != null) {
            ft.hide(fragment);
        }
        fragment = mFragmentManager.findFragmentByTag(TAG_FRAGMENT_MINE);
        if (fragment != null) {
            ft.hide(fragment);
        }
    }

    @Override
    protected void onViewClick(View v) {

    }

}
