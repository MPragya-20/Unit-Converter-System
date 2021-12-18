package com.example.ntcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainPageActivity extends AppCompatActivity {
    private TextView welcomeUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        welcomeUser = findViewById(R.id.wlcmUser);

        SharedPreferences sharedPreferences = getSharedPreferences("Mypref", MODE_PRIVATE);
        String displayWelcome = sharedPreferences.getString("display","");
        String[] usernamegot = displayWelcome.split("@");
        welcomeUser.setText("Welcome "+usernamegot[0]+"!");
    }
}