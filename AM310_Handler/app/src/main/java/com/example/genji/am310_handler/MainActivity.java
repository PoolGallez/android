package com.example.genji.am310_handler;

import android.annotation.SuppressLint;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String MSG_KEY = "key";

    // here below we do not use lambda
    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String string = bundle.getString(MSG_KEY);
            final TextView myTextView = findViewById(R.id.textView);
            myTextView.setText(string);
        }
    };

    private final Runnable messageSender = () -> {
        Message msg = mHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString(MSG_KEY, getCurrentTime());
        msg.setData(bundle);
        mHandler.sendMessage(msg);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        // button.setOnClickListener((View v) -> handleButtonClick(v));
        button.setOnClickListener(this::handleButtonClick);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(messageSender);
    }

    public void handleButtonClick(View view) {
        new Thread(messageSender).start();
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.ITALY);
        return sdf.format(new Date());
    }

}