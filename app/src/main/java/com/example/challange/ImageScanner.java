package com.example.challange;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageScanner extends AppCompatActivity {
    ImageButton clear, camera, copy;
    EditText extractedText;
    ImageView scanPicture;
    TextView scanText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scanner);

        clear = findViewById(R.id.clear_button);
        camera = findViewById(R.id.camera);
        copy = findViewById(R.id.copy_button);
        extractedText = findViewById(R.id.extracted_text);
        scanPicture = findViewById(R.id.scanner_picture);
        scanText = findViewById(R.id.convert_text);

        camera.setOnClickListener(picture -> Utils.showToast(ImageScanner.this, "Cheese"));
    }
}