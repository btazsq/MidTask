package com.example.midtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.midtask.enter.EnterActivity;
import com.example.midtask.enter.fragments.PlayMusicFragment;
import com.example.midtask.enter.other.TestService;
import com.example.midtask.mytools.application.MyApplication;
import com.example.midtask.mytools.network.requestion.RequestionBuilder;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ((MyApplication)getApplication()).getData().uid = 318082831;
        new RequestionBuilder().build();
        login = (Button)findViewById(R.id.login_pager_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EnterActivity.class));
                MainActivity.this.finish();
            }
        });

        //test();
    }

    private void test(){
        startService(new Intent(MainActivity.this, TestService.class));
    }
}
