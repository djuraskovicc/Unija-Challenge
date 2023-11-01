package com.example.challange;

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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CAMERA_AND_IMAGES_PERMISSION = 101;
    DrawerHelper drawerHelper;
    RecyclerView recyclerView;
    ArrayList<GmailItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        items = new ArrayList<>();

        drawerHelper = new DrawerHelper(this);
        drawerHelper.init();
        menuClickListener();

        if(!checkCameraPermission() && !checkStoragePermission()){
            requestPermissions();
        }

        gmailItems();
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new MainAdapter(MainActivity.this, items));
    }

    private void gmailItems(){
        items.add(new GmailItem("Email", "exaple123@gmail.com"));
        items.add(new GmailItem("Email", "exaple124@gmail.com"));
        items.add(new GmailItem("Email", "exaple125@gmail.com"));
        items.add(new GmailItem("Email", "exaple126@gmail.com"));
        items.add(new GmailItem("Email", "exaple127@gmail.com"));
        items.add(new GmailItem("Email", "exaple128@gmail.com"));
        items.add(new GmailItem("Email", "exaple129@gmail.com"));
        items.add(new GmailItem("Email", "exaple130@gmail.com"));
        items.add(new GmailItem("Email", "exaple131@gmail.com"));
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
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.READ_MEDIA_IMAGES};
        ActivityCompat.requestPermissions(MainActivity.this, permissions, REQUEST_CAMERA_AND_IMAGES_PERMISSION);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CAMERA_AND_IMAGES_PERMISSION){
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
