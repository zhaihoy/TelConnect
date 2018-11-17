package com.github.florent37.materialviewpager.sample.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Create by zhaihongyuan
 * E-mail zhaihoy@foxMail.com
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static boolean isshowtitle = true;
    public static boolean isshowstate = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isshowtitle) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        if (isshowstate) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }


        setContentView(initLayout());
//        控件初始化
        initView();
//        设置数据
        initData();
    }

    //是否显示状态栏

    public void setIsshowtitle(boolean isshow) {
        isshowtitle = isshow;
    }

    public void setState(boolean isState) {
        isshowstate = isState;
    }

    protected abstract void initData();

    protected abstract void initView();

    //初始化布局
    public abstract int initLayout();

}
