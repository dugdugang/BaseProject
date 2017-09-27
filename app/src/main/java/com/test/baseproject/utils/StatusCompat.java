package com.test.baseproject.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;


/**
 * Created by dg on 2017/6/4.
 * 显示模式
 */

public class StatusCompat {
    public final int FULL_STATUS_BAR = 0;
    public final int COLOR_STATUS_BAR = 1;
    public final int FULL_SCREEN = 2;

    /**
     * @param activity
     * @param mode        模式：0全屏状态栏模式，1着色状态栏模式，2全屏模式
     * @param statusColor 颜色值
     */
    public static void setStatusColor(Activity activity, int mode, int statusColor) {
        if (mode == 2) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
            return;
        }
        //判断版本大于等于5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (mode == 0) {
                fullScreenUp21(activity, statusColor);
            } else {

                toColorUp21(activity, statusColor);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (mode == 0) {

                fullScreenDown21(activity, statusColor);
            } else {

                toColorDown21(activity, statusColor);
            }

        }

    }

    /**
     * 5.0已上全屏模式
     */
    private static void fullScreenUp21(Activity activity, int statusColor) {
        Window window = activity.getWindow();
        //设置透明状态栏,这样才能让 ContentView 向上
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= 21) {
            //设置状态栏颜色
            window.setStatusBarColor(statusColor);
        }
        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }
    }

    /**
     * 5.0以下上全屏模式
     */

    private static void fullScreenDown21(Activity activity, int statusColor) {
        Window window = activity.getWindow();
        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);

        //首先使 ChildView 不预留空间
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }

        int statusBarHeight = getStatusBarHeight(activity);
        //需要设置这个 flag 才能设置状态栏
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //避免多次调用该方法时,多次移除了 View
        if (mChildView != null && mChildView.getLayoutParams() != null && mChildView.getLayoutParams().height == statusBarHeight) {
            //移除假的 View.
            mContentView.removeView(mChildView);
            mChildView = mContentView.getChildAt(0);
        }
        if (mChildView != null) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
            //清除 ChildView 的 marginTop 属性
            if (lp != null && lp.topMargin >= statusBarHeight) {
                lp.topMargin -= statusBarHeight;
                mChildView.setLayoutParams(lp);
            }
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 5.0已上着色模式
     */
    private static void toColorUp21(Activity activity, int statusColor) {
        Window window = activity.getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= 21) {
            //设置状态栏颜色
            window.setStatusBarColor(statusColor);
        }

        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
            ViewCompat.setFitsSystemWindows(mChildView, true);
        }
    }

    /**
     * 5.0以下上着色模式
     */
    private static void toColorDown21(Activity activity, int statusColor) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        int statusBarHeight = getStatusBarHeight(activity);

        View mTopView = mContentView.getChildAt(0);
        if (mTopView != null && mTopView.getLayoutParams() != null && mTopView.getLayoutParams().height == statusBarHeight) {
            //避免重复添加 View
            mTopView.setBackgroundColor(statusColor);
            return;
        }
        //使 ChildView 预留空间
        if (mTopView != null) {
            ViewCompat.setFitsSystemWindows(mTopView, true);
        }

        //添加假 View
        mTopView = new View(activity);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        mTopView.setBackgroundColor(statusColor);
        mContentView.addView(mTopView, 0, lp);
    }


}
