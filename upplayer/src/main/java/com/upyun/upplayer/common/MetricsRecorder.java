package com.upyun.upplayer.common;

import android.content.Context;
import android.os.Build;

import com.upyun.upplayer.model.Metrics;
import com.upyun.upplayer.utils.NetSpeed;
import com.upyun.upplayer.utils.NetUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    File tempFile;

    public MetricsRecorder(Context context) {
        this.mCotext = context;
        tempFile = new File(context.getCacheDir(),"metrics");
        try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(tempFile));
            metricses= (List<Metrics>) os.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(metrics==null){
            metricses = new ArrayList<>();
        }
        metrics = new Metrics();
        bufferingTimes = new ArrayList<>();
        metrics.setNonSmoothTimes(bufferingTimes);
        metricses.add(metrics);
        metrics.setClientNetwork(NetUtil.isConnected(mCotext).toString());
        metrics.setPlayVersion(Config.VERSION);
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
        try {
            ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(tempFile));
            objectOutput.writeObject(metricses);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void setBandwidth(int arg2) {
        metrics.setUrlBandwidth(arg2 + "");
    }

    public void postRecord() {
        if(metricses!=null&&metricses.size()>0){
            NetUtil.postMetric(metricses);
            metricses.clear();
        }
    }

    public int getNonSmoothCount(){
        return metrics.getNonSmoothCount();
    }
}
