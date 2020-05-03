package com.example.midtask.enter.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import androidx.fragment.app.FragmentTransaction;

import com.example.midtask.R;
import com.example.midtask.enter.EnterActivity;
import com.example.midtask.mytools.application.WholeData;
import com.example.midtask.mytools.network.requestion.BTArequest;
import com.example.midtask.mytools.network.requestion.StringReturn;

import org.json.JSONException;
import org.json.JSONObject;

public class MediaFragment extends BaseFragment {
    private static final String TAG = "MediaFragment";

    public ObjFragment lastFragment;
    public int id;
    public PlayListFragment playListFragment;
    public StringReturn playList = new StringReturn(()->{
        try {
            JSONObject jsonObject = new JSONObject(MediaFragment.this.playList.getBack());
            Log.d(TAG, ": "+jsonObject.toString());
            playListFragment = new PlayListFragment(thisActivity,jsonObject.getJSONObject("playlist"),this);
            Message message = new Message();
            message.what = 1;
            MediaFragment.this.handler.sendMessage(message);
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    });

    public MediaFragment(Activity activity,ObjFragment fragment,int id) {
        super(activity);
        lastFragment = fragment;
        this.id = id;
    }

    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:{
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fragment_view,playListFragment);
                    transaction.commit();
                }break;
                case 2:{
                    PlayMusicFragment playMusicFragment = (PlayMusicFragment)msg.obj;
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fragment_view,playMusicFragment);
                    transaction.commit();
                }break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        view.setOnClickListener((v)->{
            load();
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        titleFragment.setTitle("play list");
        titleFragment.setLeftButtonImage(R.mipmap.back_arrow);
        titleFragment.setRightButtonImage(R.mipmap.icon_dots);

        titleFragment.setLeftButtonClick((v)->{
            ((EnterActivity)thisActivity).setFragment(lastFragment);
        });


    }

    private void load(){
        BTArequest.getOnlyEmbody()
                .setcType(BTArequest.TYPE_FORM)
                .fromWeb(WholeData.baseUrl+"/playlist/detail")
                .setWhileTime(1L)
                .setRequestMethod("GET")
                .postData(new StringReturn()
                        .setData("id="+id))
                .getStringReturn(playList)
                .disconnect();
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
