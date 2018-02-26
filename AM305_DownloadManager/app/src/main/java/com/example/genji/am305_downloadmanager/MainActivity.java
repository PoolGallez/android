package com.example.genji.am305_downloadmanager;

import android.Manifest;
import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DownloadManager downloadManager;
    private Uri downloadUri;
    private long reference;
    BroadcastReceiver notificationReceiver, downloadReceiver;
    IntentFilter notificationFilter, downloadFilter;

    private static int mNotificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        if(!enabledPermission())
            Toast.makeText(this, "You have not permission", Toast.LENGTH_LONG).show();
    }

    // onClick method
    public void checkNetwork(View v){
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
            // notify("Network test", "ok");
            Toast.makeText(this, "Network ok", Toast.LENGTH_SHORT).show();
    }

    // onClick method
    public void startDownload(View v){
        EditText editUri = findViewById(R.id.url_file);
        String fileUri = editUri.getText().toString();
        downloadUri = Uri.parse(fileUri);
        // create and enqueue a request
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);
        enqueueRequest(request);
    }

    @Override
    protected void onResume() {
        super.onResume();
        notificationFilter = new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        downloadFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        notificationReceiver = getNotificationReceiver();
        downloadReceiver = getDownloadReceiver();
        registerReceiver(notificationReceiver, notificationFilter);
        registerReceiver(downloadReceiver, downloadFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(notificationReceiver);
        unregisterReceiver(downloadReceiver);
    }

    private void enqueueRequest(DownloadManager.Request request){
        // obtain the name of file
        String fileName = downloadUri.getLastPathSegment();
        // set notifications
        // request.setVisibleInDownloadsUi(true);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setTitle("Downloading data");
        request.setDescription(fileName);
        // set file destination
        // request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, fileName );
        // obsoleto
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName );
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        // put in queue the (prepared) request and return the ID of the download
        reference = downloadManager.enqueue(request);
    }

    // a factory methods
    private BroadcastReceiver getNotificationReceiver(){
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // When clicks on multiple notifications are received, the following provides an array of download ids corresponding to the download notification that was clicked.
                long[] references = intent.getLongArrayExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS);
                for (long r : references){
                    if (r == reference){
                        Toast.makeText(context, "Do something while downloading", Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
    }

    private BroadcastReceiver getDownloadReceiver(){
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // the id of the download just completed
                long complete = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (reference == complete){
                    DownloadManager.Query query = new DownloadManager.Query();
                    // include only the complete (id) download
                    query.setFilterById(complete);
                    Cursor cursor = downloadManager.query(query);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                    int status = cursor.getInt(columnIndex);
                    /* deprecated
                    columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
                    */
                    columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);
                    String filePath = cursor.getString(columnIndex);
                    // my notification at the end of download
                    MainActivity.this.notify("Downloaded", filePath);
                    columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_REASON);
                    int reason = cursor.getInt(columnIndex);
                    switch(status){
                        case DownloadManager.STATUS_SUCCESSFUL:
                            // open in a file manager
                            Intent i = new Intent();
                            /* i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
                            startActivity(i)
                             */
                            i.setAction(Intent.ACTION_GET_CONTENT);
                            i.setType("file/*");
                            startActivity(i);
                            break;
                        case DownloadManager.STATUS_PAUSED:
                            Toast.makeText(context, "PAUSED", Toast.LENGTH_LONG).show();
                            break;
                        case DownloadManager.STATUS_PENDING:
                            Toast.makeText(context, "PENDING", Toast.LENGTH_LONG).show();
                            break;
                        case DownloadManager.STATUS_RUNNING:
                            Toast.makeText(context, "PENDING", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }
        };
    }

    // for simple notifying in old style
    public void notify(String title, String subject){
        Notification notification = new Notification.Builder(this)
                .setContentTitle(title)
                .setContentText(subject)
                .setSmallIcon(R.drawable.ic_downloaded)
                .build();

        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, notification);

    }


    public boolean enabledPermission() {
        if (Build.VERSION.SDK_INT >= 23) { // from Android 6.0
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else {
            return true;
        }
    }
}
