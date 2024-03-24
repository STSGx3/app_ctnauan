package com.example.baitaplonadnc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity{
    Button Sign_in;
    Button Sign_up;
    EditText EditEmail;
    EditText EditPassword;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        callID();
        Sign_up.setOnClickListener(v -> Sign_up());
        Sign_in.setOnClickListener(v -> Sign_in());
    }
    private void callID(){
        Sign_in= findViewById(R.id.button_sign_in);
        Sign_up= findViewById(R.id.button_sign_up);
        EditEmail = findViewById(R.id.EditEmail);
        EditPassword=findViewById(R.id.EditPassword);
    }
    private void Sign_up(){
        Intent intent = new Intent(MainActivity.this, Sign_up.class);
        startActivity(intent);
    }
    private void Sign_in(){
        String email,password;
        email=EditEmail.getText().toString().trim();
        password=EditPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this,"Hãy nhập đầy đủ dữ liệu",Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length()<6){
            Toast.makeText(MainActivity.this,"mật khẩu sai",Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(MainActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
            }else {
                Toast.makeText(MainActivity.this,"Đăng nhập không thành công",Toast.LENGTH_SHORT).show();
            }
        });
    }

}