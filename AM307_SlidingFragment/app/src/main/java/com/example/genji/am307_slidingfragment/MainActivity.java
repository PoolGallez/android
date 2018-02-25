package com.example.genji.am307_slidingfragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final float MIN_DISTANCE = 150.0f;
    private float mPreviousX;
    private float mPreviousY;

    View enter, exit;
    Slide previous, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enter = findViewById(R.id.enter);
        exit = findViewById(R.id.exit);

        previous = Slide.getInstance(0, 0);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.enter, previous);
        ft.commit();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPreviousX = e.getX();
                mPreviousY = e.getY();
                break;

            case MotionEvent.ACTION_UP:
                float x = e.getX();
                float y = e.getY();
                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                if (Math.abs(dx) <= MIN_DISTANCE && Math.abs(dy) <= MIN_DISTANCE) {
                    Toast.makeText(this, "TAP", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (Math.abs(dx) <= MIN_DISTANCE && Math.abs(dy) > MIN_DISTANCE) {
                    if(dy > 0){
                        Toast.makeText(this, "DOWN", Toast.LENGTH_SHORT).show();
                        slide(SlideAnimator.DIRECTION_DOWN);
                        return true;
                    } else {
                        Toast.makeText(this, "UP", Toast.LENGTH_SHORT).show();
                        slide(SlideAnimator.DIRECTION_UP);
                        return true;
                    }
                } else if (Math.abs(dx) > MIN_DISTANCE && Math.abs(dy) <= MIN_DISTANCE) {
                    if(dx > 0){
                        Toast.makeText(this, "RIGHT", Toast.LENGTH_SHORT).show();
                        slide(SlideAnimator.DIRECTION_RIGHT);
                        return true;
                    } else {
                        Toast.makeText(this, "LEFT", Toast.LENGTH_SHORT).show();
                        slide(SlideAnimator.DIRECTION_LEFT);
                        return true;
                    }
                } else {
                    Toast.makeText(this, "DIAGONAL", Toast.LENGTH_SHORT).show();
                    return true;
                }
        }
        return false;
    }

    public void slide(int move){

        int i = previous.getI();
        int j = previous.getJ();

        FragmentManager fm = getFragmentManager();

        switch(move){
            case SlideAnimator.DIRECTION_LEFT:
                next = Slide.getInstance(i, Math.abs((j-1) % 5));
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.enter, next);
                ft.commit();
                SlideAnimator.slide(enter, exit, SlideAnimator.DIRECTION_LEFT, 400);
                previous = next;
                break;
            case SlideAnimator.DIRECTION_RIGHT:
                next = Slide.getInstance(i, (j+1) % 5);
                ft = fm.beginTransaction();
                ft.replace(R.id.enter, next);
                ft.commit();
                SlideAnimator.slide(enter, exit, SlideAnimator.DIRECTION_RIGHT, 400);
                previous = next;
                break;
            case SlideAnimator.DIRECTION_UP:
                next = Slide.getInstance((i+1) % 5, j);
                ft = fm.beginTransaction();
                ft.replace(R.id.enter, next);
                ft.commit();
                SlideAnimator.slide(enter, exit, SlideAnimator.DIRECTION_UP, 400);
                previous = next;
                break;
            case SlideAnimator.DIRECTION_DOWN:
                next = Slide.getInstance(Math.abs((i-1) % 5), j);
                ft = fm.beginTransaction();
                ft.replace(R.id.enter, next);
                ft.commit();
                SlideAnimator.slide(enter, exit, SlideAnimator.DIRECTION_DOWN, 400);
                previous = next;
                break;
        }
    }
}
