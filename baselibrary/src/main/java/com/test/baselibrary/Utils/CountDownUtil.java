package com.test.baselibrary.Utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.widget.TextView;

import com.test.baselibrary.R;

/**
 * 倒计时
 * Created by lady_zhou on 2018/1/2.
 */

public class CountDownUtil extends CountDownTimer {
    // 秒（1000毫秒）
    private static final int SECOND = 1000;

    // 默认总时长，，60秒
    private static final long DEFAULT_MILLIS_IN_FUTURE = 60 * SECOND;

    // 默认回调onTick(long)时间间隔，，默认1秒
    private static final long DEFAULT_INTERVAL = SECOND;

    private TextView mTextView; // 需要显示倒计时的TextView
    private String countDownText; // 在倒计时期间显示的文本

    private boolean isFormatStr; // 记录倒计时期间显示的文本是否包含%d

    /**
     * 构造方法
     *
     * @param textView 需要显示倒计时的TextView
     *                 显示60s
     */
    public CountDownUtil(TextView textView) {
        this(textView, 0, 0, null);
    }

    /**
     * @param textView 需要显示倒计时的TextView
     * @param totalSec 自定义秒数
     */
    public CountDownUtil(TextView textView, int totalSec) {
        this(textView, totalSec, 0, null);
    }

    /**
     * 构造方法，默认倒计时为60s
     *
     * @param textView      需要显示倒计时的TextView
     * @param countDownText 倒计时显示的文本，如果需要将倒计时剩余数和其他文本混合显示，需要使用格式化字符串，例如：
     *                      ‘%ds后重新发送’，显示效果：20s后重新发送
     */
    public CountDownUtil(TextView textView, String countDownText) {
        this(textView, 0, 0, countDownText);
    }

    /**
     * 构造方法，默认倒计时为60s
     *
     * @param textView      需要显示倒计时的TextView
     * @param countDownText 倒计时显示的文本，如果需要将倒计时剩余数和其他文本混合显示，需要使用格式化字符串，例如：
     *                      ‘%ds后重新发送’，显示效果：20s后重新发送
     * @param totalSec      自定义秒数
     */
    public CountDownUtil(TextView textView, int totalSec, String countDownText) {
        this(textView, totalSec, 0, countDownText);
    }

    /**
     * 构造方法
     *
     * @param textView      需要显示倒计时的TextView
     * @param totalSec      倒计时秒数
     * @param countDownText 倒计时显示的文本，如果需要将倒计时剩余数和其他文本混合显示，需要使用格式化字符串，例如：
     *                      ‘%ds后重新发送’，显示效果：20s后重新发送
     */
    public CountDownUtil(TextView textView, int totalSec, int countDownInterval, String countDownText) {
        super(totalSec <= 0 ? DEFAULT_MILLIS_IN_FUTURE : totalSec * SECOND,
                countDownInterval <= 0 ? DEFAULT_INTERVAL : countDownInterval * SECOND);
        mTextView = textView;
        this.countDownText = countDownText;
        if (!TextUtils.isEmpty(countDownText)) {
            isFormatStr = countDownText.contains("%d");
        }
    }

    /**
     * 固定间隔被调用
     *
     * @param millisUntilFinished
     */
    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setEnabled(false);
        mTextView.setBackgroundResource(R.drawable.bg_gray);  //设置按钮为灰色，这时是不能点击的
        mTextView.setTextColor(Color.BLACK);


        long sec = millisUntilFinished / SECOND;

        if (TextUtils.isEmpty(countDownText)) {
            mTextView.setText(String.valueOf(sec) + "s");
        } else if (isFormatStr) {
            mTextView.setText(String.format(countDownText, sec));
        }
    }

    /**
     * 倒计时完成时被调用
     */
    @Override
    public void onFinish() {
        mTextView.setEnabled(true);
        mTextView.setText("获取验证码");
    }

}
