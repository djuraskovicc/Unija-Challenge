package com.example.challange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.http.HttpCommunication;
import com.google.gson.Gson;
import java.util.ArrayList;
import okhttp3.OkHttpClient;

public class Documents extends AppCompatActivity {
    ArrayList<DocumentItem> documents;
    OkHttpClient httpClient;
    RecyclerView recyclerView;
    Gson gson;
    String fileContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);

        documents = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);

        httpClient = new OkHttpClient();
        gson = new Gson();
        //fileContents = gson.toString(HttpCommunication.getRequest(this, httpClient,
        //                                                                "http://62.171.137.95:8080/api/files"));

        loadItems();
        recyclerView.setLayoutManager(new LinearLayoutManager(Documents.this));
        recyclerView.setAdapter(new DocumentsAdapter(Documents.this, documents));
    }

    private void loadItems(){
        for(int i = 1; i <= 10; i++){
            documents.add(new DocumentItem("Document name",
                    "1/" + i + "/2000",  "Document " + i + " text"));
        }
    }
}