package com.example.genji.am304_jobservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by genji on 2/4/18.
 */

public class ResponseReceiver extends BroadcastReceiver {
    public static final String ACTION_RESP =
            "com.example.genji.am040_service.PROCESS_MESSAGE";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "HelloIntentService: " + intent.getStringExtra(HelloJobService.OUTMSG), Toast.LENGTH_SHORT).show();
    }
}
