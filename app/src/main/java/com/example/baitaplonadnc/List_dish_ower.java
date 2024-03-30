package com.example.baitaplonadnc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class List_dish_ower extends AppCompatActivity {
    ImageButton bt_home;
    ImageButton bt_search;
    ImageButton bt_edit;
    ImageButton bt_user;
    EditText edt_search;
    ImageButton button_search;
    String bnTimkiem,email;
    private  RecyclerView Relative_dish;
    private Dish_Adapter dishAdapter;
    private LinearLayoutManager linearLayoutManager ;
    private List<Dish> mListDish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_dish_ower);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findID();
        Evenlist();
        linearLayoutManager = new LinearLayoutManager(List_dish_ower.this);
        Relative_dish.setLayoutManager(linearLayoutManager);
        //Lấy dữ liệu vào list
        getlistDishFromRealtimedatabase();

    }
    public void findID(){
        bt_home= findViewById(R.id.bt_home);
        bt_edit=findViewById(R.id.bt_edit);
        bt_search=findViewById(R.id.bt_search);
        bt_user=findViewById(R.id.bt_user);
        Relative_dish=findViewById(R.id.Relative_dish);
        mListDish = new ArrayList<>();
        button_search= findViewById(R.id.button_search);
        edt_search = findViewById(R.id.edt_search);
        dishAdapter = new Dish_Adapter(List_dish_ower.this, mListDish, new Dish_Adapter.IClickListener() {
            @Override
            public void onClick(Dish dish) {
                String ID = dish.getID();
                String namedish = dish.getName_ofDish();
                String calo = dish.getCalories();
                String timenau=dish.getDuration();
                String nguyenlieu = dish.getFood_ingredients();
                String cachnau = dish.getDirections();
                String linkanh = dish.getLinkAnh();
                String classify=dish.getClassify();
                String ower = dish.getOwer();
                Intent intent = new Intent(List_dish_ower.this, Edit_dish.class);
                intent.putExtra("ID",ID );
                intent.putExtra("namedish",namedish );
                intent.putExtra("classify",classify);
                intent.putExtra("calo",calo );
                intent.putExtra("timenau",timenau );
                intent.putExtra("nguyenlieu",nguyenlieu );
                intent.putExtra("cachnau",cachnau );
                intent.putExtra("linkanh",linkanh );
                intent.putExtra("ower",ower);
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
        FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();
        if(User==null){
            return;
        }
        email = User.getEmail();
        Query query = databaseReference.orderByChild("ower").equalTo(email);

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
    void Evenlist(){
        bt_home.setOnClickListener(view -> {
            Intent intent = new Intent(List_dish_ower.this, Home.class);
            startActivity(intent);
        });
        bt_edit.setOnClickListener(view -> {
            Intent intent = new Intent(List_dish_ower.this, Total_calories.class);
            startActivity(intent);
        });
        bt_search.setOnClickListener(view -> {
            Intent intent = new Intent(List_dish_ower.this, Search.class);
            startActivity(intent);
        });
        bt_user.setOnClickListener(view -> {
            Intent intent = new Intent(List_dish_ower.this, User_ac.class);
            startActivity(intent);
        });
        button_search.setOnClickListener(view -> {
            bnTimkiem= edt_search.getText().toString().trim();
            if(bnTimkiem.isEmpty()){
                Toast.makeText(List_dish_ower.this,"Chuỗi rỗng",Toast.LENGTH_SHORT).show();
                getlistDishFromRealtimedatabase();
            }else {
                if(mListDish!=null){
                    mListDish.clear();
                }
                timkiem(bnTimkiem);
            }
        });
    }

    private void timkiem(String bnTimkiem) {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("list_dish");
        Query query = databaseReference.orderByChild("name_ofDish").equalTo(bnTimkiem);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Dish dish=snapshot.getValue(Dish.class);
                //Tìm kiếm với nhiều điều kiện
                if(dish.getName_ofDish().contains(bnTimkiem)&& dish!=null){
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