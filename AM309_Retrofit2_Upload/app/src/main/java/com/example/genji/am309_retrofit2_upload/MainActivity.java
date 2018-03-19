package com.example.genji.am309_retrofit2_upload;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    public static final String BASE_URL = "http://192.168.1.2:8080";
    private FileUploadService mService;
    private static final int SELECT_IMAGE = 666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // define Retrofit 2 service
        mService = RetrofitClient.getClient(BASE_URL).create(FileUploadService.class);

    }

    public void pickImage(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_IMAGE);
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
                    String name = path.substring(path.lastIndexOf("/")+1);
                    // set textView
                    TextView tvName =  MainActivity.this.findViewById(R.id.name);
                    tvName.setText(name);
                    File file = new File(path);
                    uploadFile(file);
                }
        }
    }

    private void uploadFile(File file) {

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/*"),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

        // add another part within the multipart request
        String descriptionString = "hello, this is description speaking";
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

}
