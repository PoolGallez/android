package com.example.genji.pp001_flatdice;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private boolean started = false;
    private DiceManager diceManager;
    private GestureDetector gestureDetector;
    // or private GestureDetectorCompat gestureDetector;
    private SensorManager sensorManager;
    private ShakeListener shakeListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceManager = new DiceManager();

        gestureDetector = new GestureDetector(this, new GestureListener());

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        shakeListener = new ShakeListener();
        sensorManager.registerListener(shakeListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        // start app with an intro screen
        setIntroFragment();

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(shakeListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(shakeListener);
        super.onStop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    public void setIntroFragment(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, new IntroFragment());
        ft.commit();
    }

    class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private long lastSwipe;

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            diceManager.start();
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // exit if swiped less than 500 millisecons ago
            if (System.currentTimeMillis() - lastSwipe < 500) {
                return true;
            }
            lastSwipe = System.currentTimeMillis();
            // left or Right
            if (Math.abs(velocityX) > Math.abs(velocityY)) {
                if (velocityX < 0) {
                    diceManager.changeFace(Direction.Left);
                }
                if (velocityX > 0) {
                    diceManager.changeFace(Direction.Right);
                }
            }
            //Up or Down
            if (Math.abs(velocityY) > Math.abs(velocityX)) {
                if (velocityY < 0) {
                    diceManager.changeFace(Direction.Up);
                }
                if (velocityY > 0) {
                    diceManager.changeFace(Direction.Down);
                }
            }
            return true;
        }
    }



    // manage shakes (using sensor)
    class ShakeListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            float acceleration = (float) Math.sqrt((double) (x * x + y * y + z * z));
            if (acceleration > 30) {
                diceManager.roll();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    }

    // directions
    public enum Direction {
        Left,
        Right,
        Up,
        Down
    }

    // an inner service class
    public class DiceManager {

        private final DiceFragment[] faces;
        private final Random random;

        public DiceManager() {
            Activity main = MainActivity.this;
            faces = new DiceFragment[]{
                    DiceFragment.newIstance(main.getString(R.string.one), main.getResources().getColor(R.color.green)),
                    DiceFragment.newIstance(main.getString(R.string.two), main.getResources().getColor(R.color.blue)),
                    DiceFragment.newIstance(main.getString(R.string.six), main.getResources().getColor(R.color.red)),
                    DiceFragment.newIstance(main.getString(R.string.five), main.getResources().getColor(R.color.orange)),
                    DiceFragment.newIstance(main.getString(R.string.three), main.getResources().getColor(R.color.purple)),
                    DiceFragment.newIstance(main.getString(R.string.four), main.getResources().getColor(R.color.yellow))
            };
            random = new Random();
        }

        public void start() {
            FragmentManager fm = MainActivity.this.getFragmentManager();
            Fragment nextFragment = faces[0];
            if (nextFragment != null) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment, nextFragment);
                ft.commit();
            }
        }

        public void roll() {
            FragmentManager fm = MainActivity.this.getFragmentManager();
            DiceFragment nextFragment = faces[random.nextInt(6)];
            nextFragment.setDirection(null, 0, 0);
            if (nextFragment != null) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment, nextFragment);
                ft.commit();
            }
        }

        public void changeFace(Direction direction) {
            DiceFragment f0 = faces[0]; DiceFragment f1 = faces[1];
            DiceFragment f2 = faces[2]; DiceFragment f3 = faces[3];
            DiceFragment f4 = faces[4]; DiceFragment f5 = faces[5];
            switch (direction) {
                case Up:
                    faces[0] = f3; faces[1] = f0; faces[2] = f1; faces[3] = f2;
                    break;
                case Down:
                    faces[0] = f1; faces[1] = f2; faces[2] = f3; faces[3] = f0;
                    break;
                case Left:
                    faces[0] = f5; faces[2] = f4; faces[4] = f0; faces[5] = f2;
                    break;
                case Right:
                    faces[0] = f4; faces[2] = f5; faces[4] = f2; faces[5] = f0;
                    break;
            }

            FragmentManager fm = MainActivity.this.getFragmentManager();
            DiceFragment nextFragment = faces[0];
            DiceFragment currentFragment = (DiceFragment) fm.findFragmentById(R.id.fragment);

            if (nextFragment != null) {
                int width = currentFragment.getView().getWidth();
                int height = currentFragment.getView().getHeight();
                currentFragment.setDirection(direction, width, height);
                nextFragment.setDirection(direction, width, height);
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment, nextFragment);
                ft.commit();
            }
        }
    }

}
