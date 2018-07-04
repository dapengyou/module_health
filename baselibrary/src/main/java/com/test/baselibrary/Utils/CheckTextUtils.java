package com.test.baselibrary.Utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lady_zhou on 2017/12/27.
 */

public class CheckTextUtils {

    /**
     * 判断手机号格式
     * * 手机号码:
     * 13[0-9], 14[5,7], 15[0, 1, 2, 3, 5, 6, 7, 8, 9], 17[0, 1, 6, 7, 8], 18[0-9]
     *
     * @param mobiles
     * @return
     */
    public static boolean checkMobile(String mobiles) {
        Pattern p = Pattern
                .compile("^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    /**
     * 判断密码格式  6-20位 数字 字母
     *
     * @param pwd
     * @return
     */
    public static boolean checkPassword(String pwd) {
        Pattern p = Pattern
                .compile("^[0-9a-zA-Z]{6,20}$");
        Matcher m = p.matcher(pwd);

        return m.matches();
    }

    /**
     * 判断昵称是否符合汉字姓名命名规则
     *
     * @param userName 用户真是姓名
     * @return
     */
    public static boolean checkChinese(String userName) {
        Pattern p = Pattern
                .compile("^[\\u4e00-\\u9fa5]{2,4}$");
        Matcher m = p.matcher(userName);

        return m.matches();
    }

    /**
     * 返回字符长度
     *
     * @param value
     * @return
     */
    public static int String_length(String value) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * 判断学校是否有有字符限制，只容许英文和中文
     *
     * @param school 用户昵称
     * @return
     */
    public static boolean checkSchool(String school) {
        Pattern p = Pattern
                .compile("^[a-zA-Z\\u4e00-\\u9fa5]{2,20}$");
        Matcher m = p.matcher(school);
        return m.matches();
    }

    /**
     * 验证是否是邮箱
     *
     * @param email 邮箱地址
     * @return 如果是邮箱返回true，否则返回false
     */
    public static boolean checkEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        String regExp = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|" +
                "(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        return email.matches(regExp);
    }

    /**
     * 字符串半角转换为全角(半角空格为32,全角空格为12288.其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248)
     *
     * @param input 需要转换的字符串
     * @return 转换后的字符串
     */
    public static String halfToFull(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 32) { //半角空格
                c[i] = (char) 12288;
                continue;
            }
            if (c[i] > 32 && c[i] < 127) {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }

    /**
     * 字符串全角转换为半角(全角空格为12288，半角空格为32.其他字符全角(65281-65374)与半角(33-126)的对应关系是：均相差65248)
     *
     * @param input 需要转换的字符串
     * @return 转换后的字符串
     */
    public static String fullToHalf(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) { //全角空格
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }

}
