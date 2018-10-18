package com.zhj.cau.nestedscrollview;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by zhj on 2018/10/8.
 */

public class DisplayUtil {

    /**
     * 将dp值转换为px值
     *
     * @param context 上下文
     * @param dpValue dp单位的长度
     * @return px单位的长度
     */
    public static int dp2px(Context context, int dpValue) {
        //获取屏幕密度
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        //屏幕密度的比例值
        float density = displayMetrics.density;
        //将dp转换为px
        return (int) (dpValue * density + 0.5);
    }

}
