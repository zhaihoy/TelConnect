package com.github.florent37.materialviewpager.sample.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.florent37.materialviewpager.sample.Config.Config;
import com.github.florent37.materialviewpager.sample.MainActivity;
import com.github.florent37.materialviewpager.sample.R;
import com.github.florent37.materialviewpager.sample.base.BaseActivity;
import com.github.florent37.materialviewpager.sample.json.GankImage;
import com.github.florent37.materialviewpager.sample.Api.NetWorkAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Create by zhaihongyuan
 * E-mail zhaihoy@foxMail.com
 */
public class SplashActivity extends BaseActivity {
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setIsshowtitle(true);
        setState(true);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        //localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | ((WindowManager.LayoutParams) localLayoutParams).flags);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetWorkAPI netWorkAPI = retrofit.create(NetWorkAPI.class);
        netWorkAPI.testUrlAndQuery().enqueue(new Callback<GankImage>() {
            @Override
            public void onResponse(Call<GankImage> call, Response<GankImage> response) {
                //响应成功
                if (response.isSuccessful()) {
                    GankImage.ResultsBean resultsBean = response.body().getResults().get(0);
                    Glide.with(getApplicationContext()).load(Uri.parse(resultsBean.getUrl())).into(mImageView);

                }
            }

            @Override
            public void onFailure(Call<GankImage> call, Throwable t) {

            }
        });


    }

    @Override
    protected void initView() {
        mImageView = (ImageView) findViewById(R.id.splash);
        //给图片设置动画
        AlphaAnimation animation = new AlphaAnimation(1f, 0.01f);
        //设置动画执行的时间
        animation.setDuration(8000);
        //控件与动画关联
        mImageView.setAnimation(animation);
        //设置动画的监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始执行时候，可以去加在网络图片
                initData();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                //将此页销毁
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //do nothing
            }
        });

    }

    @Override
    public int initLayout() {
        return R.layout.splash_layout;
    }

}
