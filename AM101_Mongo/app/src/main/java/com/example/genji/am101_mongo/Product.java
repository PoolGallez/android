package com.example.genji.am101_mongo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by genji on 2/9/17.
 */

public class Product {
    @SerializedName("_id")
    @Expose
    private Id id;
    @SerializedName("item")
    @Expose
    private String item;
    @SerializedName("qty")
    @Expose
    private Double qty;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("size")
    @Expose
    private Size size;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
