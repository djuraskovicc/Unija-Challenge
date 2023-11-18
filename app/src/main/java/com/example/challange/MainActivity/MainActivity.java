package com.example.challange.MainActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.challange.DocumentsActivity.Documents;
import com.example.challange.DrawerHelper.DrawerHelper;
import com.example.challange.OCRActivity.ImageScanner;
import com.example.challange.LoginActivity.Login;
import com.example.challange.R;
import com.example.challange.SettingsActivity.Settings;
import com.example.challange.UploadedActivity.Uploaded;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSIONS = 101;
    DrawerHelper drawerHelper;
    RecyclerView recyclerView;
    ArrayList<GmailItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_App_Challenge);
        setContentView(R.layout.activity_main);

        drawerHelper = new DrawerHelper(this);
        drawerHelper.init();
        menuClickListener();

        recyclerView = findViewById(R.id.recycler_view);
        items = new ArrayList<>();

        if(!checkCameraPermission()
                || !checkStoragePermission()
                || !checkInternetPermission()
                || !checkNotificationPermission()){

            requestPermissions();
        }

        gmailItems();
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new MainAdapter(MainActivity.this, items));
    }

    private void gmailItems(){
        for(int i = 1; i <= 10; i++){
            items.add(new GmailItem("user" + 1 + "@email.com"));
        }
    }

    private void menuClickListener(){
        drawerHelper.getNavigationView().setNavigationItemSelectedListener(item -> {
            int itemID = item.getItemId();

            if(itemID == R.id.home){
                return true;
            } else if (itemID == R.id.security) {
                return true;
            } else if (itemID == R.id.scanner){
                startActivity(new Intent(MainActivity.this, ImageScanner.class));
                return true;
            } else if (itemID == R.id.about) {
                startActivity(new Intent(MainActivity.this, Login.class));
                return true;
            } else if (itemID == R.id.settings){
                startActivity(new Intent(MainActivity.this, Settings.class));
                return true;
            } else if (itemID == R.id.documents) {
                startActivity(new Intent(MainActivity.this, Documents.class));
            } else if (itemID == R.id.upload) {
                startActivity(new Intent(MainActivity.this, Uploaded.class));
            }

            drawerHelper.getDrawerLayout().closeDrawers();
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerHelper.getDrawerToggle().onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerHelper.getDrawerLayout().isDrawerOpen(GravityCompat.START)){
            drawerHelper.getDrawerLayout().closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    void requestPermissions(){
        String[] permissions = {Manifest.permission.CAMERA,
                Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.INTERNET, Manifest.permission.POST_NOTIFICATIONS};
        ActivityCompat.requestPermissions(MainActivity.this, permissions, REQUEST_PERMISSIONS);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean checkCameraPermission() {
        return ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean checkStoragePermission() {
        return ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean checkInternetPermission() {
        return ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean checkNotificationPermission() {
        return ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_PERMISSIONS){
            boolean permissionsGranted = true;

            for(int grantResult : grantResults){
                if(grantResult != PackageManager.PERMISSION_GRANTED){
                    permissionsGranted = false;
                    requestPermissions();
                }
            }

            Utils.showToast(MainActivity.this, permissionsGranted ? "All permissions granted" : "Permissions required!");
        }
    }
}
