package com.example.midtask.enter.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.midtask.R;
import com.example.midtask.enter.EnterActivity;
import com.example.midtask.mytools.application.MyApplication;
import com.example.midtask.mytools.application.WholeData;
import com.example.midtask.mytools.network.requestion.BTArequest;
import com.example.midtask.mytools.network.requestion.StringReturn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class PlayMusicFragment extends ObjFragment {
    private static final String TAG = "PlayMusicFragment";

    public SeekBar seekBar;
    public JSONArray tracks;
    public int id;
    boolean isContinue = false;

    StringReturn music = new StringReturn(()->{
        if (this.music.getBack()!=null){
            try {
                String url = new JSONObject(this.music.getBack()).getJSONArray("data").getJSONObject(0).getString("url");
                Message message = new Message();
                message.obj = url;
                ((EnterActivity)thisActivity).myBinder.setPlayMusicFragment(PlayMusicFragment.this);
                ((EnterActivity)thisActivity).myBinder.resetMusic(url);
                isContinue = false;
                Log.d("****************", this.music.getBack());
                //PlayMusicFragment.this.handler.sendMessage(message);
            } catch (JSONException e) {
                Log.d("music******", ": "+e.toString());
            }
        }
    });

//    @SuppressLint("HandlerLeak")
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            String url = (String) msg.obj;
//            ((MyApplication)thisActivity.getApplication()).getData().musicService.resetMusic(url);
//            ((MyApplication)thisActivity.getApplication()).getData().musicService.setPlayMusicFragment(PlayMusicFragment.this);
//            ((MyApplication)thisActivity.getApplication()).getData().musicService.mp.start();
//            isContinue = false;
//            Log.d("********", "load--"+isContinue);
//        }
//    };

    public PlayMusicFragment(Activity activity,JSONArray array,int id) {
        super(activity);
        tracks = array;
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media,container,false);
        Log.d(TAG, "PlayMusicFragment: *********"+id);
        seekBar = view.findViewById(R.id.media_seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (!fromUser)return;
                ((EnterActivity)thisActivity).myBinder.getService().mp.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        view.setOnClickListener((v)->{
//            load();
//        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        isContinue = true;
        new Thread(()->{
            while (isContinue){
                load();
                Log.d("********", "load--"+isContinue);
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void load(){
        try {
            BTArequest.getOnlyEmbody()
                    .setcType(BTArequest.TYPE_FORM)
                    .fromWeb(WholeData.baseUrl+"/song/url")
                    .setWhileTime(5L)
                    .setRequestMethod("GET")
                    .postData(new StringReturn()
                            .setData("id="+tracks.getJSONObject(id).getString("id")+"&br=320000"))
                    .getStringReturn(music)
                    .disconnect();
        } catch (JSONException e) {
            Log.d("Music******", "load: "+e.toString());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //handler.removeCallbacksAndMessages(null);
    }
}
