package com.example.midtask.mytools.application;

import android.annotation.SuppressLint;
import android.app.Application;

@SuppressLint("Registered")
public class MyApplication extends Application {

    private WholeData data = new WholeData();

    public WholeData getData(){
        return data;
    }

    public void resetData(){
        data = null;
        data = new WholeData();
    }

}
