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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.example.midtask.R;
import com.example.midtask.mytools.application.WholeData;
import com.example.midtask.mytools.network.image.MyThreadTask;
import com.example.midtask.mytools.network.requestion.BTArequest;
import com.example.midtask.mytools.network.requestion.StringReturn;

import org.json.JSONException;
import org.json.JSONObject;

public class RecommendFragment extends BaseFragment {
    private static final String TAG = "RecommendFragment";

    RecommendRecycleViewFragment recycleViewFragment;

    StringReturn recommendList = new StringReturn(()->{
        try {
            recycleViewFragment.recommendList = new JSONObject(RecommendFragment.this.recommendList.getBack());
            RecommendFragment.this.handler.sendMessage(new Message());
            //Toast.makeText(thisActivity,RecommendFragment.this.recommendList.getBack(),Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    });

    StringReturn banner = new StringReturn(()->{
        try {
            recycleViewFragment.banner = new JSONObject(RecommendFragment.this.banner.getBack());
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    });

    public RecommendFragment(Activity activity) {
        super(activity);
        recycleViewFragment = new RecommendRecycleViewFragment(thisActivity);
        recycleViewFragment.owner = this;
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @SuppressLint("ResourceType")
        @Override
        public void handleMessage(@NonNull Message msg) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_view,recycleViewFragment);
            transaction.commit();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        view.setOnClickListener((v)->{
            BTArequest.getOnlyEmbody()
                    .setcType(BTArequest.TYPE_FORM)
                    .fromWeb(WholeData.baseUrl+"/album/newest")
                    .setWhileTime(1L)
                    .setRequestMethod("GET")
                    .getStringReturn(banner)
                    .disconnect();
            BTArequest.getOnlyEmbody()
                    .setcType(BTArequest.TYPE_FORM)
                    .fromWeb(WholeData.baseUrl+"/top/playlist")
                    .setWhileTime(1L)
                    .setRequestMethod("GET")
                    .postData(new StringReturn()
                            .setData("limit=20"))
                    .getStringReturn(recommendList)
                    .disconnect();
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        titleFragment.setRightButtonImage(R.mipmap.home_search);
        titleFragment.setTitle("Home");
        BTArequest.getOnlyEmbody()
                .setcType(BTArequest.TYPE_FORM)
                .fromWeb(WholeData.baseUrl+"/album/newest")
                .setWhileTime(1L)
                .setRequestMethod("GET")
                .getStringReturn(banner)
                .disconnect();
        BTArequest.getOnlyEmbody()
                .setcType(BTArequest.TYPE_FORM)
                .fromWeb(WholeData.baseUrl+"/top/playlist")
                .setWhileTime(1L)
                .setRequestMethod("GET")
                .postData(new StringReturn()
                        .setData("limit=20"))
                .getStringReturn(recommendList)
                .disconnect();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
