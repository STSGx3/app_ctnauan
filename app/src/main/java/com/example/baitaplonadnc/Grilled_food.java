package com.example.baitaplonadnc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Grilled_food extends AppCompatActivity {
    ImageButton bt_home;
    ImageButton bt_search;
    ImageButton bt_edit;
    ImageButton bt_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grilled_food);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findID();
        Evenlist();
    }

    private void Evenlist() {
        //Thanh điều hướng bên dưới
        bt_home.setOnClickListener(view -> {
            Intent intent = new Intent(Grilled_food.this, Home.class);
            startActivity(intent);
        });
        bt_edit.setOnClickListener(view -> {
            Intent intent = new Intent(Grilled_food.this, Total_calories.class);
            startActivity(intent);
        });
        bt_search.setOnClickListener(view -> {
            Intent intent = new Intent(Grilled_food.this, Search.class);
            startActivity(intent);
        });
        bt_user.setOnClickListener(view -> {
            Intent intent = new Intent(Grilled_food.this, User_ac.class);
            startActivity(intent);
        });
    }

    public void findID() {
        bt_home = findViewById(R.id.bt_home);
        bt_edit = findViewById(R.id.bt_edit);
        bt_search = findViewById(R.id.bt_search);
        bt_user = findViewById(R.id.bt_user);
    }
}