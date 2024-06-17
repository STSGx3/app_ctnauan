package com.example.baitaplonadnc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

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

public class Total_calories extends AppCompatActivity {
    int totalCL;
    ArrayList listtotalCalo;
    ImageButton bt_home;
    ImageButton bt_search;
    ImageButton bt_edit;
    ImageButton bt_user;
    String ID ;
    String namedish ;
    String calo ;
    String timenau;
    String nguyenlieu ;
    String cachnau ;
    String linkanh ;
    TextView text_view_total_calo;

    private RecyclerView Relative_dish;
    private Dish_Adapter dishAdapter;
    private List<Dish> mListDish;
    private LinearLayoutManager linearLayoutManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_total_calories);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        ID= intent.getStringExtra("ID");
        findID();
        Evenlist();
        linearLayoutManager = new LinearLayoutManager(Total_calories.this);
        Relative_dish.setLayoutManager(linearLayoutManager);
        getListID_totalCalo();
        getlistDishFromRealtimedatabase();
        onPause();
    }
    public void findID(){
        bt_home= findViewById(R.id.bt_home);
        bt_edit=findViewById(R.id.bt_edit);
        bt_search=findViewById(R.id.bt_search);
        bt_user=findViewById(R.id.bt_user);
        text_view_total_calo=findViewById(R.id.text_view_total_calo);
        Relative_dish=findViewById(R.id.Relative_dish);
        mListDish = new ArrayList<>();
        dishAdapter = new Dish_Adapter(Total_calories.this, mListDish, new Dish_Adapter.IClickListener() {
            @Override
            public void onClick(Dish dish) {
                String ID = dish.getID();
                String namedish = dish.getName_ofDish();
                String calo = dish.getCalories();
                String timenau=dish.getDuration();
                String nguyenlieu = dish.getFood_ingredients();
                String cachnau = dish.getDirections();
                String linkanh = dish.getLinkAnh();
                Intent intent = new Intent(Total_calories.this, About_dish.class);
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
    void Evenlist(){
        bt_home.setOnClickListener(view -> {
            Intent intent = new Intent(Total_calories.this, Home.class);
            startActivity(intent);
        });
        bt_edit.setOnClickListener(view -> {
            Intent intent = new Intent(Total_calories.this, Total_calories.class);
            startActivity(intent);
        });
        bt_search.setOnClickListener(view -> {
            Intent intent = new Intent(Total_calories.this, Search.class);
            startActivity(intent);
        });
        bt_user.setOnClickListener(view -> {
            Intent intent = new Intent(Total_calories.this, User_ac.class);
            startActivity(intent);
        });
    }
    private void getListID_totalCalo(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("list_totalCalo");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                int bn= (int) snapshot.getValue();
                if(bn!=0) {
                    //Lọc có điều kiện if(Integer.parseInt(dish.getCalories())<=200) {
                    listtotalCalo.add(bn);
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
    private void getlistDishFromRealtimedatabase(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("list_dish");
        String bn = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
             bn =String.valueOf(java.time.LocalDate.now());
        }
        Query query = null;
        FirebaseDatabase database1=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1 = database.getReference("list_totalCalo");
        String bn2= String.valueOf(databaseReference1.orderByChild("list_totalCalo"));
        if(!bn.equals(bn2)){
            return;
        }
            databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Dish dish=snapshot.getValue(Dish.class);
                if(dish!=null) {
                    for(int i=0;i<listtotalCalo.size();i++) {
                        if(dish.getID()==String.valueOf(listtotalCalo.get(i))) {
                            mListDish.add(dish);
                            dishAdapter.notifyDataSetChanged();
                            totalCL = totalCL + Integer.parseInt(dish.getCalories());
                            text_view_total_calo.setText("Total calories: " + totalCL);
                        }
                    }
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