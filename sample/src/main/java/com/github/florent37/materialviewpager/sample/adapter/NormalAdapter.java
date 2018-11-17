package com.github.florent37.materialviewpager.sample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.materialviewpager.sample.R;

import java.util.List;

import entity.PhoneConnectInfo;

/**
 * Create by zhaihongyuan
 * E-mail zhaihoy@foxMail.com
 */
public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.ViewHolder> {


    private Context contexts;
    List<PhoneConnectInfo> phoneDtos;
    ViewHolder viewHolder;
    public NormalAdapter(Context applicationContext, List<PhoneConnectInfo> phoneDtos) {
        System.out.println("1");
        this.contexts = applicationContext;
        this.phoneDtos = phoneDtos;
    }



    @NonNull
    @Override
    public NormalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(contexts).inflate(R.layout.item_main, viewGroup, false);
         viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name_connect.setText(phoneDtos.get(i).getName());
        viewHolder.num_connect.setText(phoneDtos.get(i).getTelPhone());
        //此处可以将头像动态的展示2.0再做

    }
    @Override
    public int getItemCount() {
        return phoneDtos.size();
    }

    //创建ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView num_connect;
        TextView name_connect;
        ImageView icons;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println("2");
             icons = (ImageView) itemView.findViewById(R.id.icons);
            num_connect = (TextView) itemView.findViewById(R.id.num_connect);
            name_connect = (TextView) itemView.findViewById(R.id.name_connect);

        }


    }
}