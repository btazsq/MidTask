package com.example.midtask.mytools.network.image;

import android.graphics.Bitmap;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.midtask.mytools.network.requestion.BTArequest;
import com.example.midtask.mytools.network.requestion.RequestionBuilder;

import java.util.HashMap;

public class MyImage {

    protected static HashMap<String,Bitmap> hashMap = new HashMap<String, Bitmap>();

    public static Bitmap getBitmapBuffer(String key){
        return  hashMap.get(key);
    }

    public static void delBitmapBuffer(){
        hashMap = null;
    }

    public static void test(){

    }

    public static MyImageTool with(){
        if (BTArequest.getOnlyEmbody() == null)
            new RequestionBuilder().build();
        return new MyImageTool();
    }

    public static MyImageTool with(View view){
        if (BTArequest.getOnlyEmbody() == null)
            new RequestionBuilder().build();
        return new MyImageTool(view);
    }

    public static MyImageTool with(AppCompatActivity appCompatActivity){
        if (BTArequest.getOnlyEmbody() == null)
            new RequestionBuilder().build();
        return new MyImageTool(appCompatActivity);
    }

}
