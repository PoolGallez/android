package com.example.genji.am023_threading;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

// READ: http://developer.android.com/guide/components/processes-and-threads.html

public class MainActivity extends AppCompatActivity {

    private ProgressBar progress;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = findViewById(R.id.progressBar);
        text = findViewById(R.id.textView);
    }

    public void startProgress(View view) {

        // this thread communicates with UI thread
        new Thread(()->{
            // reset (UI communication)
            progress.post(() -> progress.setProgress(0));
            // work
            for (int i = 0; i <= 10; i++) {
                final int value = i;
                doSomething();
                // (UI communication)
                progress.post(() -> {
                    text.setText("Updating");
                    progress.setProgress(value);
                });
            }
            // end work (UI communication)
            progress.post(()->{
                text.setText("Finish");
            });
        }).start();
    }

    private void doSomething() {
        SystemClock.sleep(2000);
    }
}
