package com.upyun.rtmptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.upyun.upplayer.UpVideoView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    UpVideoView mUpVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUpVideoView = (UpVideoView) findViewById(R.id.uvv_vido);
        mUpVideoView.setDataSource("rtmp://uppull.b0.upaiyun.com/apphk/streamhk");
        mUpVideoView.start();
    }

    public void change(View view) {
        if (mUpVideoView.isPlaying()) {
            mUpVideoView.stop();
        } else {
            mUpVideoView.start();
        }
    }

    public void refresh(View view) {
        mUpVideoView.refresh();
    }
}
