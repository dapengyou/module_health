package com.test.baselibrary;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.test.baselibrary.Utils.AdressDataUtil;
import com.test.baselibrary.module.Province;

import java.util.ArrayList;

/**
 * Created by lady_zhou on 2018/1/16.
 */

public class MyApplication extends MultiDexApplication {
    private static MyApplication instance;
    private static Context mContext;
    private static String token;

    public static ArrayList<Province> provinces = new ArrayList<>();
    public static ArrayList<ArrayList<String>> citys = new ArrayList<>();
    public static ArrayList<ArrayList<ArrayList<String>>> districts = new ArrayList<>();

    //使用指定的 Header 和 Footer
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.blue, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        this.mContext = this;

        initWheelData();
    }

    private void initWheelData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                provinces = AdressDataUtil.getProviceData(mContext);
                citys = AdressDataUtil.getCityData(provinces);
                districts = AdressDataUtil.getDistricts(provinces);
            }
        }
        ).start();

    }
}
