package com.example.midtask.enter.other;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.midtask.enter.fragments.PlayMusicFragment;

public class MusicService extends Service {
    private static final String TAG = "MusicService";

    public final IBinder binder = new  MyBinder();
    public MediaPlayer mp = new MediaPlayer();
    public PlayMusicFragment playMusicFragment;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void playOrPause(){
        if (mp.isPlaying()){
            mp.pause();
        }else {
            mp.start();
        }
    }

//    public void stop(){
//        mp.stop();
//        try {
//            mp.prepare();
//            mp.seekTo(0);
//        } catch (IOException e) {
//            Log.d(TAG, e.toString());
//        }
//    }

    public void resetMusic(String path){
        mp.stop();
        try {
            mp.reset();
            mp.setDataSource(path);
            mp.prepareAsync();
            mp.seekTo(0);
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

    }

    public void setPlayMusicFragment(PlayMusicFragment playMusicFragment){
        this.playMusicFragment = playMusicFragment;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                playMusicFragment.seekBar.setMax(mp.getDuration());
                mp.start();
            }
        });
        handler.post(updateUI);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public class MyBinder extends Binder{

        public MusicService getService(){
            return MusicService.this;
        }

        public void resetMusic(String path){
            MusicService.this.resetMusic(path);
        }

        public void setPlayMusicFragment(PlayMusicFragment playMusicFragment){
            MusicService.this.setPlayMusicFragment(playMusicFragment);
        }
    }

    private Runnable updateUI = new Runnable() {
        @Override
        public void run() {
            //获取歌曲进度并在进度条上展现
            if (playMusicFragment != null){
                playMusicFragment.seekBar.setProgress(mp.getCurrentPosition());
            }
            handler.postDelayed(updateUI, 1000);
        }
    };

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
