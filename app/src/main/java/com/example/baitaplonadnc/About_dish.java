package com.example.baitaplonadnc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

public class About_dish extends AppCompatActivity {


    ImageButton bt_home;
    ImageButton bt_search;
    ImageButton bt_edit;
    ImageButton bt_user;
    ImageButton imageButton_plus_about_dish;

    String ID ;
    String namedish ;
    String calo ;
    String timenau;
    String nguyenlieu ;
    String cachnau ;
    String linkanh ;

    ImageView image_view_about_dish;
    TextView Text_view_name_dish_about_dish,
            Text_view_time_make_dish_about_dish,
            Text_view_calo_dish_about_dish,
            Text_view_Ingredients_dish,
            Text_view_Directions_dish;
    Dish dish;
    List<Dish> dishList;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about_dish);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        ID= intent.getStringExtra("ID");
        namedish= intent.getStringExtra("namedish");
        calo= intent.getStringExtra("calo");
        timenau= intent.getStringExtra("timenau");
        nguyenlieu= intent.getStringExtra("nguyenlieu");
        cachnau= intent.getStringExtra("cachnau");
        linkanh= intent.getStringExtra("linkanh");
        findID();
        Evenlist();
        capnhatNoidung();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("list_totalCalo");
    }

    private void Evenlist() {
        //Thanh điều hướng bên dưới
        bt_home.setOnClickListener(view -> {
            Intent intent = new Intent(About_dish.this, Home.class);
            startActivity(intent);
        });
        bt_edit.setOnClickListener(view -> {
            Intent intent = new Intent(About_dish.this, Total_calories.class);
            startActivity(intent);
        });
        bt_search.setOnClickListener(view -> {
            Intent intent = new Intent(About_dish.this, Search.class);
            startActivity(intent);
        });
        bt_user.setOnClickListener(view -> {
            Intent intent = new Intent(About_dish.this, User_ac.class);
            startActivity(intent);
        });
        imageButton_plus_about_dish.setOnClickListener(view -> {
            ListTotal_Calo();
            Intent intent = new Intent(About_dish.this,Total_calories.class);
            intent.putExtra("ID",ID );
            startActivity(intent);
        });
    }

    public void findID() {
        bt_home = findViewById(R.id.bt_home);
        bt_edit = findViewById(R.id.bt_edit);
        bt_search = findViewById(R.id.bt_search);
        bt_user = findViewById(R.id.bt_user);
        //Ánh xạ ID nội dung
        image_view_about_dish=findViewById(R.id.image_view_about_dish);
        Text_view_name_dish_about_dish=findViewById(R.id.Text_view_name_dish_about_dish);
        Text_view_time_make_dish_about_dish=findViewById(R.id.Text_view_time_make_dish_about_dish);
        Text_view_calo_dish_about_dish=findViewById(R.id.Text_view_calo_dish_about_dish);
        Text_view_Ingredients_dish=findViewById(R.id.Text_view_Ingredients_dish);
        Text_view_Directions_dish=findViewById(R.id.Text_view_Directions_dish);
        //Chuyen ND
        imageButton_plus_about_dish=findViewById(R.id.imageButton_plus_about_dish);
    }
    private void capnhatNoidung(){
        Uri uri = Uri.parse(linkanh);
        Glide.with(About_dish.this).load(uri).error(R.drawable.image).into(image_view_about_dish);
        Text_view_name_dish_about_dish.setText(namedish);
        String bn = "Duration: "+timenau;
        Text_view_time_make_dish_about_dish.setText(bn);
        bn = "Calories: "+calo+" Kcal";
        Text_view_calo_dish_about_dish.setText(bn);
        Text_view_Ingredients_dish.setText(nguyenlieu);
        Text_view_Directions_dish.setText(cachnau);
    }

    private void ListTotal_Calo(){
        String bn = null;
        myRef = FirebaseDatabase.getInstance().getReference("list_totalCalo");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
             bn = String.valueOf(java.time.LocalDate.now());
        }
        String finalBn = bn;

        myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    myRef.child(finalBn).child(ID).setValue(ID);
                    Toast.makeText(About_dish.this, "Thêm thành công ", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(About_dish.this, "Thêm không thành công ", Toast.LENGTH_SHORT).show();
                }
            });

    }

}