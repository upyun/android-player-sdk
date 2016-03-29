package com.upyun.upplayer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IP {
    @SerializedName("origin")
    @Expose
    private String origin;

    /**
     * @return The origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin The origin
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
