package com.example.midtask.enter.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midtask.R;
import com.example.midtask.enter.other.PlayListRecycleViewAdapter;
import com.example.midtask.enter.other.RecommendRecycleViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlayListFragment extends ObjFragment {
    private static final String TAG = "PlayListFragment";

    public JSONObject jsonData;
    public JSONArray tracks;
    public MediaFragment mediaFragment;

    public PlayListFragment(Activity activity,JSONObject jsonArray,MediaFragment fragment) {
        super(activity);
        jsonData = jsonArray;
        mediaFragment = fragment;
        try {
            tracks = jsonData.getJSONArray("tracks");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_list,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.play_list_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        PlayListRecycleViewAdapter adapter = new PlayListRecycleViewAdapter(this);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
