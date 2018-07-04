package com.test.baselibrary.Utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by lady_zhou on 2017/12/26.
 */

public class NumberUtils {
    /**
     *
     setScale(2);//表示保留2位小数，默认用四舍五入方式

     setScale(2,BigDecimal.ROUND_DOWN);//直接删除多余的小数位  11.116约=11.11

     setScale(2,BigDecimal.ROUND_UP);//临近位非零，则直接进位；临近位为零，不进位。11.114约=11.12

     setScale(2,BigDecimal.ROUND_HALF_UP);//四舍五入 2.335约=2.33，2.3351约=2.34

     setScaler(2,BigDecimal.ROUND_HALF_DOWN);//四舍五入；2.335约=2.33，2.3351约=2.34，11.117约11.12

     */
    //方法一，四舍五入
    public static String keepTwoByBigDecimal(double number){
        BigDecimal bigDecimal   =   new   BigDecimal(number);
        double  s  =   bigDecimal.setScale(2).doubleValue();
        return String.valueOf(s);
    }

    //方法二
    public static String keepTwoByDecimalFormat(double number) {
        DecimalFormat df = new java.text.DecimalFormat("#.00");
        String s = df.format(number);//默认String.format会自动四舍五入
        return doubleTypeRep(s);
    }

    /**
     * 去掉double字符串多余的0
     *
     * @param str
     * @return
     */

    public static String doubleTypeRep(String str) {
        if (!str.contains(".")) {
            return str;
        }
        String[] strArr = str.split("\\.");
        String end = strArr[1];
        if (end.matches("[0]{2}")) {
            return strArr[0];
        } else if (end.matches("[0-9][0]")) {
            return str.substring(0, str.length() - 1);
        } else if (end.matches("[0]")) {
            return strArr[0];
        }

        return str;

    }

}
