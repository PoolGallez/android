package com.example.genji.am101_mongo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by genji on 3/7/18.
 */

public class Size {
    @SerializedName("h")
    @Expose
    private Double h;
    @SerializedName("w")
    @Expose
    private Double w;
    @SerializedName("uom")
    @Expose
    private String uom;

    public Double getH() {
        return h;
    }

    public void setH(Double h) {
        this.h = h;
    }

    public Double getW() {
        return w;
    }

    public void setW(Double w) {
        this.w = w;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }
}
