package com.example.baitaplonadnc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class Hot_pot extends AppCompatActivity {
    ImageButton bt_home;
    ImageButton bt_search;
    ImageButton bt_edit;
    ImageButton bt_user;
    private RecyclerView Relative_dish;
    private Dish_Adapter dishAdapter;
    private LinearLayoutManager linearLayoutManager ;
    private List<Dish> mListDish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hot_pot);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findID();
        Evenlist();
        linearLayoutManager = new LinearLayoutManager(Hot_pot.this);
        Relative_dish.setLayoutManager(linearLayoutManager);
        //Lấy dữ liệu vào list
        getlistDishFromRealtimedatabase();
    }

    private void Evenlist() {
        //Thanh điều hướng bên dưới
        bt_home.setOnClickListener(view -> {
            Intent intent = new Intent(Hot_pot.this, Home.class);
            startActivity(intent);
        });
        bt_edit.setOnClickListener(view -> {
            Intent intent = new Intent(Hot_pot.this, Total_calories.class);
            startActivity(intent);
        });
        bt_search.setOnClickListener(view -> {
            Intent intent = new Intent(Hot_pot.this, Search.class);
            startActivity(intent);
        });
        bt_user.setOnClickListener(view -> {
            Intent intent = new Intent(Hot_pot.this, User_ac.class);
            startActivity(intent);
        });
    }

    public void findID() {
        bt_home = findViewById(R.id.bt_home);
        bt_edit = findViewById(R.id.bt_edit);
        bt_search = findViewById(R.id.bt_search);
        bt_user = findViewById(R.id.bt_user);
        Relative_dish=findViewById(R.id.Relative_dish);
        mListDish = new ArrayList<>();
        dishAdapter = new Dish_Adapter(Hot_pot.this, mListDish, new Dish_Adapter.IClickListener() {
            @Override
            public void onClick(Dish dish) {
                String ID = dish.getID();
                String namedish = dish.getName_ofDish();
                String calo = dish.getCalories();
                String timenau=dish.getDuration();
                String nguyenlieu = dish.getFood_ingredients();
                String cachnau = dish.getDirections();
                String linkanh = dish.getLinkAnh();
                Intent intent = new Intent(Hot_pot.this, About_dish.class);
                intent.putExtra("ID",ID );
                intent.putExtra("namedish",namedish );
                intent.putExtra("calo",calo );
                intent.putExtra("timenau",timenau );
                intent.putExtra("nguyenlieu",nguyenlieu );
                intent.putExtra("cachnau",cachnau );
                intent.putExtra("linkanh",linkanh );
                startActivity(intent);
            }
        });
        Relative_dish.setAdapter(dishAdapter);
    }
    private void getlistDishFromRealtimedatabase(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("list_dish");
        if(mListDish!=null){
            mListDish.clear();
        }
        Query query = databaseReference.orderByChild("classify").equalTo("Hot_pot");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Dish dish=snapshot.getValue(Dish.class);
                if(dish!=null) {
                    mListDish.add(dish);
                    dishAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}