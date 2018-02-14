package com.example.genji.am027_asynctask;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText time;
    private TextView finalResult;

    private AsyncTaskRunner runner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = findViewById(R.id.time);
        button = findViewById(R.id.button);
        finalResult = findViewById(R.id.finalResult);
    }

    /* AsyncTask generic:
     * <params> String
     * <progress> String
     * <result> String
     */

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        private int time;

        @Override
        protected String doInBackground(String... params) {
            time = Integer.parseInt(params[0]);
            // Divide total time in 10 parts
            int pTime = 10*time;
            for(int i = 1; i < 100; i++){
                SystemClock.sleep(pTime);
                /* The next method can be invoked from doInBackground(Params...)
                 * to publish updates on the UI thread
                 * while the background computation is still running.
                 * Each call to this method will trigger the execution of
                 * onProgressUpdate(Progress...) on the UI thread.
                 */
                publishProgress(String.valueOf(i));
            }
            resp = String.valueOf(time);
            return resp;
        }

        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            finalResult.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.green));
            finalResult.setText("FINISHED after " + result + " seconds");
        }

        @Override
        protected void onPreExecute() {
            // for example: visualize ProgessDialog
        }

        /* AsyncTask generic:
        * ...
        * <progress> String
        * ...
        */
        @Override
        protected void onProgressUpdate(String... text) {
            finalResult.setText(text[0]+"%");
        }
    }

    public void onClick(View v) {
        finalResult.setTextColor(getResources().getColor(R.color.red));
        finalResult.setText("");
        if (runner != null && runner.getStatus() == AsyncTask.Status.RUNNING) {
            runner.cancel(true);
            finalResult.setText("STOPPED");
        }
        runner = new AsyncTaskRunner();
        String sleepTime = time.getText().toString();
        runner.execute(sleepTime);
    }
}
