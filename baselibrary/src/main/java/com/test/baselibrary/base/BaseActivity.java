package com.test.baselibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity implements OnClickListener {
    public Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        //设置成没有Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());

        initView(savedInstanceState);

        Intent intent = getIntent();
        initData(intent, savedInstanceState);

        initListener();
    }

    /**
     * 获得相应Activity的layoutId
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
     * @param intent
     * @param savedInstanceState
     */
    protected abstract void initData(Intent intent, Bundle savedInstanceState);

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
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 添置生命周期
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
