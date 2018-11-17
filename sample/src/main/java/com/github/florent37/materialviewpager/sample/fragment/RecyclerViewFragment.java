package com.github.florent37.materialviewpager.sample.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.github.florent37.materialviewpager.sample.Api.NetWorkAPI;
import com.github.florent37.materialviewpager.sample.Config.Config;
import com.github.florent37.materialviewpager.sample.R;
import com.github.florent37.materialviewpager.sample.TestRecyclerViewAdapter;
import com.github.florent37.materialviewpager.sample.bean.ImaBean;
import com.github.florent37.materialviewpager.sample.util.PhoneUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import entity.PhoneConnectInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class RecyclerViewFragment extends Fragment {

    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 100;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private String url;

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        //这里获取手机联系人
        checkPermissions();


    }
    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 201);
        } else {
            initViews();
        }
    }

    private void initViews() {
        //test qa
//        final List<Object> items = new ArrayList<>();
//        //check权限
//        for (int i = 0; i < ITEM_COUNT; ++i) {
//            items.add(new Object());
//        }
//        //setup materialviewpagxer
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetWorkAPI netWorkAPI = retrofit.create(NetWorkAPI.class);
        int random = (int) (Math.random()*10);
        int randoms = (int) (Math.random()*10);
        netWorkAPI.getImage(String.valueOf(random),String.valueOf(randoms)).enqueue(new Callback<ImaBean>() {
            @Override
            public void onResponse(Call<ImaBean> call, Response<ImaBean> response) {
                ImaBean body = response.body();
                List<ImaBean.ResultsBean> results = body.getResults();
                url = results.get(new Random().nextInt(results.size())).getUrl();
                if (url!=null){
                    Toast.makeText(getContext(),"1.0版本没有网络哟～",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ImaBean> call, Throwable t) {
                Toast.makeText(getContext(),"尹亮你的网络开小差了，稍等一会",Toast.LENGTH_LONG).show();
            }
        });
        PhoneUtils phoneUtil = new PhoneUtils(getActivity());
        List<PhoneConnectInfo> phoneDtos = phoneUtil.getPhone();
//        //这里的想normalAdapter里传递的是一个对象

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);
        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(new TestRecyclerViewAdapter(url));
        mRecyclerView.setAdapter(new TestRecyclerViewAdapter(phoneDtos));

    }
}
