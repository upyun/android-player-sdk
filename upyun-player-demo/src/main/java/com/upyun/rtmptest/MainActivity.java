package com.upyun.rtmptest;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.upyun.upplayer.widget.SimpleVideoView;
import com.upyun.upplayer.widget.UpVideoView;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends Activity {

    String path = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
//    String path = "http://test86400.b0.upaiyun.com/liveph//os/tempLivePhoto.mov";
//    String path = "http://hls3.douyutv.com/live/174831rubuCE4GPg/playlist.m3u8?wsSecret=e9cd09eb8ca11477efc1f669118c//c0&wsTime=1457685701";
//    String path = "http://vevoplaylist-live.hls.adaptive.level3.net/vevo///1/06/prog_index.m3u8";
//    String path = "http://vevoplaylist-live.hls.adaptive.level3.net/vevo/ch1/appleman.m3u8";
//    String path = "/mnt/sdcard/localfile.mp4";

    private static final String TAG = MainActivity.class.getSimpleName();
    SimpleVideoView mSimpleVideoView;
    RelativeLayout.LayoutParams mVideoParams;

    UpVideoView upVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        upVideoView = (UpVideoView) findViewById(R.id.uvv_vido);
        upVideoView.setVideoPath(path);
        upVideoView.setImage(R.drawable.dog);
        upVideoView.setCacheDuration(5000);
    }

    public void toggle(View view) {
        if (upVideoView.isPlaying()) {
            upVideoView.pause();
        } else {
            upVideoView.start();
        }
    }

    public void refresh(View view) {
        upVideoView.resume();
    }

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
            upVideoView.exitFullScreen(this);
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        upVideoView.release(true);
        IjkMediaPlayer.native_profileEnd();
    }
}
