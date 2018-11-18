package com.github.florent37.materialviewpager.sample.util;



public final class AppUtils {
    private AppUtils() {

    }

    private static long mLastClickTime;// 用户判断多次点击的时间

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (Math.abs(time - mLastClickTime) < 500) {
            return true;
        }
        mLastClickTime = time;
        return false;
    }
}