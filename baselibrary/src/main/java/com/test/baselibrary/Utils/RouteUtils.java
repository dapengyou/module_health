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

    public static String getHomeTagFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(Home_Fragment_Main).navigation();

        String tag = fragment.getClass().getSimpleName();

        return tag;
    }

    public static String getHospitalTagFragment() {
        String tag = ARouter.getInstance().build(Hospital_Fragment_Main).toString();
        return tag;
    }

    public static String getMineTagFragment() {
        String tag = ARouter.getInstance().build(Mine_Fragment_Main).toString();
        return tag;
    }
//    /**
//     * 跳转到商品详情的页面
//     * @param goodName
//     */
//    public static void startGoodDetailActivity(String goodName) {
//        ARouter.getInstance().build(GoodDetail_Activity_Main).withString("goodName",goodName).navigation();
//    }
//    public static void startLoginActivity() {
//        ARouter.getInstance().build(User_Activity_Login).navigation();
//    }
}
