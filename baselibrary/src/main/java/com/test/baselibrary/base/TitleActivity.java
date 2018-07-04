package com.test.baselibrary.base;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.baselibrary.R;

/**
 * Created by lady_zhou on 2018/1/8.
 */

public abstract class TitleActivity extends BaseActivity {
    // 此界面根视图
    private LinearLayout mRootView;
    // 内容视图
    private View mContentView;
    // 标题栏
    private RelativeLayout mTitleBar;
    // 标题文本
    protected TextView mTitleTv;
    // 标题栏左文本
    protected TextView mLeftTv;
    // 标题栏右文本
    protected TextView mRightTv;

    @Override
    protected final int getLayoutId() {
        return R.layout.activity_title;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTitleBar = findViewById(R.id.title_bar);
        mTitleTv = findViewById(R.id.title_title_tv);
        mLeftTv = findViewById(R.id.title_left_tv);
        mRightTv = findViewById(R.id.title_right_tv);
        mRootView = findViewById(R.id.title_root);
        setLeftIcon(R.mipmap.back_white);
        //获取到Title下方的layoutId
        int contentResId = getContentResId();
        if (contentResId > 0) {
            mContentView = getLayoutInflater().inflate(contentResId, mRootView, false);
            mRootView.addView(mContentView);
        }
    }

    /**
     * 得到标题栏下方布局资源id
     *
     * @return
     */
    protected abstract int getContentResId();


    /**
     * 设置标题栏左文本图标
     *
     * @param drawableId 传0 隐藏图标
     */
    public void setLeftIcon(@DrawableRes int drawableId) {
        if (drawableId == 0) {
            mLeftTv.setVisibility(View.GONE);
        } else {
            setIcon(mLeftTv, drawableId, 1.0);
        }
    }

    /**
     * 设置标题栏右文本图标
     *
     * @param drawableId 传0 隐藏图标
     */
    @SuppressWarnings("deprecation")
    public void setRightIcon(@DrawableRes int drawableId) {
        if (drawableId == 0) {
            mRightTv.setVisibility(View.GONE);
        } else {
            setIcon(mRightTv, drawableId, 1.0);
        }
    }

    /**
     * 设置左右边图标
     *
     * @param iconId
     */
    private void setIcon(TextView mView, int iconId, double scale) {
        Drawable drawable = getResources().getDrawable(iconId);
        if (drawable != null) {
            //设置位置
            drawable.setBounds(0, 0, (int) (drawable.getMinimumWidth() * scale), (int) (drawable.getMinimumHeight() * scale));

            mView.setCompoundDrawables(drawable, null, null, null);
            mView.setVisibility(View.VISIBLE);
            mView.setOnClickListener(this);

        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.title_left_tv) {
            onLeftClick(v);
        } else if (v.getId() == R.id.title_right_tv) {
            onRightClick(v);
        } else {
            super.onClick(v);
        }
    }

    /**
     * 当标题栏左文本被点击时调用，默认实现为结束当前Activity
     *
     * @param leftTv
     */
    protected void onLeftClick(View leftTv) {
        finish();
    }

    /**
     * 当标题栏右文本被点击时调用
     *
     * @param rigthTv
     */
    protected void onRightClick(View rigthTv) {
        //应用逻辑内容
    }


    protected void setTitleBarColor(int[] colors) {
        int titleColors[] = colors;
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,colors);
//        GradientDrawable bg = new GradientDrawable(GradientDrawable.RECTANGLE, titleColors);
        //根据SDK判断
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            mTitleBar.setBackgroundDrawable(bg);
        } else {
            mTitleBar.setBackground(bg);
        }
    }

    /**
     * 设置标题栏文本文字,可设置strings里的内容
     *
     * @param stringId
     * @see #setTitleText(String)
     */
    public void setTitleText(@StringRes int stringId) {
        setTitleText(getString(stringId));
    }

    /**
     * 设置标题栏文本文字
     *
     * @param text
     */
    public void setTitleText(String text) {
        setViewAttribute(mTitleTv, text);
    }

    /**
     * 设置标题栏左文本文字,可设置strings里的内容
     *
     * @param stringId
     * @see #setLeftText(String)
     */
    public void setLeftText(@StringRes int stringId) {
        setLeftText(getString(stringId));
    }

    /**
     * 设置标题栏左文本文字
     *
     * @param text
     */
    public void setLeftText(String text) {
        setViewAttribute(mLeftTv, text);
    }

    /**
     * 设置标题栏右文本文字,可设置strings里的内容
     *
     * @param stringId
     * @see #setRightText(String)
     */
    public void setRightText(@StringRes int stringId) {
        setRightText(getString(stringId));
    }

    /**
     * 设置标题栏右文本文字
     *
     * @param text
     */
    public void setRightText(String text) {
        setViewAttribute(mRightTv, text);
    }

    /**
     * 设置传入的textView 的属性设置
     *
     * @param mView
     * @param text
     */
    private void setViewAttribute(TextView mView, String text) {
        mView.setText(text);
        mView.setVisibility(View.VISIBLE);
        mView.setOnClickListener(this);
    }

    /**
     * 获取此界面根视图
     *
     * @return
     */
    public final LinearLayout getRootView() {
        return mRootView;
    }

    /**
     * 获取内容视图
     *
     * @return
     */
    public final View getContentView() {
        return mContentView;
    }

    /**
     * 获取标题栏
     *
     * @return
     */
    public final RelativeLayout getTitleBar() {
        return mTitleBar;
    }

    /**
     * 获取标题文本
     *
     * @return
     */
    public final TextView getTitleTv() {
        return mTitleTv;
    }

    /**
     * 获取标题栏左文本
     *
     * @return
     */
    public final TextView getLeftTv() {
        return mLeftTv;
    }

    /**
     * 获取标题栏右文本
     *
     * @return
     */
    public final TextView getRightTv() {
        return mRightTv;
    }
}
