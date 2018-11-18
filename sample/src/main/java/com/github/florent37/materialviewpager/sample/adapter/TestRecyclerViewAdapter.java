package com.github.florent37.materialviewpager.sample.adapter;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.florent37.materialviewpager.sample.R;
import com.github.florent37.materialviewpager.sample.activity.DetailActivity;
import com.github.florent37.materialviewpager.sample.util.AppUtils;

import java.util.List;

import entity.PhoneConnectInfo;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.ViewHolder> {


    private Context contexts;
    List<PhoneConnectInfo> contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    private ViewHolder views;
    private String url;

    public TestRecyclerViewAdapter(List<PhoneConnectInfo> contents) {
        this.contents = contents;

    }

    public TestRecyclerViewAdapter(String url, Context context) {
        this.url = url;
        this.contexts = context;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public TestRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                views = new ViewHolder(view);
                return views;
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
                views = new ViewHolder(view);
                return views;
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                if (url != null) {
                    //这个基本上是个摆设
                    Glide.with(holder.itemView).load(Uri.parse(url)).into(holder.img);
                } else {
                    holder.img.setImageResource(R.drawable.llf);
                    int i = 1;
                    //设置条目的点击事件
                    if (holder.itemView != null)
                        if (i % 2 != 0) {
                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                          //设置动画
                                    ObjectAnimator alpha = ObjectAnimator.ofFloat(holder.itemView, "alpha", 1.0f, 0.5f, 1.0f);
                                    alpha.setDuration(3000);
                                    alpha.start();
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("id", R.drawable.llf);
                                    final Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                                    intent.putExtras(bundle);
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            new Handler(new Handler.Callback() {
                                                @Override
                                                public boolean handleMessage(Message msg) {
                                                    //实现页面跳转
                                                    holder.itemView.getContext().startActivity(intent);
                                                    return false;
                                                }
                                            }).sendEmptyMessageDelayed(0, 3500);//表示延迟3秒发送任务
                                        }
                                    });
//
                                }
                            });
                        }
                }
                break;
            case TYPE_CELL:
                //此处可以将头像动态的展示2.0再做break;
                holder.name_connect.setText(contents.get(position).getName());
                holder.num_connect.setText(contents.get(position).getTelPhone());
                //此处可以将头像动态的展示2.0再做
                //设置条目的点击事件
                if (holder.itemView != null) {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //
                            ObjectAnimator alpha = ObjectAnimator.ofFloat(holder.itemView, "alpha", 1.0f, 0.5f, 1.0f);
                            alpha.setDuration(1000);
                            alpha.start();
                            //拿着手机号打电话  防止过快点击
                            if (!AppUtils.isFastDoubleClick()) {
                                //Android 6.0之后需要用户动态申请权限
                                call(holder.itemView.getContext(), "tel:" + contents.get(position).getTelPhone());
                            }
                        }
                    });
                }
        }

    }

    private void call(Context context, String telPhone) {

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(telPhone));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(intent);

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView num_connect;
        TextView name_connect;
        ImageView icons;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println("2");
            icons = (ImageView) itemView.findViewById(R.id.icons);
            num_connect = (TextView) itemView.findViewById(R.id.num_connect);
            name_connect = (TextView) itemView.findViewById(R.id.name_connect);
            img = (ImageView) itemView.findViewById(R.id.img);


        }
    }

    //给条目设置点击事件
    private ItemClickListener mItemClickListener;

    public interface ItemClickListener {
        public void onItemClick(int position);
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;

    }
}