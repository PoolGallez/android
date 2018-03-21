package com.example.genji.am309_retrofit2_upload;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://192.168.1.2:3000";
    private FileUploadService mService;
    private static final int SELECT_IMAGE = 666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // define Retrofit2 service POST multipart http://192.168.1.2:3000/upload
        mService = RetrofitClient.getClient(BASE_URL).create(FileUploadService.class);
        // check permissions
        if(!enabledPermission())
            Toast.makeText(this, getString(R.string.permissions), Toast.LENGTH_LONG).show();
    }

    public void pickImage(View view){
        Intent imagePickerIntent = new Intent(Intent.ACTION_PICK);
        imagePickerIntent.setType("image/*");
        startActivityForResult(imagePickerIntent, SELECT_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case SELECT_IMAGE:
                if(resultCode == RESULT_OK){
                    // get Uri from intent data
                    Uri uri = imageReturnedIntent.getData();
                    // get file path and name
                    String path = getRealPathFromURI(uri);
                    // String name = path.substring(path.lastIndexOf("/")+1);
                    String name = getFileNameFromURI(uri);
                    // set textView
                    TextView tvName =  MainActivity.this.findViewById(R.id.name);
                    tvName.setText(name);
                    EditText etd = findViewById(R.id.description);
                    String descriptionString = etd.getText().toString();
                    File file = new File(path);
                    // upload file
                    getParts(file, descriptionString);
                }
        }
    }

    private void getParts(File file, String descriptionString) {

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/*"),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        RequestBody description = RequestBody.create(
                // multipart/form-data
                MultipartBody.FORM, descriptionString);


        // finally, execute the request
        Call<ResponseBody> call = mService.upload(description, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Log.v("Upload", "success");
                Toast.makeText(MainActivity.this, getString(R.string.success), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
                Toast.makeText(MainActivity.this, getString(R.string.failure), Toast.LENGTH_LONG).show();
            }
        });
    }

    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.MediaColumns.DATA }; // Path to the file on disk
            cursor = getContentResolver().query(contentUri,  proj, null, null, null);
            // Returns the zero-based index for the given column name, or throws IllegalArgumentException if the column doesn't exist.
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public String getFileNameFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.MediaColumns.DISPLAY_NAME }; // name of file
            cursor = getContentResolver().query(contentUri,  proj, null, null, null);
            // Returns the zero-based index for the given column name, or throws IllegalArgumentException if the column doesn't exist.
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }




    // from API 23 ....
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
