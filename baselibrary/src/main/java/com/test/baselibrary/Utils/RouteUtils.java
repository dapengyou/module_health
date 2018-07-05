package com.test.baselibrary.Utils;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by lx on 17-10-24.
 * 路由的工具类
 */

public class RouteUtils {
    public static final String Home_Fragment_Main = "/home/main";
    public static final String Hospital_Fragment_Main = "/hospital/main";
    public static final String Hot_Fragment_Main = "/hot/main";
    public static final String Report_Fragment_Main = "/report/main";
    public static final String Mine_Fragment_Main = "/mine/main";

    public static Fragment getHomeFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(Home_Fragment_Main).navigation();
        return fragment;
    }

    public static Fragment getHospitalFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(Hospital_Fragment_Main).navigation();
        return fragment;
    }

    public static Fragment getHotFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(Hot_Fragment_Main).navigation();
        return fragment;
    }

    public static Fragment getReportFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(Report_Fragment_Main).navigation();
        return fragment;
    }

    public static Fragment getMineFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(Mine_Fragment_Main).navigation();
        return fragment;
    }

}
