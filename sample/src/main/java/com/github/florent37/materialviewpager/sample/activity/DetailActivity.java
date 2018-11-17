package com.github.florent37.materialviewpager.sample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.florent37.materialviewpager.sample.R;
import com.github.florent37.materialviewpager.sample.base.BaseActivity;

/**
 * Create by zhaihongyuan
 * E-mail zhaihoy@foxMail.com
 */
public class DetailActivity extends BaseActivity {

    private ImageView img;

    @Override
    protected void initData() {
        Bundle extras = getIntent().getExtras();
        img.setImageResource(extras.getInt("id"));
    }

    @Override
    protected void initView() {
        img = findViewById(R.id.ciamg);


    }

    @Override
    public int initLayout() {
        return R.layout.scanxy;
    }
}
