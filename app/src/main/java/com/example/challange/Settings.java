package com.example.challange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    Switch stayLoggedInSwitch;
    Spinner languageSpinner;
    DrawerHelper drawerHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        languageSpinner = findViewById(R.id.languageSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.language_entries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        drawerHelper = new DrawerHelper(this);
        drawerHelper.init();
        menuClickListener();
    }

    private void menuClickListener(){
        DrawerHelper.getNavigationView().setNavigationItemSelectedListener(item -> {
            int itemID = item.getItemId();

            if(itemID == R.id.home){
                startActivity(new Intent(Settings.this, MainActivity.class));
                return true;
            } else if (itemID == R.id.security) {
                return true;
            } else if (itemID == R.id.scanner){
                startActivity(new Intent(Settings.this, ImageScanner.class));
            } else if (itemID == R.id.about) {
                startActivity(new Intent(Settings.this, Login.class));
            }

            DrawerHelper.getDrawerLayout().closeDrawers();
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(DrawerHelper.getDrawerToggle().onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        languageSpinner.setAdapter(null);
        finish();
        super.onDestroy();
    }
}