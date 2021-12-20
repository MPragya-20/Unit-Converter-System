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

public class SignUpActivity extends AppCompatActivity {
    private TextView clicktoSignin;
    private EditText etname, etemail, etpass;
    private Button btsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etname = findViewById(R.id.newname);
        etemail = findViewById(R.id.newemail);
        etpass = findViewById(R.id.newpassword);

        btsignup = findViewById(R.id.signupButton);

        clicktoSignin = findViewById(R.id.signinClick);

        btsignup = findViewById(R.id.signupButton);
        btsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etname.getText().toString().isEmpty() || etemail.getText().toString().isEmpty()||etpass.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter all the required fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences sharedPreferences = getSharedPreferences("Mypref", MODE_PRIVATE);
                    String newUser = etname.getText().toString().trim();
                    String newEmail = etemail.getText().toString().trim();
                    String newPassword = etpass.getText().toString().trim();
                    if (sharedPreferences.getAll().containsKey(newUser+newPassword)) {
                        Toast.makeText(getApplicationContext(), "Please try using different username..", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        SharedPreferences.Editor myditor = sharedPreferences.edit();
                        myditor.putString(newUser + newPassword, newUser + '&' + newEmail + '&' + newPassword);
                        myditor.commit();

                        Intent newIntentobject1 = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(newIntentobject1);
                        Toast.makeText(getApplicationContext(), "Redirecting you to the login page", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
    public void gotosignin(View v){
        Intent newIntentobject2 = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(newIntentobject2);
    }

}