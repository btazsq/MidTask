package com.example.midtask.enter.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midtask.R;
import com.example.midtask.enter.other.RecommendRecycleViewAdapter;

import org.json.JSONObject;

public class RecommendRecycleViewFragment extends ObjFragment {

    public JSONObject recommendList,banner;
    public ObjFragment owner;

    public RecommendRecycleViewFragment(Activity activity) {
        super(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend_recycleview,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recommend_recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        RecommendRecycleViewAdapter adapter = new RecommendRecycleViewAdapter(recommendList,this);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
