package com.example.genji.am309_upload_image;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.POST;

/**
 * Created by genji on 3/12/18.
 */

public interface UploadService {
    @Multipart
    @POST("/images/upload")
    Call<Response> uploadImage(@Part MultipartBody.Part image);
}
