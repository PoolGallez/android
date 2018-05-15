package com.example.genji.pp001_flatdice;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DiceFragment extends Fragment {

    private MainActivity.Direction direction;
    private int width;
    private int height;

    public static DiceFragment newIstance(String face, int color) {
        DiceFragment diceFragment = new DiceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("face", face);
        bundle.putInt("color", color);
        diceFragment.setArguments(bundle);
        return diceFragment;
    }

    public void setDirection(MainActivity.Direction direction, int width, int height) {
        this.direction = direction;
        this.width = width;
        this.height = height;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.face, container, false);
        linearLayout.setBackgroundColor(getArguments().getInt("color"));
        TextView textView = linearLayout.findViewById(R.id.number);
        textView.setText(getArguments().getString("face"));
        return linearLayout;
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        if (direction != null) {
            switch (direction) {
                case Up:
                    if (enter) {
                        return ObjectAnimator.ofFloat(null, "y", height, 0).setDuration(500);
                    } else {
                        return ObjectAnimator.ofFloat(null, "y", 0, -height).setDuration(500);
                    }
                case Down:
                    if (enter) {
                        return ObjectAnimator.ofFloat(null, "y", -height, 0).setDuration(500);
                    } else {
                        return ObjectAnimator.ofFloat(null, "y", 0, height).setDuration(500);
                    }
                case Left:
                    if (enter) {
                        return ObjectAnimator.ofFloat(null, "x", width, 0).setDuration(500);
                    } else {
                        return ObjectAnimator.ofFloat(null, "x", 0, -width).setDuration(500);
                    }
                case Right:
                    if (enter) {
                        return ObjectAnimator.ofFloat(null, "x", -width, 0).setDuration(500);
                    } else {
                        return ObjectAnimator.ofFloat(null, "x", 0, width).setDuration(500);
                    }
            }
        }
        return null;
    }
}
