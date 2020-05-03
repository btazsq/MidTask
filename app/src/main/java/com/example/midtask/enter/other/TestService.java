package com.example.midtask.enter.other;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class TestService extends Service {
    private MediaPlayer mediaPlayer = new MediaPlayer();

    public TestService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            mediaPlayer.setDataSource("http://m8.music.126.net/20200501003928/e8e546cd221a75affe93c30812742d01/ymusic/0ab9/78f7/2e9c/5f061ff558e633304b8c95182bb8578b.mp3");
            mediaPlayer.prepare();
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
