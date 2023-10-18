package com.example.challange;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    Switch stayLoggedInSwitch;
    Spinner languageSpinner;
    Button saveButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        stayLoggedInSwitch = findViewById(R.id.stayLoggedInSwitch);
        languageSpinner = findViewById(R.id.languageSpinner);
        saveButton = findViewById(R.id.saveButton);

        // Set up the language options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.language_entries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        // Handle the "Save" button click
        saveButton.setOnClickListener(view -> {
            // Save settings and return to the main activity
            startActivity(new Intent(Settings.this, MainActivity.class));
            finish();
        });
    }
}