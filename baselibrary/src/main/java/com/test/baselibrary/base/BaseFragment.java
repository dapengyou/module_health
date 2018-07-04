package com.test.baselibrary.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.test.baselibrary.constant.Constant;

/**
 * Created by lady_zhou on 2018/1/9.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    public View mRootView;
    public Activity mActivity;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutId() > 0) {
            //避免没有设置id的异常
            try {
                mRootView = inflater.inflate(getLayoutId(), container, false);

            } catch (Exception e) {
                e.getStackTrace();
            }

        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView(savedInstanceState);
        initData(getArguments(), savedInstanceState);
        initListener();
    }

    /**
     * 获得相应的layoutId
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化数据
     *
     * @param arguments
     * @param savedInstanceState
     */
    protected abstract void initData(Bundle arguments, Bundle savedInstanceState);


    /**
     * 初始化点击事件
     */
    protected abstract void initListener();

    /**
     * 当View被点击时调用
     *
     * @param v
     */
    protected abstract void onViewClick(View v);

    @Override
    public void onClick(View v) {
        onViewClick(v);
    }

    /**
     * 显示短Toast
     *
     * @param message
     */
    protected void show(String message) {
        show(message, false);
    }

    /**
     * 显示传入ResId 的 Toast
     *
     * @param resId
     */
    protected void show(int resId) {
        show(getString(resId), false);
    }

    /**
     * 显示toast
     *
     * @param message 显示的内容
     * @param isLong  是否是长Toast
     */
    protected void show(String message, boolean isLong) {
        if (isLong) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    protected final <T extends View> T findViewById(int id) {
        return (T) mRootView.findViewById(id);
    }

    /**
     * 申请指定的权限.
     */
    public void requestPermission(int code, String... permissions) {

        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(permissions, code);
        }
    }

    /**
     * 判断是否有指定的权限
     */
    public boolean hasPermission(String... permissions) {

        for (String permisson : permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(), permisson)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Constant.HARDWEAR_CAMERA_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doOpenCamera();
                }
                break;
            case Constant.WRITE_READ_EXTERNAL_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doWriteSDCard();
                }
                break;
        }
    }

    public void doOpenCamera() {

    }

    public void doWriteSDCard() {

    }
}
