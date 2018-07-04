package com.test.baselibrary.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 体重和身高的数值显示
 */

public class BodyUtil {
    /**
     * 有间隔的获取体重
     *
     * @return
     */
    public static List<String> getWeightData(int step_weight) { //体重间隔值
        int min_weight = 30;    //体重最小值
        int max_weight = 150;   //体重最大值
        List<String> weightList = new ArrayList<>();//体重数据源
        //计算体重
        int count = (max_weight - min_weight) / step_weight + 1;
        for (int i = 0; i < count; i++) {
            weightList.add(String.valueOf(min_weight + i * step_weight));
        }

        return weightList;
    }

    /**
     * 有间隔的获取身高
     *
     * @return
     */
    public static List<String> getHeightData(int step_height) { //身高体重间隔
        int min_height = 130;   //身高最小值
        int max_height = 250;   //身高最大值
        List<String> heightList = new ArrayList<>();  //身高数据源
        //计算身高
        int heightCount = (max_height - min_height) / step_height + 1;
        for (int i = 0; i < heightCount; i++) {
            heightList.add(String.valueOf(min_height + i * step_height));
        }
        return heightList;
    }

    /**
     * 获取体重数据
     *
     * @return
     */
    public static List<String> getWeightData() {
        int min_weight = 30;    //体重最小值
        int max_weight = 150;   //体重最大值
        List<String> weightList = new ArrayList<>();//体重数据源
        //计算体重
        for (int i = min_weight; i <= max_weight; i++) {
            weightList.add(i + "");
        }

        return weightList;
    }

    public static List<String> getHeightData() {
        int min_height = 130;   //身高最小值
        int max_height = 250;   //身高最大值
        List<String> heightList = new ArrayList<>();  //身高数据源
        //计算身高
        for (int i = min_height; i <= max_height; i++) {
            heightList.add(i + "");
        }
        return heightList;
    }
}