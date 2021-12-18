package com.example.ntcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private TextView clicktoSignup;
    private EditText etoldname, etoldpassword;
    private Button btlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etoldname = findViewById(R.id.oldname);
        etoldpassword = findViewById(R.id.oldpassword);
        clicktoSignup = findViewById(R.id.signupClick);

        btlogin = findViewById(R.id.loginButton);
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etoldname.getText().toString().isEmpty() || etoldpassword.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter all the required fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    String username = etoldname.getText().toString().trim();
                    String userpassword = etoldpassword.getText().toString().trim();
                    SharedPreferences sharedPreferences = getSharedPreferences("Mypref", MODE_PRIVATE);
                    if (sharedPreferences.getAll().containsKey(username+userpassword)) {
                        String checking = sharedPreferences.getString(username + userpassword, "Please check your username and password");
                        SharedPreferences.Editor myeditor = sharedPreferences.edit();
                        myeditor.putString("display", checking);
                        myeditor.commit();
                        Intent newIntentobject3 = new Intent(LoginActivity.this, MainPageActivity.class);
                        startActivity(newIntentobject3);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Please check your username and password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    public void gotosignup(View v){
        Intent newIntentobject = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(newIntentobject);
    }
}