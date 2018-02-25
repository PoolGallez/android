package com.example.genji.am307_slidingfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by genji on 2/25/18.
 */

public class Slide extends Fragment {

    public static String[][] sator = {
            {"S", "A", "T", "O", "R"},
            {"A", "R", "E", "P", "O"},
            {"T", "E", "N", "E", "T"},
            {"O", "P", "E", "R", "A"},
            {"R", "O", "T", "A", "S"}
    };

    private int i, j;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slide, container, false);
        TextView textView = view.findViewById(R.id.text);
        textView.setText(sator[i][j]);
        return view;
    }

    // good practice: use a bundle as argument
    public static Slide getInstance(int i, int j){
        Slide slide = new Slide();
        slide.i = i;
        slide.j = j;
        return slide;
    }

    public int getI(){return i;}
    public int getJ(){return j;}

}
