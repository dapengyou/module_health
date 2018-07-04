package com.test.baselibrary.Utils;

import android.content.Context;


import com.alibaba.fastjson.JSON;
import com.test.baselibrary.module.Province;

import java.util.ArrayList;

/**
 * 提供地址 工作等个人信息
 */
public class AdressDataUtil {

    /**
     * 省份信息
     *
     * @param context
     * @return
     */

    public static ArrayList<Province> getProviceData(Context context) {
        String region = FileUtil.readStringFromAsset(context, "region.json");
        ArrayList<Province> provinces = (ArrayList<Province>) JSON.parseArray(region, Province.class);
        return provinces;
    }

    /**
     * 根据省份获取所有的城市
     *
     * @param provinces
     * @return
     */
    public static ArrayList<ArrayList<String>> getCityData(ArrayList<Province> provinces) {
        ArrayList<ArrayList<String>> citys = new ArrayList<>();
        for (int i = 0; i < provinces.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            for (int c = 0; c < provinces.get(i).getCitys().size(); c++) {//遍历该省份的所有城市
                String CityName = provinces.get(i).getCitys().get(c).getRegionName();
                cityList.add(CityName);//添加城市
            }
            /**
             * 添加城市数据
             */
            citys.add(cityList);
        }
        return citys;
    }

    public static ArrayList<ArrayList<ArrayList<String>>> getDistricts(ArrayList<Province> provinces) {
        ArrayList<ArrayList<ArrayList<String>>> districts = new ArrayList<>();

        for (int i = 0; i < provinces.size(); i++) {//遍历省份
            ArrayList<ArrayList<String>> districtsList = new ArrayList<>();//该省的区列表（第三级）
            for (int c = 0; c < provinces.get(i).getCitys().size(); c++) {//遍历该省份的所有城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (provinces.get(i).getCitys().get(c).getDistricts() == null
                        || provinces.get(i).getCitys().get(c).getDistricts().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < provinces.get(i).getCitys().get(c).getDistricts().size(); d++) {//该城市对应地区所有数据
                        String AreaName = provinces.get(i).getCitys().get(c).getDistricts().get(d).getRegionName();

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                districtsList.add(City_AreaList);
            }
            districts.add(districtsList);
        }
        return districts;
    }
}