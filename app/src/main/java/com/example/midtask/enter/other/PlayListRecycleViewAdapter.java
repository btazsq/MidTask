package com.example.midtask.enter.other;

import android.annotation.SuppressLint;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midtask.R;
import com.example.midtask.enter.fragments.ObjFragment;
import com.example.midtask.enter.fragments.PlayListFragment;
import com.example.midtask.enter.fragments.PlayMusicFragment;

import org.json.JSONException;

public class PlayListRecycleViewAdapter extends RecyclerView.Adapter<PlayListRecycleViewAdapter.ViewHolder> {
    private static final String TAG = "PlayListRecycleViewAdapter";

    PlayListFragment owner;

    public PlayListRecycleViewAdapter(PlayListFragment objFragment){
        owner = objFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.play_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.num.setText((position+1)+"");
        try {
            holder.name.setText(owner.tracks.getJSONObject(position).getString("name"));
            int totalTime = owner.tracks.getJSONObject(position).getInt("dt")/1000;
            holder.time.setText((totalTime/60)+":"+(totalTime%60));
            holder.view.setOnClickListener((v)->{
                Message message = new Message();
                message.what = 2;
                message.obj = new PlayMusicFragment(owner.thisActivity,owner.tracks,position);
                owner.mediaFragment.handler.sendMessage(message);
            });
        } catch (Exception e) {
            Log.d(TAG, "onBindViewHolder: "+e.toString());
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ******"+owner.tracks.length());
        return owner.tracks.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public View view;
        public TextView num,name,time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            num = itemView.findViewById(R.id.play_list_item_num);
            name = itemView.findViewById(R.id.play_list_item_name);
            time = itemView.findViewById(R.id.play_list_item_time);
        }

    }

}
