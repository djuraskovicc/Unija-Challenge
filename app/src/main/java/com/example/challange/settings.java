package com.example.challange;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

public class settings extends Activity {

    private Switch stayLoggedInSwitch;
    private Spinner languageSpinner;
    private Button saveButton;

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
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save settings and return to the main activity
                finish();
            }
        });
    }
}
