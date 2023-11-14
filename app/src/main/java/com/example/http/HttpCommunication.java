package com.example.http;

import android.app.Activity;
import androidx.annotation.NonNull;
import com.example.challange.Utils;
import java.io.File;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpCommunication {
    public static void postRequest(Activity activity, OkHttpClient httpClient, File file, String postUrl) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("fileUpload", file.getName(),
                        RequestBody.create(MediaType.parse("image/jpg"), file))
                .build();

        Request request = new Request.Builder()
                .url(postUrl)
                .post(requestBody)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                activity.runOnUiThread(() -> Utils.showToast(activity, "Something went wrong :("));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                activity.runOnUiThread(() -> {
                    try {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            String responseBody = response.body().string();
                            response.close();
                            Utils.showToast(activity, "Success");
                        } else {
                            Utils.showToast(activity, "Fail, Code: " + response.code());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    public static String getRequest(OkHttpClient httpClient, String getUrl) throws IOException {
        Request request = new Request.Builder()
                .url(getUrl)
                .get()
                .build();

        Response response = httpClient.newCall(request).execute();
        if (response.isSuccessful() && response.body() != null) {
            return response.body().string();
        } else {
            throw new IOException("Failed to fetch data, Code: " + response.code());
        }
    }
}