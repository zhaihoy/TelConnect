package com.github.florent37.materialviewpager.sample.Api;



import com.github.florent37.materialviewpager.sample.bean.ImaBean;
import com.github.florent37.materialviewpager.sample.json.GankImage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Create by zhaihongyuan
 * E-mail zhaihoy@foxMail.com
 */
public interface NetWorkAPI {
    //创建Retrofit 接口工具类
    @GET("13/1")
    Call<GankImage> testUrlAndQuery();
    @GET("{user}/{user}")
    Call<ImaBean> getImage(@Path("user") String pa,@Path("user") String path);

}
