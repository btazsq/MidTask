package com.example.midtask.enter.fragments;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.midtask.R;

public class TitleFragment extends ObjFragment {

    ImageButton leftButton,rightButton;
    TextView title;

    public TitleFragment(Activity activity) {
        super(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title,container,false);
        leftButton = view.findViewById(R.id.title_left_button);
        rightButton = view.findViewById(R.id.title_right_button);
        title = view.findViewById(R.id.title_text);
        return view;
    }

    public void setLeftButtonImage(int id){
        Drawable drawable = getResources().getDrawable(R.mipmap.back_arrow);
        drawable.setBounds(0, 0, 0, 0);
        leftButton.setImageDrawable(drawable);
    }

    public void setRightButtonImage(int id){
        Drawable drawable = getResources().getDrawable(id);
        drawable.setBounds(0,0,0,0);
        rightButton.setImageDrawable(drawable);
    }

    public void setTitle(String text){
        title.setText(text);
    }

    public void setLeftButtonClick(View.OnClickListener onClickListener){
        leftButton.setOnClickListener(onClickListener);
    }

    public void setRightButtonClick(View.OnClickListener onClickListener){
        rightButton.setOnClickListener(onClickListener);
    }

}
