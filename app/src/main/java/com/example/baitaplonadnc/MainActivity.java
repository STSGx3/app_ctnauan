package com.example.baitaplonadnc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button Sign_in;
    Button Sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Sign_in=(Button) findViewById(R.id.button_sign_in);
        Sign_up=(Button) findViewById(R.id.button_sign_up);
        Sign_up.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Sign_up.class);
            startActivity(intent);
        });
        Sign_in.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
        });
    }

}