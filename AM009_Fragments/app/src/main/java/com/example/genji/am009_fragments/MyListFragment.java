package com.example.genji.am009_fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by genji on 1/28/16.
 */

// http://developer.android.com/training/basics/fragments/communicating.html

public class MyListFragment extends Fragment {

    private OnItemSelectedListener listener;

    public interface OnItemSelectedListener {
        void onItemSelected(String link);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list,
                container, false);
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener((v) -> updateDetail("fake"));
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        MainActivity activity = context instanceof MainActivity ? (MainActivity) context : null;

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            listener = activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // For version < Android 6
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // This makes sure that the container activity has implemented
            // the callback interface. If not, it throws an exception.
            try {
                listener = (OnItemSelectedListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnHeadlineSelectedListener");
            }
        }
    }


    // triggers update of the details fragment
    public void updateDetail(String txt) {
        String newTime = String.valueOf(System.currentTimeMillis());
        // send data to activity
        if (listener != null)
            listener.onItemSelected(newTime);
    }
}
