package com.example.challange;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImageScanner extends AppCompatActivity {
    OkHttpClient httpClient;
    ImageButton clear, camera, copy;
    ImageView scanPicture;
    TextView scanText;
    String getUrl, postUrl;
    PreviewView cameraView;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scanner);

        httpClient = new OkHttpClient();
        clear = findViewById(R.id.clear_button);
        camera = findViewById(R.id.camera);
        copy = findViewById(R.id.copy_button);
        scanPicture = findViewById(R.id.scanner_picture);
        scanText = findViewById(R.id.convert_text);
        cameraView = findViewById(R.id.camera_preview);

        cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        camera.setOnClickListener(picture -> startCameraPreview());
    }

    private void startCameraPreview() {
        cameraProviderFuture.addListener(() -> {
            try {
                // Get the camera provider
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                // Set up a camera selector to choose the camera (you can customize this)
                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build();

                // Set up the preview use case
                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(cameraView.getSurfaceProvider());

                // Bind the camera use cases to the camera view
                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(this, cameraSelector, preview);

            } catch (Exception e) {
                Log.e("ImageScanner", "Error starting camera preview: " + e.getLocalizedMessage());
            }
        }, ContextCompat.getMainExecutor(this));
    }

    public void getRequest(){
        Request request = new Request.Builder().url(getUrl).build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }
}