package com.example.midtask.enter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.midtask.R;
import com.example.midtask.enter.fragments.ObjFragment;
import com.example.midtask.enter.fragments.PersonalFragment;
import com.example.midtask.enter.fragments.RecommendFragment;
import com.example.midtask.enter.other.MusicService;
import com.example.midtask.mytools.application.MyApplication;
import com.example.midtask.mytools.application.WholeData;
import com.example.midtask.mytools.network.requestion.BTArequest;
import com.example.midtask.mytools.network.requestion.StringReturn;

import org.json.JSONException;
import org.json.JSONObject;

public class EnterActivity extends AppCompatActivity {

    public FragmentManager manager;

    Button recommend,list,personal;

    public RecommendFragment recommendFragment;
    public PersonalFragment personalFragment;
    public MusicService.MyBinder myBinder;

    public MusicService.MyBinder getMyBinder(){
        return myBinder;
    }

    private final ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            setMusicService(((MusicService.MyBinder) service).getService());
            EnterActivity.this.myBinder = (MusicService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            setMusicService(null);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        getSupportActionBar().hide();

        recommend = findViewById(R.id.enter_commend);
        list = findViewById(R.id.enter_list);
        personal = findViewById(R.id.enter_personal);

        manager = getSupportFragmentManager();
        recommendFragment = new RecommendFragment(this);
        personalFragment = new PersonalFragment(this,null);

        Intent intent = new Intent(EnterActivity.this,MusicService.class);
        startService(intent);
        bindService(intent,sc, BIND_AUTO_CREATE);

        startService(new Intent(EnterActivity.this,MusicService.class));

        test();
    }

//    StringReturn personalString = new StringReturn(()->{
//        try {
//            personalFragment = new PersonalFragment(this,new JSONObject(
//                    EnterActivity.this.personalString.getBack()
//            ).getJSONObject("profile"));
//            Message message = new Message();
//            message.what = 2;
//            EnterActivity.this.handler.sendMessage(message);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    });

    private void test(){
        setFragment(recommendFragment);
        recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(recommendFragment);
            }
        });
        personal.setOnClickListener((v)->{
//            BTArequest.getOnlyEmbody()
//                    .setcType(BTArequest.TYPE_FORM)
//                    .fromWeb(WholeData.baseUrl+"/user/detail")
//                    .setWhileTime(5L)
//                    .setRequestMethod("GET")
//                    .postData(new StringReturn()
//                            .setData("id="+((MyApplication)getApplication()).getData().uid))
//                    .getStringReturn(personalString)
//                    .disconnect();
            setFragment(personalFragment);
        });
    }

    public void setFragment(ObjFragment fragment){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_content,fragment);
        transaction.commit();
    }

    public void addFragment(ObjFragment fragment){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_content,fragment);
        transaction.commit();
    }

    public void removeFragment(ObjFragment fragment){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
    }

    public MusicService getMusicService(){
        return ((MyApplication)getApplication()).getData().musicService;
    }

    public void setMusicService(MusicService service){
        ((MyApplication)getApplication()).getData().musicService = service;
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:{

                }break;
                case 2:{
                    setFragment(personalFragment);
                }break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        unbindService(sc);
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
