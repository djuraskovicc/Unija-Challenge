package com.example.challange;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class FileViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_viewer);
    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }
}