package com.github.florent37.materialviewpager.sample;

import android.app.Application;
import android.content.Context;


public class Myapplication extends Application
{
    private static Context context;
    
    @Override
    public void onCreate()
    {
        super.onCreate();
        
        context = getAppContext();
    }
    
    public static Context getAppContext()
    {
        return context;
    }
}