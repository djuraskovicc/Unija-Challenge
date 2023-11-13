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

    public static void getRequest(Activity activity, OkHttpClient httpClient, File file, String getUrl){
        Request request = new Request.Builder().url(getUrl).build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                activity.runOnUiThread(() -> {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                            .addFormDataPart("fileUpload", file.getName(),
                                    RequestBody.create(MediaType.parse("text/json"), file))
                            .build();

                    Request request1 = new Request.Builder()
                            .url(getUrl)
                            .post(requestBody)
                            .build();
                    Response response1;

                    try {
                        response1 = client.newCall(request1).execute();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    if (response1.code() != 200) {
                        System.out.println("Something went wrong");
                    }
                });
            }
        });
    }
}