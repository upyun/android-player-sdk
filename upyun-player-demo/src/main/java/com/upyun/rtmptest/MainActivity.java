package com.upyun.rtmptest;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.upyun.upplayer.widget.SimpleVideoView;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class MainActivity extends Activity {

    String path = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
//    String path = "http://test86400.b0.upaiyun.com/liveph//os/tempLivePhoto.mov";
//    String path = "http://hls3.douyutv.com/live/174831rubuCE4GPg/playlist.m3u8?wsSecret=e9cd09eb8ca11477efc1f669118c//c0&wsTime=1457685701";
//    String path = "http://vevoplaylist-live.hls.adaptive.level3.net/vevo///1/06/prog_index.m3u8";
//    String path = "http://vevoplaylist-live.hls.adaptive.level3.net/vevo/ch1/appleman.m3u8";
//    String path = "/mnt/sdcard/localfile.mp4";

    private static final String TAG = MainActivity.class.getSimpleName();

//    LinearLayout mVideoContainer;
    LinearLayout mButtons;
    SimpleVideoView mSimpleVideoView;
    RelativeLayout.LayoutParams mVideoParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mVideoContainer = (LinearLayout) findViewById(R.id.ll_video_container);
        mSimpleVideoView = (SimpleVideoView) findViewById(R.id.uvv_vido);
        mButtons = (LinearLayout) findViewById(R.id.lly_button);
        mSimpleVideoView.setBackgroundResource(R.drawable.dog);
        mSimpleVideoView.setVideoPath(path);
        mSimpleVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {
//                Log.e(TAG, mp.getMediaInfo().toString());
            }
        });

        mSimpleVideoView.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer mp, int what, int extra) {
//                Log.e(TAG, mp.toString() + what + extra);
                return false;
            }
        });
    }

    public void change(View view) {
        if (mSimpleVideoView.isPlaying()) {
            mSimpleVideoView.pause();
        } else {
            mSimpleVideoView.start();
        }
    }

    public void refresh(View view) {
        mSimpleVideoView.resume();
    }

    public void fullScreen(View view) {
        fullScreen();
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
        mVideoParams = (RelativeLayout.LayoutParams) mSimpleVideoView.getLayoutParams();
        mSimpleVideoView.setLayoutParams(params);
        mButtons.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            if (mVideoParams != null) {
                mSimpleVideoView.setLayoutParams(mVideoParams);
                mButtons.setVisibility(View.VISIBLE);
            }
            return;
        }
        super.onBackPressed();
    }
}
