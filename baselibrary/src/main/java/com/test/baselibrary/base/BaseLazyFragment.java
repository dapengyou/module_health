package com.test.baselibrary.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * 懒加载用于 Fragment+ViewPager
 * Created by lady_zhou on 2018/1/10.
 */

public abstract class BaseLazyFragment extends BaseFragment {
    private View mView;
    //视图是否初始化
    protected boolean isInit = false;
    //视图是否加载
    protected boolean isLoad = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutId() > 0) {
            try {
                mView = inflater.inflate(getLayoutId(), container, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        isInit = true;

        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (!isInit) {
            return;
        }
        if (getUserVisibleHint()) {
            lazyLoad();
            isLoad = true;
        } else {
            isLoad = false;
            stopLoad();
        }

    }

    /**
     * 获得相对应的layoutId
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    protected abstract void lazyLoad();

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以调用此方法
     */
    protected void stopLoad() {
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

}
