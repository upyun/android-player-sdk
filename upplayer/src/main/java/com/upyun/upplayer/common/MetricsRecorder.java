package com.upyun.upplayer.common;

import android.content.Context;
import android.os.Build;

import com.upyun.upplayer.model.Metrics;
import com.upyun.upplayer.utils.NetSpeed;
import com.upyun.upplayer.utils.NetUtil;

import java.util.ArrayList;
import java.util.List;

public class MetricsRecorder {

    public List<Metrics> metricses;
    public NetSpeed netSpeed;
    public long startTime;
    public long firstPacketTime;
    public long firstPlayTime;
    private Metrics metrics;
    private Context mCotext;
    private List<Long> bufferingTimes;

    private long bufferStartTime;
    private long bufferEndTime;

    public MetricsRecorder(Context context) {
        this.mCotext = context;
        //TODO
        metricses = new ArrayList<>();
        metrics = new Metrics();
        bufferingTimes = new ArrayList<>();
        metrics.setNonSmoothTimes(bufferingTimes);
        metricses.add(metrics);
        metrics.setClientNetwork(NetUtil.isConnected(mCotext).toString());
        metrics.setPlayVersion("1.0.0");
        metrics.setSystemVersion(Build.VERSION.RELEASE);
        netSpeed = new NetSpeed(mCotext);
    }

    public void Start() {
        this.startTime = System.currentTimeMillis();
    }

    public void FirstPacket() {
        this.firstPacketTime = System.currentTimeMillis();
        metrics.setFirstPacketDuration(firstPacketTime - startTime + "");
    }

    public void startPlay() {
        if (firstPlayTime == 0) {
            this.firstPlayTime = System.currentTimeMillis();
            metrics.setFirstPlayDuration(firstPlayTime - startTime + "");
        }
    }

    public void setPlayUrl(String url) {

        metrics.setPlayUrl(url);
    }

    public void setCacheDuration(long mesc) {

        metrics.setPlayBufferTime(mesc + "");
    }

    public void endRecode() {
        metrics.setTotalPlayDuration(System.currentTimeMillis() - startTime + "");
        metrics.setAvgDownloadSpeed(netSpeed.getAvgSpeed() + "");
        metrics.setNonSmoothCount(bufferingTimes.size());
    }

    public void BufferStart() {
        this.bufferStartTime = System.currentTimeMillis();
    }

    public void BufferEnd() {
        if (bufferStartTime != 0) {
            this.bufferEndTime = System.currentTimeMillis();
            bufferingTimes.add(bufferEndTime - bufferStartTime);
        }
    }
}
