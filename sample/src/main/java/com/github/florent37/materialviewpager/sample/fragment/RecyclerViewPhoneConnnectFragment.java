package com.github.florent37.materialviewpager.sample.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.github.florent37.materialviewpager.sample.R;
import com.github.florent37.materialviewpager.sample.adapter.MyRecycleViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by zhaihongyuan
 * E-mail zhaihoy@foxMail.com
 */
public class RecyclerViewPhoneConnnectFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.ry_view)
    RecyclerView ryView;
    Unbinder unbinder1;


    //对外暴露方法

    public static RecyclerViewPhoneConnnectFragment newInstance() {

        return new RecyclerViewPhoneConnnectFragment();
    }

    //创建布局

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview_phonecon, container, false);
        unbinder1 = ButterKnife.bind(this, view);
        return view;
    }
    //设置数据ect操作

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ryView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ryView.setHasFixedSize(true);
        ryView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        ryView. setAdapter(new MyRecycleViewAdapter(getContext()));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
