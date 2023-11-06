package com.example.challange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import com.example.http.HttpComunication;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import okhttp3.OkHttpClient;

public class ImageScanner extends AppCompatActivity {
    OkHttpClient httpClient;
    ImageButton clear, camera, copy;
    PreviewView cameraView;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private ImageCapture imageCapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scanner);

        httpClient = new OkHttpClient();
        clear = findViewById(R.id.clear_button);
        camera = findViewById(R.id.camera);
        copy = findViewById(R.id.copy_button);
        cameraView = findViewById(R.id.camera_preview);

        cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        startCameraPreview();
        camera.setOnClickListener(picture -> captureImage());
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

                // Initialize the ImageCapture use case and bind it to the camera
                imageCapture = new ImageCapture.Builder().build();

                // Bind the camera use cases to the camera view
                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);

            } catch (Exception e) {
                Log.e("ImageScanner", "Error starting camera preview: " + e.getLocalizedMessage());
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void captureImage(){
        if (imageCapture != null) {
            File fileOutput = new File(getExternalFilesDir(null), "image.jpg");
            ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(fileOutput).build();

            imageCapture.takePicture(outputFileOptions, ContextCompat.getMainExecutor(this), new ImageCapture.OnImageSavedCallback() {
                @Override
                public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                    HttpComunication.postRequest(ImageScanner.this, httpClient, fileOutput, "http://62.171.137.95:8080/api/imageUpload");
                }

                @Override
                public void onError(@NonNull ImageCaptureException exception) {
                    Log.e("ImageScanner", "Error capturing image: " + exception.getMessage());
                }
            });
        } else {
            Log.e("ImageScanner", "ImageCapture is not initialized");
        }
    }
}