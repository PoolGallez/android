package com.example.genji.am008_alertdialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by genji on 2/21/17.
 */

public class MyDialogFragment extends DialogFragment {

    int style;
    int theme;

    // factory method
    static MyDialogFragment newInstance(int style, int theme) {
        MyDialogFragment f = new MyDialogFragment();
        Bundle args = new Bundle();
        args.putInt("style", style);
        args.putInt("theme", theme);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        style = getArguments().getInt("style");
        theme = getArguments().getInt("theme");

        int fragmentStyle = DialogFragment.STYLE_NORMAL;
        int fragmentTheme = android.R.style.Theme_Holo;

        switch (style%4) {
            case 0: fragmentStyle = DialogFragment.STYLE_NORMAL; break;
            case 1: fragmentStyle = DialogFragment.STYLE_NO_TITLE; break;
            case 2: fragmentStyle = DialogFragment.STYLE_NO_FRAME; break;
            case 3: fragmentStyle = DialogFragment.STYLE_NO_INPUT; break;
        }
        switch (theme%4) {
            case 0: fragmentTheme = android.R.style.Theme_Holo; break;
            case 1: fragmentTheme = android.R.style.Theme_Holo_Light_Dialog; break;
            case 2: fragmentTheme = android.R.style.Theme_Holo_Light_Panel; break;
            case 3: fragmentTheme = android.R.style.Theme_Holo_Light; break;
        }
        setStyle(fragmentStyle, fragmentTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_dfragment, container, false);
        Button button = view.findViewById(R.id.mcdf01b);
        Button close = view.findViewById(R.id.mcdf02b);
        final TextView tvd = view.findViewById(R.id.mcdf01t);
        tvd.setText("");
        // fragment interacts with activity
        final TextView tv = getActivity().findViewById(R.id.textView);

        button.setOnClickListener(
            (View v) -> {
                tvd.setText("style #" + style + " theme #" + theme);
                tv.setText("clicked");
            }
        );
        close.setOnClickListener(
                (View v) -> dismiss()
        );
        return view;
    }
}
