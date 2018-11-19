package com.github.florent37.materialviewpager.sample.adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.florent37.materialviewpager.sample.Myapplication;
import com.github.florent37.materialviewpager.sample.R;
import com.github.florent37.materialviewpager.sample.util.GetStringListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhaihongyuan
 * E-mail zhaihoy@foxMail.com
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {
    Context context;
    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    private MyRecycleViewAdapter.ViewHolder views;
    private List<String> strList;
    private String string;
    private ArrayList<String> mList;

    //添加构造
    public MyRecycleViewAdapter(Context applicationContext) {
        this.context = applicationContext;
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

    //创建布局的地方
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        System.out.println("===" + viewType);
        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                views = new MyRecycleViewAdapter.ViewHolder(view);
                return views;
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_recyclerview_pc, parent, false);
                views = new MyRecycleViewAdapter.ViewHolder(view);
                return views;
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if (holder.editText != null) {
            MyEditTextChangeListener myEditTextChangeListener = new MyEditTextChangeListener();
            holder.editText.addTextChangedListener(myEditTextChangeListener);
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //拨打打电话
                    //从播放的列表中拿出第一个
                    if (strList == null) {
                        return;
                    }
                    for (int i = 0; i < mList.size(); i++) {
                        if (mList.get(i).length() == 11 && mList.size() > 0) {
                            //从列表中拿出
                            string = mList.get(0);
                        }
                    }
                    if (holder.itemView != null) {
                        if (string==null){
                            Toast.makeText(holder.itemView.getContext(),"您的手机号有误，请核对",Toast.LENGTH_SHORT).show();
                        }else {
                            call(holder.itemView.getContext(), "tel:" + string);
                        }
                    }
                }
            });
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


    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final EditText editText;
        private final Button btn;

        public ViewHolder(View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.edt);
            btn = itemView.findViewById(R.id.bt_play);
        }
    }

    /*-------------------------------------------------------------------------------------------*/
    class MyEditTextChangeListener implements TextWatcher {
        private String TAG = this.getClass().getName();

        /**
         * 编辑框的内容发生改变之前的回调方法
         */
        @SuppressLint("LongLogTag")
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.i("MyEditTextChangeListener", "beforeTextChanged---" + charSequence.toString());
        }

        /**
         * 编辑框的内容正在发生改变时的回调方法 >>用户正在输入
         * 我们可以在这里实时地 通过搜索匹配用户的输入
         */
        @SuppressLint("LongLogTag")
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.i("MyEditTextChangeListener", "onTextChanged---" + charSequence.toString());
        }


        /**
         * 编辑框的内容改变以后,用户没有继续输入时 的回调方法
         */
        @SuppressLint("LongLogTag")
        @Override
        public void afterTextChanged(Editable editable) {
            Log.i("MyEditTextChangeListener", "afterTextChanged---");
            //本来想做个工具类

            strList = GetStringListUtils.getStrList(editable.toString(), 11);
             mList= new ArrayList<>();
            for (int i = 0; i < strList.size(); i++) {
                if (strList.get(i).length()==11){
                    mList.add(strList.get(i));
                }
            }

        }
/*--------------------------------------------------------------------------------------------------*/
    }
}
