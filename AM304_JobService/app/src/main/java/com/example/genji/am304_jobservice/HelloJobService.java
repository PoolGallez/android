package com.example.genji.am304_jobservice;

import android.content.Context;
import android.content.Intent;

import android.support.v4.app.JobIntentService;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by genji on 2/4/18.
 */

public class HelloJobService extends JobIntentService {

    static final int JOB_ID = 1000;

    public static final String OUTMSG = "omsg";
    public static final String INMSG = "imsg";
    public static final Random r = new Random();

    static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, HelloJobService.class, JOB_ID, work);
    }


    @Override
    public void onHandleWork(Intent intent) {

        Log.i("HelloJobService", "Executing work: " + intent);

        long delay = 2 + r.nextInt(3);

        try {
            String msg = intent.getStringExtra(INMSG);
            Thread.sleep(delay * 1000);
            String resultTxt = msg + " "
                    + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis());

            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(ResponseReceiver.ACTION_RESP);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            broadcastIntent.putExtra(OUTMSG, resultTxt);

            sendBroadcast(broadcastIntent);
        } catch (InterruptedException e) {
            // Restore interrupt status.
            // Thread.currentThread().interrupt();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "All work complete", Toast.LENGTH_LONG).show();
    }

}
