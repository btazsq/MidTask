package com.example.midtask.enter.other;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midtask.R;
import com.example.midtask.enter.EnterActivity;
import com.example.midtask.enter.fragments.MediaFragment;
import com.example.midtask.enter.fragments.RecommendRecycleViewFragment;
import com.example.midtask.mytools.network.image.MyImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecommendRecycleViewAdapter extends RecyclerView.Adapter<RecommendRecycleViewAdapter.ViewHolder> {
    private static final String TAG = "RecommendRecycleViewAda";

    public RecommendRecycleViewFragment owner;
    public JSONArray jsonData;
    //public MediaFragment mediaFragment;

    public RecommendRecycleViewAdapter(JSONObject jsonObject, RecommendRecycleViewFragment fragment){
        owner = fragment;
        try {
            jsonData = jsonObject.getJSONArray("playlists");
        } catch (JSONException e) {
            Log.d(TAG, e.toString());
        }
        Log.d(TAG, "RecommendRecycleViewAdapter: *******************");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_v_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JSONObject jsonObject;
        try {
            jsonObject = jsonData.getJSONObject(position);
            MyImage.with((View) null)
                    .load(jsonObject.getString("coverImgUrl"))
                    .into(holder.imageView);
            holder.name.setText(jsonObject.getString("name"));
            holder.author.setText(jsonObject.getJSONObject("creator").getString("nickname"));
            holder.mediaFragment = new MediaFragment(owner.thisActivity,owner.owner, jsonObject.getInt("id"));
            holder.view.setOnClickListener((v)->{
                ((EnterActivity)owner.thisActivity).setFragment(holder.mediaFragment);
            });
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public View view;
        ImageView imageView;
        TextView name,author;
        MediaFragment mediaFragment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recommend_v_item_image);
            name = itemView.findViewById(R.id.recommend_v_item_name);
            author = itemView.findViewById(R.id.recommend_v_item_author);
            this.view = itemView;
        }

    }
}
