package com.example.challange.UploadedActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.challange.R;

public class Image extends AppCompatActivity {
    ImageView imageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView = findViewById(R.id.image_icon);

        String imageUrl = getIntent().getStringExtra("image");
        Glide.with(this).load(imageUrl).into(imageView);
    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }
}