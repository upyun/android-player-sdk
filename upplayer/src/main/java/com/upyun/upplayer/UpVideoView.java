package com.upyun.upplayer;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class UpVideoView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = UpVideoView.class.getSimpleName();

    private IjkMediaPlayer mMediaPlayer;

    public void setDataSource(String dataSource) {
        this.mDataSource = dataSource;
    }

    private String mDataSource;

    public UpVideoView(Context context) {
        super(context);
        initView();
    }

    public UpVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public UpVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public UpVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        getHolder().setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
        getHolder().setKeepScreenOn(true);
        getHolder().addCallback(this);
        mMediaPlayer = new IjkMediaPlayer();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        openVide(holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseVide();
    }

    private void openVide(SurfaceHolder holder) {
        try {
            mMediaPlayer.setDataSource(mDataSource);
            mMediaPlayer.setDisplay(holder);
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        }
        Log.e(TAG, mMediaPlayer.isPlayable() + "::" + mMediaPlayer.isPlaying());
    }

    private void releaseVide() {
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void start() {
        if (mMediaPlayer.isPlayable()) {
            mMediaPlayer.start();
        } else {
            openVide(getHolder());
        }
    }

    public void pause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }

    public void stop() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
    }

    public void refresh() {
        releaseVide();
        openVide(getHolder());
    }

    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }
}
