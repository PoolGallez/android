package com.example.genji.am004_receiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MyBroadcastReceiver myReceiver;
    LocalBroadCast localBroadcast;
    LocalBroadcastManager localBroadcastManager;
    IntentFilter intentFilter;
    IntentFilter localIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myReceiver = new MyBroadcastReceiver();
        intentFilter = new IntentFilter("com.example.genji.am004_receiver.ONE");

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcast = new LocalBroadCast();
        localIntentFilter = new IntentFilter("com.example.genji.am004_receiver.THREE");
    }

    public void onClick(View view) {
        int id = view.getId();
        Intent i = new Intent();
        switch (id){
            case R.id.btn1:
                i.setAction("com.example.genji.am004_receiver.ONE");
                i.putExtra("key", "*** ONE ***");
                sendBroadcast(i);
                break;
            case R.id.btn2:
                i.setAction("com.example.genji.am004_receiver.TWO");
                i.putExtra("key", "*** TWO ***");
                sendBroadcast(i);
                break;
            case R.id.btn3:
                i.setAction("com.example.genji.am004_receiver.THREE");
                localBroadcastManager.sendBroadcast(i);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(myReceiver, intentFilter);
        localBroadcastManager.registerReceiver(localBroadcast, localIntentFilter);
    }
    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
        localBroadcastManager.unregisterReceiver(localBroadcast);
    }
}
