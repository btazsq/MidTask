package com.example.midtask.enter.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.midtask.R;

public class BaseFragment extends ObjFragment {

    public FragmentManager manager;

    View media;
    TitleFragment titleFragment;

    ObjFragment objFragment = null;

    public BaseFragment(Activity activity) {
        super(activity);
        titleFragment = new TitleFragment(activity);
    }

    public BaseFragment(Activity activity,ObjFragment objFragment) {
        this(activity);
        this.objFragment = objFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container,false);

        manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.fragment_title,titleFragment);
        transaction.commit();

        media = view.findViewById(R.id.fragment_media);
        media.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (objFragment!=null){
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_view,objFragment);
            transaction.commit();
        }
    }
}
