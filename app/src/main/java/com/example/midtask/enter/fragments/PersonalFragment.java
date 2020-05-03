package com.example.midtask.enter.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.midtask.R;
import com.example.midtask.mytools.network.image.MyImage;

import org.json.JSONArray;
import org.json.JSONObject;

public class PersonalFragment extends ObjFragment {

    View red,blue,green;
    ImageView mainImage;
    JSONObject data;

    public PersonalFragment(Activity activity, JSONObject jsonObject) {
        super(activity);
        data = jsonObject;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal,container,false);
        red = view.findViewById(R.id.icon_red);
        blue = view.findViewById(R.id.icon_blue);
        green = view.findViewById(R.id.icon_green);
        View parent = (View) red.getParent();
        red.bringToFront();
        blue.bringToFront();
        green.bringToFront();
        parent.requestLayout();
        parent.invalidate();
        mainImage = view.findViewById(R.id.personal_image);
        MyImage.with(view)
                .load("http://p1.music.126.net/entTS9DFUG2U5LYUWXMrmw==/18598239185642962.jpg")
                .into(mainImage);
        return view;
    }
}
