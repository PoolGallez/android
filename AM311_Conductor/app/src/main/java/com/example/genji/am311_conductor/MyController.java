package com.example.genji.am311_conductor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluelinelabs.conductor.Controller;

public class MyController extends Controller {

    private String header;
    private String text;
    private int color;

    public static MyController get(Bundle b){
        MyController my = new MyController();
        my.header = b.getString("header");
        my.text = b.getString("text");
        my.color = b.getInt("color");
        return my;
    }


    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_my, container, false);
        LinearLayout layout = view.findViewById(R.id.controller_layout);
        layout.setBackgroundColor(color);
        TextView tvheader = view.findViewById(R.id.controller_header);
        TextView tvtext = view.findViewById(R.id.controller_text);
        tvheader.setText(header);
        tvtext.setText(text);
        return view;
    }
}
