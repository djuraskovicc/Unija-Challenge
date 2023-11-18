package com.example.challange.DocumentsActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.challange.R;

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