package com.example.baitaplonadnc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity {
    ImageButton bt_home;
    ImageButton bt_search;
    ImageButton bt_edit;
    ImageButton bt_user;
    ImageButton imageButton_mainfd;
    ImageButton imageButton_monxao;
    ImageButton imageButton_monnuong;
    ImageButton button_salad;
    ImageButton button_lau;
    ImageButton button_hot_soup;
    ImageButton button_sweet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findID();
        Evenlist();
    }
    public void findID(){
        bt_home= findViewById(R.id.bt_home);
        bt_edit=findViewById(R.id.bt_edit);
        bt_search=findViewById(R.id.bt_search);
        bt_user=findViewById(R.id.bt_user);
        imageButton_mainfd=findViewById(R.id.imageButton_mainfd);
        imageButton_monnuong=findViewById(R.id.imageButton_monnuong);
        imageButton_monxao=findViewById(R.id.imageButton_monxao);
        button_hot_soup=findViewById(R.id.button_hot_soup);
        button_lau=findViewById(R.id.button_lau);
        button_sweet=findViewById(R.id.button_sweet);
    }
    void Evenlist(){
        bt_home.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, Home.class);
            startActivity(intent);
        });
        bt_edit.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, Total_calories.class);
            startActivity(intent);
        });
        bt_search.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, Search.class);
            startActivity(intent);
        });
        bt_user.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, User_ac.class);
            startActivity(intent);
        });
    }
}