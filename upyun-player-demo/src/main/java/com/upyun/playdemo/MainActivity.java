package com.upyun.playdemo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.upyun.upplayer.widget.UpVideoView;

public class MainActivity extends Activity {

       //String path = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
    String path = "rtmp://testlivesdk.b0.upaiyun.com/live/myapp11";
//    String path = "rtmp://testlivesdk.b0.upaiyun.com/live/upyunab";
//    String path = "rtmp://testlivesdk.b0.upaiyun.com/live/upyunaa";
//    String path = "rtmp://testlivesdk.b0.upaiyun.com/live/test131";

    private static final String TAG = MainActivity.class.getSimpleName();
    RelativeLayout.LayoutParams mVideoParams;

    UpVideoView upVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upVideoView = (UpVideoView) findViewById(R.id.uvv_vido);

        //设置默认缓存区大小 (需在setVideoPath 或者 resume 前执行生效)
        upVideoView.setBufferSize(0);

        //设置背景图片
        upVideoView.setImage(R.drawable.dog);

        //设置播放地址
        upVideoView.setVideoPath(path);

        //开始播放
        upVideoView.start();
    }

    public void toggle(View view) {
        if (upVideoView.isPlaying()) {

            //暂停播放
            upVideoView.pause();

        } else {

            //开始播放
            upVideoView.start();
        }
    }

    public void refresh(View view) {

        // 重新开始播放器
        upVideoView.resume();
        upVideoView.start();
    }

    //全屏播放
    public void fullScreen(View view) {
        upVideoView.fullScreen(this);
    }

    private void fullScreen() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(metrics.widthPixels, metrics.heightPixels);
        mVideoParams = (RelativeLayout.LayoutParams) upVideoView.getLayoutParams();
        upVideoView.setLayoutParams(params);
        upVideoView.getTrackInfo();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {

        if (upVideoView.isFullState()) {
            //退出全屏
            upVideoView.exitFullScreen(this);
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        upVideoView.release(true);
    }
}
