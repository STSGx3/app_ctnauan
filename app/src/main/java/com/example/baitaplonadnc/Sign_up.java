package com.example.baitaplonadnc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_up extends AppCompatActivity {
    Button Sign_up;
    EditText EditEmail;
    EditText EditPassword;
    EditText Edit_Confirm_Password;
    FirebaseAuth mAut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mAut=FirebaseAuth.getInstance();
        callID();
        Sign_up.setOnClickListener(view -> sign_up());
    }
    private void callID(){
        Sign_up=findViewById(R.id.button_sign_up2);
        EditEmail = findViewById(R.id.EditEmail);
        EditPassword=findViewById(R.id.EditPassword);
        Edit_Confirm_Password=findViewById(R.id.Edit_Confirm_Password);
    }
    private void sign_up() {
        String email,password,CFpassword;
        email=EditEmail.getText().toString().trim();
        password=EditPassword.getText().toString().trim();
        CFpassword=Edit_Confirm_Password.getText().toString().trim();
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)||TextUtils.isEmpty(CFpassword)){
            Toast.makeText(Sign_up.this,"Hãy nhập đầy đủ dữ liệu",Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length()<6){
            Toast.makeText(Sign_up.this,"mật khẩu sai",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!CFpassword.equals(password)){
            Toast.makeText(Sign_up.this,"nhập lại mật khẩu sai",Toast.LENGTH_SHORT).show();
            return;
        }
        mAut.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Sign_up.this,"Tạo tài khoản thành công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Sign_up.this, Home.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Sign_up.this,"Tạo tài khoản không thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}