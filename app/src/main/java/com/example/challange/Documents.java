package com.example.challange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class Documents extends AppCompatActivity {
    ArrayList<DocumentItem> documents;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);

        documents = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);

        documentItems();
        recyclerView.setLayoutManager(new LinearLayoutManager(Documents.this));
        recyclerView.setAdapter(new DocumentsAdapter(Documents.this, documents));
    }

    private void documentItems(){
        for(int i = 0; i <= 10; i++){
            documents.add(new DocumentItem("Document name", "1/" + i + "/2000"));
        }
    }
}