package com.example.genji.am024_handler;

import android.Manifest;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private ProgressBar progress;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // handler = new Handler(Looper.getMainLooper())
        handler = new Handler();
        progress = findViewById(R.id.progressBar);
        text = findViewById(R.id.textView);
    }

    public void startProgress(View view) {

        // the task that will run in UI thread
        Runnable task = () -> {
            for (int i = 0; i <= 10; i++) {
                final int value = i;
                doSmallWork();
                // post a task to the handler so it communicates with UI thread
                handler.post(() -> {
                    progress.setProgress(value);
                    });
                }
                // the last post
                handler.post(()-> {
                    text.setText("Finish");
                    progress.setProgress(0);
                });
        };
        Thread t = new Thread(task);
        t.start();
    }

    // Simulating something timeconsuming
    private void doSmallWork() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
