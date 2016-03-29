package com.upyun.upplayer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Metrics {
    @SerializedName("non_smooth_count")
    @Expose
    private Integer nonSmoothCount;
    @SerializedName("system_version")
    @Expose
    private String systemVersion;
    @SerializedName("client_network")
    @Expose
    private String clientNetwork;
    @SerializedName("non_smooth_times")
    @Expose
    private List<Long> nonSmoothTimes = new ArrayList<Long>();
    @SerializedName("client_ip")
    @Expose
    private String clientIp;
    @SerializedName("play_url")
    @Expose
    private String playUrl;
    @SerializedName("first_play_duration")
    @Expose
    private String firstPlayDuration;
    @SerializedName("play_version")
    @Expose
    private String playVersion;
    @SerializedName("avg_download_speed")
    @Expose
    private String avgDownloadSpeed;
    @SerializedName("play_bufferTime")
    @Expose
    private String playBufferTime;
    @SerializedName("total_play_duration")
    @Expose
    private String totalPlayDuration;
    @SerializedName("first_packet_duration")
    @Expose
    private String firstPacketDuration;
    @SerializedName("url_bandwidth")
    @Expose
    private String urlBandwidth;

    /**
     * @return The nonSmoothCount
     */
    public Integer getNonSmoothCount() {
        return nonSmoothCount;
    }

    /**
     * @param nonSmoothCount The non_smooth_count
     */
    public void setNonSmoothCount(Integer nonSmoothCount) {
        this.nonSmoothCount = nonSmoothCount;
    }

    /**
     * @return The systemVersion
     */
    public String getSystemVersion() {
        return systemVersion;
    }

    /**
     * @param systemVersion The system_version
     */
    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    /**
     * @return The clientNetwork
     */
    public String getClientNetwork() {
        return clientNetwork;
    }

    /**
     * @param clientNetwork The client_network
     */
    public void setClientNetwork(String clientNetwork) {
        this.clientNetwork = clientNetwork;
    }

    /**
     * @return The nonSmoothTimes
     */
    public List<Long> getNonSmoothTimes() {
        return nonSmoothTimes;
    }

    /**
     * @param nonSmoothTimes The non_smooth_times
     */
    public void setNonSmoothTimes(List<Long> nonSmoothTimes) {
        this.nonSmoothTimes = nonSmoothTimes;
    }

    /**
     * @return The clientIp
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp The client_ip
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @return The playUrl
     */
    public String getPlayUrl() {
        return playUrl;
    }

    /**
     * @param playUrl The play_url
     */
    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    /**
     * @return The firstPlayDuration
     */
    public String getFirstPlayDuration() {
        return firstPlayDuration;
    }

    /**
     * @param firstPlayDuration The first_play_duration
     */
    public void setFirstPlayDuration(String firstPlayDuration) {
        this.firstPlayDuration = firstPlayDuration;
    }

    /**
     * @return The playVersion
     */
    public String getPlayVersion() {
        return playVersion;
    }

    /**
     * @param playVersion The play_version
     */
    public void setPlayVersion(String playVersion) {
        this.playVersion = playVersion;
    }

    /**
     * @return The avgDownloadSpeed
     */
    public String getAvgDownloadSpeed() {
        return avgDownloadSpeed;
    }

    /**
     * @param avgDownloadSpeed The avg_download_speed
     */
    public void setAvgDownloadSpeed(String avgDownloadSpeed) {
        this.avgDownloadSpeed = avgDownloadSpeed;
    }

    /**
     * @return The playBufferTime
     */
    public String getPlayBufferTime() {
        return playBufferTime;
    }

    /**
     * @param playBufferTime The play_bufferTime
     */
    public void setPlayBufferTime(String playBufferTime) {
        this.playBufferTime = playBufferTime;
    }

    /**
     * @return The totalPlayDuration
     */
    public String getTotalPlayDuration() {
        return totalPlayDuration;
    }

    /**
     * @param totalPlayDuration The total_play_duration
     */
    public void setTotalPlayDuration(String totalPlayDuration) {
        this.totalPlayDuration = totalPlayDuration;
    }

    /**
     * @return The firstPacketDuration
     */
    public String getFirstPacketDuration() {
        return firstPacketDuration;
    }

    /**
     * @param firstPacketDuration The first_packet_duration
     */
    public void setFirstPacketDuration(String firstPacketDuration) {
        this.firstPacketDuration = firstPacketDuration;
    }

    /**
     * @return The urlBandwidth
     */
    public String getUrlBandwidth() {
        return urlBandwidth;
    }

    /**
     * @param urlBandwidth The url_bandwidth
     */
    public void setUrlBandwidth(String urlBandwidth) {
        this.urlBandwidth = urlBandwidth;
    }
}
