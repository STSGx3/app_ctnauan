package com.example.baitaplonadnc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Soup extends AppCompatActivity {
    ImageButton bt_home;
    ImageButton bt_search;
    ImageButton bt_edit;
    ImageButton bt_user;
    private RecyclerView Relative_dish;
    private Dish_Adapter dishAdapter;
    private LinearLayoutManager linearLayoutManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_soup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findID();
        Evenlist();
        linearLayoutManager = new LinearLayoutManager(Soup.this);
        Relative_dish.setLayoutManager(linearLayoutManager);
        //Lấy dữ liệu vào list
        //dishAdapter.setData(getListDish());
        Relative_dish.setAdapter(dishAdapter);
    }

    private void Evenlist() {
        //Thanh điều hướng bên dưới
        bt_home.setOnClickListener(view -> {
            Intent intent = new Intent(Soup.this, Home.class);
            startActivity(intent);
        });
        bt_edit.setOnClickListener(view -> {
            Intent intent = new Intent(Soup.this, Total_calories.class);
            startActivity(intent);
        });
        bt_search.setOnClickListener(view -> {
            Intent intent = new Intent(Soup.this, Search.class);
            startActivity(intent);
        });
        bt_user.setOnClickListener(view -> {
            Intent intent = new Intent(Soup.this, User_ac.class);
            startActivity(intent);
        });
    }

    public void findID() {
        bt_home = findViewById(R.id.bt_home);
        bt_edit = findViewById(R.id.bt_edit);
        bt_search = findViewById(R.id.bt_search);
        bt_user = findViewById(R.id.bt_user);
    }
    private List<Dish> getListDish(){
        List<Dish> list = new ArrayList<>();
        //test list


        return list;
    }
}