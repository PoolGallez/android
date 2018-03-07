package com.example.genji.am101_mongo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by genji on 3/7/18.
 */

public class Embedded {
    @SerializedName("_embedded")
    @Expose
    private ArrayList<Product> embedded = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("_returned")
    @Expose
    private Integer returned;

    public ArrayList<Product> getProducts() {
        return embedded;
    }

    public void setProducts(ArrayList<Product> products) {
        this.embedded = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getReturned() {
        return returned;
    }

    public void setReturned(Integer returned) {
        this.returned = returned;
    }
}
