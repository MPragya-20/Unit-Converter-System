package com.example.ntcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainPageActivity extends AppCompatActivity{
    private TextView welcomeUser,inputvalue_, outputconverted_, prompt_;
    private Spinner spin;
    String choiceSelected;
    String[] choices = { "m-cm","cm-m", "celcius-F",
            "F-celcius", "kg-g",
            "g-kg"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        welcomeUser = findViewById(R.id.wlcmUser);
        inputvalue_ = findViewById(R.id.inputvalue);
        outputconverted_ = findViewById(R.id.outputconverted);
        prompt_ = findViewById(R.id.prompt);

        SharedPreferences sharedPreferences = getSharedPreferences("Mypref", MODE_PRIVATE);
        String displayWelcome = sharedPreferences.getString("display","");
        String[] usernamegot = displayWelcome.split("&");
        welcomeUser.setText("Hi "+usernamegot[0]+"!");

        spin = findViewById(R.id.unitDropdown);
        //binding the options to the spinner
        ArrayAdapter myadapter= new ArrayAdapter(this,R.layout.spinner_style,choices);
        myadapter.setDropDownViewResource( R.layout.spinner_dropdown_style);
        spin.setAdapter(myadapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choiceSelected = parent.getItemAtPosition(position).toString();
                prompt_.setText("Please Enter your input in "+choiceSelected.split("-")[0]);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void convertnow(View v){

        if (inputvalue_.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter the input value", Toast.LENGTH_SHORT).show();
        }
        else{

            if (choiceSelected == "m-cm"){
                outputconverted_.setText(((float) Float.parseFloat(inputvalue_.getText().toString().trim())*100) + " cm");
            }
            else if (choiceSelected == "cm-m"){
                outputconverted_.setText(((float) Float.parseFloat(inputvalue_.getText().toString().trim())/100)+ " m");
            }
            else if (choiceSelected == "celcius-F"){
                outputconverted_.setText( ((((Float.parseFloat(inputvalue_.getText().toString().trim()))*9)/5)+32) + " F");
            }
            else if (choiceSelected == "F-celcius"){
                outputconverted_.setText(((((Float.parseFloat(inputvalue_.getText().toString().trim()))-32)*5)/9) + " °C");
            }
            else if (choiceSelected == "kg-g"){
                outputconverted_.setText(((float) Float.parseFloat(inputvalue_.getText().toString().trim())*1000)+ " g");
            }
            else if (choiceSelected == "g-kg"){
                outputconverted_.setText(((float) Float.parseFloat(inputvalue_.getText().toString().trim())/1000)+ " kg");
            }
        }
    }
    public void clear(View v){
        inputvalue_.setText("");
        outputconverted_.setText("");
    }
}