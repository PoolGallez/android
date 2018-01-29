package com.example.genji.am004_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by genji on 1/29/18.
 */

public class LocalBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,
                "Received broadcast in LocalBroadcastReceiver",
                Toast.LENGTH_LONG).show();

    }
}
