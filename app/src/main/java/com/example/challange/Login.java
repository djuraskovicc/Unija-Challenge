package com.example.challange;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(view -> {
            if (username.getText().toString().equals("user") && password.getText().toString().equals("1234")) {
                Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Login.this, "Login Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}