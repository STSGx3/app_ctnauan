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

public class Search extends AppCompatActivity {
    ImageButton bt_home;
    ImageButton bt_search;
    ImageButton bt_edit;
    ImageButton bt_user;
    private  RecyclerView Relative_dish;
    private Dish_Adapter dishAdapter;
    LinearLayoutManager linearLayoutManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        linearLayoutManager = new LinearLayoutManager(Search.this);
        Relative_dish.setLayoutManager(linearLayoutManager);
        //Lấy dữ liệu vào list
        dishAdapter.setData(getListDish());
        Relative_dish.setAdapter(dishAdapter);

        findID();
        Evenlist();
    }
    public void findID(){
        bt_home= findViewById(R.id.bt_home);
        bt_edit=findViewById(R.id.bt_edit);
        bt_search=findViewById(R.id.bt_search);
        bt_user=findViewById(R.id.bt_user);
        Relative_dish=findViewById(R.id.Relative_dish);
        dishAdapter = new Dish_Adapter();

    }
    private List<Dish> getListDish(){
        List<Dish> list = new ArrayList<>();
        //test list
        list.add(new Dish(R.drawable.anh1,"Test 1",40));
        list.add(new Dish(R.drawable.electric_grill,"Test 1",40));
        return list;
    }
    void Evenlist(){
        bt_home.setOnClickListener(view -> {
            Intent intent = new Intent(Search.this, Home.class);
            startActivity(intent);
        });
        bt_edit.setOnClickListener(view -> {
            Intent intent = new Intent(Search.this, Total_calories.class);
            startActivity(intent);
        });
        bt_search.setOnClickListener(view -> {
            Intent intent = new Intent(Search.this, Search.class);
            startActivity(intent);
        });
        bt_user.setOnClickListener(view -> {
            Intent intent = new Intent(Search.this, User_ac.class);
            startActivity(intent);
        });
    }
}