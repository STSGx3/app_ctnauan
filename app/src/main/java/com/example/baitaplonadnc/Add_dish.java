package com.example.baitaplonadnc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.util.List;

public class Add_dish extends AppCompatActivity {
    String bn;
    Dish dish;
    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference myRef;
    StorageReference storageRef;
    String thamchieu;
    String ID,Calories,email,Name_ofDish,Food_ingredients,Directions,Duration,LinkAnh;
    String Classify;
    Uri ImageUri;
    Uri VideoUri;
    ImageButton bt_edit,bt_user,bt_search,bt_home;
    ImageView imageButton_addimg_add_dish;
    List<Dish> mListDish;
    Button button_Confirm_add_dish;
    EditText edittext_namedish_add_dish,
            edittext_Food_ingredients_add_dish,
            edittext_Directions_add_dish,
            edittext_Calories_add_dish,
            edittext_Duration_add_dish,
            edittext_ID;
    RadioButton radiobutton_Hot_pot,
            radiobutton_Sweets,
            radiobutton_Soup,
            radiobutton_Grilled_food,
            radiobutton_Stir_fried_meal,
            radiobutton_Salat,
            radiobutton_main_food;

    private volatile boolean running = true;

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
        dish = new Dish();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("list_dish");
        findID();
        Evenlist();

        //LayDS();
    }


    private void Evenlist() {
        //Thanh điều hướng bên dưới
        bt_home.setOnClickListener(view -> {
            XoaDL();
            Intent intent = new Intent(Add_dish.this, Home.class);
            startActivity(intent);
        });
        bt_edit.setOnClickListener(view -> {
            XoaDL();
            Intent intent = new Intent(Add_dish.this, Total_calories.class);
            startActivity(intent);
        });
        bt_search.setOnClickListener(view -> {
            XoaDL();
            Intent intent = new Intent(Add_dish.this, Search.class);
            startActivity(intent);
        });
        bt_user.setOnClickListener(view -> {
            XoaDL();
            Intent intent = new Intent(Add_dish.this, User_ac.class);
            startActivity(intent);
        });
        //Lấy ảnh ra
        imageButton_addimg_add_dish.setOnClickListener(view -> {
            imageChooser();
        });
        //Đẩy dữ liệu lên firebase
        button_Confirm_add_dish.setOnClickListener(view -> {
           addDish();
        });
    }
    private void addDish(){
        FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();
        if(User==null){
            return;
        }
        // lấy về dữ liệu ID cuối cùng
        /*
        Dish lastDish = mListDish.get(mListDish.size()-1);
        String bn = lastDish.getID();
        if(bn==null){
            ID = "1";
        }else {
            int bn1 = Integer.parseInt(bn);
            int bn2 = bn1+1;
            ID= String.valueOf(bn2).toString().trim();
        }
        Vẫn đang nghĩ chưa chạy đúng
         */
        email = User.getEmail();
        GetDataClassify();
        ID = edittext_ID.getText().toString().trim();
        Name_ofDish=edittext_namedish_add_dish.getText().toString().trim();
        Food_ingredients=edittext_Food_ingredients_add_dish.getText().toString().trim();
        Directions=edittext_Directions_add_dish.getText().toString().trim();
        Calories=edittext_Calories_add_dish.getText().toString().trim();
        Duration=edittext_Duration_add_dish.getText().toString().trim();
        if(ID==null||email==null||Name_ofDish==null||Food_ingredients==null||Directions==null||edittext_Calories_add_dish.getText()==null||Duration==null){
            return;
        }
        //Đẩy ảnh lên storage
        AddImgToStorage();
        if(LinkAnh==null){
            LinkAnh="aaa";
        }
        else {
            Toast.makeText(Add_dish.this,"Chuoi ko rong ",Toast.LENGTH_SHORT).show();
        }
        //Thêm thông tin về món ăn lên reatime database
        AddTTdish(ID, Name_ofDish, Food_ingredients, Directions, Calories, Duration, Classify, email, LinkAnh);
    }

    public void findID() {
        bt_home = findViewById(R.id.bt_home);
        bt_edit = findViewById(R.id.bt_edit);
        bt_search = findViewById(R.id.bt_search);
        bt_user = findViewById(R.id.bt_user);
        button_Confirm_add_dish=findViewById(R.id.button_Confirm_add_dish);
        imageButton_addimg_add_dish = findViewById(R.id.imageButton_addimg_add_dish);
        //Thông tin về món ăn
        edittext_namedish_add_dish=findViewById(R.id.edittext_namedish_add_dish);
        edittext_Food_ingredients_add_dish=findViewById(R.id.edittext_Food_ingredients_add_dish);
        edittext_Directions_add_dish=findViewById(R.id.edittext_Directions_add_dish);
        edittext_Calories_add_dish=findViewById(R.id.edittext_Calories_add_dish);
        edittext_Duration_add_dish=findViewById(R.id.edittext_Duration_add_dish);
        edittext_ID=findViewById(R.id.edittext_ID);
        //Loại món ăn
        radiobutton_Hot_pot=findViewById(R.id.radiobutton_Hot_pot);
        radiobutton_Sweets=findViewById(R.id.radiobutton_Sweets);
        radiobutton_Soup=findViewById(R.id.radiobutton_Soup);
        radiobutton_Grilled_food=findViewById(R.id.radiobutton_Grilled_food);
        radiobutton_Stir_fried_meal=findViewById(R.id.radiobutton_Stir_fried_meal);
        radiobutton_Salat=findViewById(R.id.radiobutton_Salat);
        radiobutton_main_food=findViewById(R.id.radiobutton_main_food);
    }
    private void GetDataClassify() {
        if(radiobutton_Hot_pot.isChecked()){
            Classify="Hot_pot";
        }
        if(radiobutton_Sweets.isChecked()){
            Classify="Sweets";
        }
        if(radiobutton_Soup.isChecked()){
            Classify="Soup";
        }
        if(radiobutton_Grilled_food.isChecked()){
            Classify="Grilled_food";
        }
        if(radiobutton_Stir_fried_meal.isChecked()){
            Classify="Stir_fried_meal";
        }
        if(radiobutton_Salat.isChecked()){
            Classify="Salat";
        }
        if(radiobutton_main_food.isChecked()){
            Classify="main_food";
        }
    }
    //Vẫn đang nghĩ
    private void LayDS(){
        myRef = FirebaseDatabase.getInstance().getReference("list_dish");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Dish dish1=snapshot.getValue(Dish.class);
                if(dish1!=null) {
                    mListDish.add(dish1);
                    String bn = dish.getID();
                    Toast.makeText(Add_dish.this,"ID "+bn,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {}
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
    //Đẩy ảnh lên storage
    private void AddImgToStorage(){
        storage = FirebaseStorage.getInstance();
        storageRef=storage.getReference("Image_dish");
        StorageReference bnStRf = storageRef.child("image" + ID + ".png");
        bnStRf.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                bnStRf.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        bn=uri.toString();
                        LinkAnh=bn;
                        Toast.makeText(Add_dish.this,"Thêm thành công "+bn,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    @SuppressLint("SuspiciousIndentation")
    private void AddTTdish(String ID,
                           String Name_ofDish,
                           String Food_ingredients,
                           String Directions,
                           String Calories,
                           String Duration,
                           String Classify,
                           String Ower, String Linkanh){
        //Ghi dữ liệu vào để chuyển lên firebase
        myRef = FirebaseDatabase.getInstance().getReference("list_dish");

        //Thêm dữ liệu
        dish.setID(ID);
        dish.setName_ofDish(Name_ofDish);
        dish.setFood_ingredients(Food_ingredients);
        dish.setDirections(Directions);
        dish.setCalories(Calories);
        dish.setDuration(Duration);
        dish.setClassify(Classify);
        dish.setOwer(Ower);
        dish.setLinkAnh(Linkanh);
        //Xác định biến để tham chiếu ở đây dùng ID
        thamchieu = String.valueOf(dish.getID());
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    myRef.child(thamchieu).setValue(dish);
                    Toast.makeText(Add_dish.this, "Thêm thành công ", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Add_dish.this, "Thêm không thành công ", Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void imageChooser()
    {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        launchSomeActivity.launch(i);
    }

    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        ImageUri = data.getData();
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    Add_dish.this.getContentResolver(),
                                    ImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        BitmapDrawable drawable = new BitmapDrawable(getResources(), selectedImageBitmap);
                        imageButton_addimg_add_dish.setBackground(drawable);
                    }
                }
            });
    private void XoaDL(){
        edittext_ID.setText(null);
        edittext_namedish_add_dish.setText(null);
        edittext_Food_ingredients_add_dish.setText(null);
        edittext_Directions_add_dish.setText(null);
        edittext_Calories_add_dish.setText(null);
        edittext_Duration_add_dish.setText(null);
    }
    private void upvideo(){
        storage = FirebaseStorage.getInstance();
        storageRef=storage.getReference("Video_dish");
        StorageReference bnStRf = storageRef.child("video" + ID + ".mp4");
        bnStRf.putFile(VideoUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                bnStRf.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        bn=uri.toString();
                        LinkAnh=bn;
                        Toast.makeText(Add_dish.this,"Thêm thành công "+bn,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}