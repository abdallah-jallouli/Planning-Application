package com.example.myplanner;

import androidx.appcompat.app.AppCompatActivity;

//import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        MaterialButton continuebtn = (MaterialButton) findViewById(R.id.continuebtn);

        //admin and admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    //correct
                    Toast.makeText(MainActivity.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this , Interface_Chef.class);
                    startActivity(intent);
                }else
                    //incorrect
                    Toast.makeText(MainActivity.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
            }
        });


        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this , Operator.class);
                //startActivity(intent);
            }
        });


    }
}