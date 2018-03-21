package com.example.genji.am309_retrofit2_upload;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by genji on 3/17/18.
 */

public interface FileUploadService {

    @Multipart
    @POST("upload")
    Call<ResponseBody> upload(
            // two parts
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file
    );
}
