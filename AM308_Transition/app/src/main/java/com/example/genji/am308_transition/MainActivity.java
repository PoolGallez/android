package com.example.genji.am308_transition;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private Scene mScene1;
    private Scene mScene2;
    private ViewGroup mSceneRoot;

    Transition back, forward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSceneRoot = findViewById(R.id.scene_root);
        back = new Slide(Gravity.TOP).setDuration(500);
        forward = new Slide(Gravity.BOTTOM).setDuration(500);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_1:
                if (checked) {
                    back = new Slide(Gravity.TOP).setDuration(500);
                    forward = new Slide(Gravity.BOTTOM).setDuration(500);
                }
                break;
            case R.id.radio_2:
                if (checked) {
                    back = new Slide(Gravity.LEFT).setDuration(500);
                    forward = new Slide(Gravity.RIGHT).setDuration(500);
                }
                break;
            case R.id.radio_3:
                if (checked) {
                    back = new Fade(Fade.IN).setDuration(700);
                    forward = new Fade(Fade.IN | Fade.OUT).setDuration(700);
                }
                break;

        }
    }

    public void next (View view){
        mScene2 = Scene.getSceneForLayout(mSceneRoot, R.layout.scene_two, this);
        TransitionManager.go(mScene2, forward);

    }

    public void prev (View view){
        mScene1 = Scene.getSceneForLayout(mSceneRoot, R.layout.scene_one, this);
        TransitionManager.go(mScene1, back);

    }
}
