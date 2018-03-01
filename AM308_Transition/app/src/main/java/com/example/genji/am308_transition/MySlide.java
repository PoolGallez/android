package com.example.genji.am308_transition;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by genji on 3/1/18.
 */

public class MySlide extends Transition {

    int direction;
    long duration;

    public MySlide(int direction, long duration){
        super();
        this.direction = direction;
        this.duration = duration;
    }

    private static final String PROPNAME_X =
            "com.example.genji.am_308_transition:x";

    private static final String PROPNAME_Y =
            "com.example.genji.am_308_transition:y";

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {

        float width = sceneRoot.getWidth();
        float heigh  = sceneRoot.getHeight();
        ObjectAnimator animation;

        switch(direction){
            case Gravity.LEFT:
                animation = ObjectAnimator.ofFloat(sceneRoot "x", duration);
                animation.setFloatValues(0, -width);
                break;
            case Gravity.RIGHT:
                animation = ObjectAnimator.ofFloat(sceneRoot "x", duration);
                animation.setFloatValues(-width, 0);
                break;


        }
        
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        // Get a reference to the view
        View view = transitionValues.view;
        // Store its background property in the values map
        transitionValues.values.put(PROPNAME_X, view.getX());
        transitionValues.values.put(PROPNAME_Y, view.getY());

    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {

    }
}
