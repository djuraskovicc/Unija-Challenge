package com.example.challange;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CAMERA_AND_IMAGES_PERMISSION = 101;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        menuClickListener();

        if(!checkCameraPermission() && !checkStoragePermission()){
            requestPermissions();
        }
    }

    private void menuClickListener(){
        navigationView.setNavigationItemSelectedListener(item -> {
            int itemID = item.getItemId();

            if(itemID == R.id.home){
                return true;
            } else if (itemID == R.id.security) {
                return true;
            } else if (itemID == R.id.scanner){
                startActivity(new Intent(MainActivity.this, ImageScanner.class));
            } else if (itemID == R.id.about) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }

            drawerLayout.closeDrawers();
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        //
        /* MenuItem  recreateDB = menu.findItem(R.id.delete);
        recreateDB.setOnMenuItemClickListener(v -> {


        });
        */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
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