package com.example.baitaplonadnc;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class User_ac extends AppCompatActivity {
    Bitmap selectedImageBitmap;
    Uri ImageUri;
    ImageButton bt_home;
    ImageButton bt_search;
    ImageButton bt_edit;
    ImageButton bt_user;
    ImageButton imageButton_plus;
    TextView textView_email;
    ImageView profile_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findID();
        Evenlist();
        ShowUserIF();
        UpdateIMGuser();
    }
    public void findID(){
        bt_home= findViewById(R.id.bt_home);
        bt_edit=findViewById(R.id.bt_edit);
        bt_search=findViewById(R.id.bt_search);
        bt_user=findViewById(R.id.bt_user);
        imageButton_plus=findViewById(R.id.imageButton_plus);
        textView_email = findViewById(R.id.textView_email);
        profile_image=findViewById(R.id.profile_image);
    }
    void Evenlist(){
        bt_home.setOnClickListener(view -> {
            Intent intent = new Intent(User_ac.this, Home.class);
            startActivity(intent);
        });
        bt_edit.setOnClickListener(view -> {
            Intent intent = new Intent(User_ac.this, Total_calories.class);
            startActivity(intent);
        });
        bt_search.setOnClickListener(view -> {
            Intent intent = new Intent(User_ac.this, Search.class);
            startActivity(intent);
        });
        bt_user.setOnClickListener(view -> {
            Intent intent = new Intent(User_ac.this, User_ac.class);
            startActivity(intent);
        });
        imageButton_plus.setOnClickListener(view -> {
            Intent intent = new Intent(User_ac.this, Add_dish.class);
            startActivity(intent);
        });
    }
    private void ShowUserIF(){
        FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();
        if(User==null){
            return;
        }
        String email = User.getEmail();
        Uri photoUrl = User.getPhotoUrl();
        textView_email.setText("Email: "+email);
        Glide.with(this).load(photoUrl).error(R.drawable.user).into(profile_image);
    }
    private void UpdateIMGuser(){
        profile_image.setOnClickListener(view -> {
            //Gọi quyền mở lấy ảnh và gán luôn ảnh cho user_ac
            imageChooser();
        });
    }


    //
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
                                    User_ac.this.getContentResolver(),
                                    ImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        profile_image.setImageBitmap(
                                selectedImageBitmap);
                        UpdateToFirebase();
                    }
                }
            });

    private void UpdateToFirebase() {
        FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();
        if(User==null){
            return;
        }
        // Lấy ảnh Uri ném vào biến tạm lưu dữ liệu update của firebase
            UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                    .setPhotoUri(ImageUri)
                    .build();
            User.updateProfile(profileUpdate).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(User_ac.this, "Cập nhật ảnh thành công", Toast.LENGTH_SHORT).show();
                    ShowUserIF();
                }
            });

    }
}