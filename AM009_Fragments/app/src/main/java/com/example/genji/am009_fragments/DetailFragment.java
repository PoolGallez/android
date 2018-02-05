package com.example.genji.am009_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by genji on 1/28/16.
 */
public class DetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail,
                container, false);
        return view;
    }

    public void setText(String txt) {
        // getView() : Get the root view for the fragment's layout (fragment must have a layout)
        TextView view = (TextView) getView().findViewById(R.id.detailsText);
        view.setText(txt);
    }
}
