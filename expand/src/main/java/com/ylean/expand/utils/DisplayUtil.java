package com.ylean.expand.utils;

import android.content.Context;

import com.ylean.expand.m;

/**
 * ================================================
 * 作    者：maojunxian
 * 版    本：1.0
 * 创建日期：2017/3/15
 * 描    述：dp/px／sp转化类
 * 修订历史：
 * ================================================
 */
public class DisplayUtil {
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    public static int getDimen(Context context, int dimen) {
        int px = context.getResources().getDimensionPixelOffset(dimen);
        return DimenUtils.getDimenUtils().getDimen(px);
    }

    public static int getDimen(int dimen) {
        int px = m.getInstance().getApplication().getResources().getDimensionPixelOffset(dimen);
        return DimenUtils.getDimenUtils().getDimen(px);
    }

    public static int getDimenUtils(int dimen) {
        return m.getInstance().getApplication().getResources().getDimensionPixelOffset(dimen);
    }
}
