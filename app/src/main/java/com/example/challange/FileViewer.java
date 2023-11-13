package com.example.challange;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FileViewer extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_viewer);

        textView = findViewById(R.id.ocr_text);
        textView.setText(getIntent().getStringExtra("fileContent"));
    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }
}