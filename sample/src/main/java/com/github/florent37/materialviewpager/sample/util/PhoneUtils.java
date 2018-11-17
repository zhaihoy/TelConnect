package com.github.florent37.materialviewpager.sample.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import entity.PhoneConnectInfo;

/**
 * Create by zhaihongyuan
 * E-mail zhaihoy@foxMail.com
 */
public class PhoneUtils {
    // 号码
    public final static String NUM = ContactsContract.CommonDataKinds.Phone.NUMBER;
    // 联系人姓名
    public final static String NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;

    //上下文对象
    private Context context;
    //联系人提供者的uri
    private Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

    public PhoneUtils(Context context){
        this.context = context;
    }

    //获取所有联系人
    public List<PhoneConnectInfo> getPhone(){
        List<PhoneConnectInfo> phoneDtos = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(phoneUri,new String[]{NUM,NAME},null,null,null);
        while (cursor.moveToNext()){
            PhoneConnectInfo phoneDto = new PhoneConnectInfo(cursor.getString(cursor.getColumnIndex(NAME)),cursor.getString(cursor.getColumnIndex(NUM)));
            phoneDtos.add(phoneDto);
        }
        return phoneDtos;
    }

}
