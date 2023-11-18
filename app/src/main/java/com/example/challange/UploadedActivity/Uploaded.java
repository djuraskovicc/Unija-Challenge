package com.example.challange.UploadedActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.challange.R;
import com.example.challange.MainActivity.Utils;
import com.example.http.HttpCommunication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import okhttp3.OkHttpClient;

public class Uploaded extends AppCompatActivity {
    OkHttpClient httpClient;
    RecyclerView recyclerView;
    ArrayList<ImageItem> items;
    String fileContents;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploaded);

        httpClient = new OkHttpClient();
        items = new ArrayList<>();
        httpClient = new OkHttpClient();
        recyclerView = findViewById(R.id.recycler_view);

        new NetworkTask().execute("http://165.227.135.100:8080/api/images/all");
    }

    private void loadItems(String fileContents){
        try {
            JSONArray jsonArray = new JSONArray(fileContents);

            if(jsonArray.length() == 0){
                Utils.showToast(Uploaded.this, "No images available");
            }else{
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String imageUrl = jsonObject.getString("imageUrl");
                    String imageName = jsonObject.getString("imageName");

                    items.add(new ImageItem(imageUrl, imageName));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }

    private class NetworkTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                fileContents = HttpCommunication.getRequest(httpClient, strings[0]);
                return fileContents;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String fileContents) {
            if(fileContents != null){
                loadItems(fileContents);
                recyclerView.setLayoutManager(new LinearLayoutManager(Uploaded.this));
                recyclerView.setAdapter(new UploadedAdapter(Uploaded.this, items));
            }else{
                Utils.showToast(Uploaded.this, "Failed to fetch data");
            }
        }
    }
}