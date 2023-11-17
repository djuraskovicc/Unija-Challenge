package com.example.challange;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.http.HttpCommunication;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.OkHttpClient;

public class Documents extends AppCompatActivity {
    ArrayList<DocumentItem> documents;
    OkHttpClient httpClient;
    RecyclerView recyclerView;
    String fileContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);

        httpClient = new OkHttpClient();
        documents = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);

        new NetworkTask().execute("http://165.227.135.100:8080/api/files");
    }

    private void loadItems(String fileContents) {
        try {
            JSONArray jsonArray = new JSONArray(fileContents);

            if(jsonArray.length() == 0){
                Utils.showToast(Documents.this, "No documents available");
            }else{
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String documentName = jsonObject.getString("fileName");
                    String documentDate = jsonObject.getString("uploadDate");
                    String documentContent = jsonObject.getString("fileContents");

                    documents.add(new DocumentItem(documentName, documentDate, documentContent));
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
                recyclerView.setLayoutManager(new LinearLayoutManager(Documents.this));
                recyclerView.setAdapter(new DocumentsAdapter(Documents.this, documents));
            }else{
                Utils.showToast(Documents.this, "Failed to fetch data");
            }
        }
    }
}