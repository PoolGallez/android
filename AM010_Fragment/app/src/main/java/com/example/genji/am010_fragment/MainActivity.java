package com.example.genji.am010_fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a new Fragment to be placed in the activity layout
        FragmentOne fr = new FragmentOne();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fr);
        fragmentTransaction.commit();
    }

    public void selectFrag(View view) {
        FragmentManager fm = getFragmentManager();
        Fragment fr;
        if(view == findViewById(R.id.btn2) && findViewById(R.id.f1) != null){
            fr = new FragmentTwo();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fr);
            fragmentTransaction.commit();
        } else if (view == findViewById(R.id.btn1) && findViewById(R.id.f2) != null){
            fr = new FragmentOne();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fr);
            fragmentTransaction.commit();
        }
    }
}
