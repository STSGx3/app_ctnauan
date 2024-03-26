package com.example.baitaplonadnc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Add_dish extends AppCompatActivity {

    ImageButton bt_edit,bt_user,bt_search,bt_home;

    Button button_Confirm_add_dish;
    EditText edittext_namedish_add_dish,
            edittext_Food_ingredients_add_dish,
            edittext_Directions_add_dish,
            edittext_Calories_add_dish,
            edittext_Duration_add_dish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_dish);
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
            Intent intent = new Intent(Add_dish.this, Home.class);
            startActivity(intent);
        });
        bt_edit.setOnClickListener(view -> {
            Intent intent = new Intent(Add_dish.this, Total_calories.class);
            startActivity(intent);
        });
        bt_search.setOnClickListener(view -> {
            Intent intent = new Intent(Add_dish.this, Search.class);
            startActivity(intent);
        });
        bt_user.setOnClickListener(view -> {
            Intent intent = new Intent(Add_dish.this, User_ac.class);
            startActivity(intent);
        });
        //Đẩy dữ liệu lên firebase
        button_Confirm_add_dish.setOnClickListener(view -> {
            Adddish();
        });
    }

    public void findID() {
        bt_home = findViewById(R.id.bt_home);
        bt_edit = findViewById(R.id.bt_edit);
        bt_search = findViewById(R.id.bt_search);
        bt_user = findViewById(R.id.bt_user);
        button_Confirm_add_dish=findViewById(R.id.button_Confirm_add_dish);
    }
    private void Adddish(){

    }
}