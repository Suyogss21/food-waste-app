package com.example.food_wastage_reduction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register_Page extends AppCompatActivity {
TextView gotolog;
EditText name,email,address,pasword;
Button btn;
    FirebaseAuth fAuth;
    FirebaseDatabase database;
    FirebaseFirestore fstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        gotolog=findViewById(R.id.gotologin);
        name=findViewById(R.id.regname);
        email=findViewById(R.id.regemail);
        address=findViewById(R.id.regaddress);
        pasword=findViewById(R.id.regpassword);
        btn=findViewById(R.id.registerdbtn);

        fAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        fstore=FirebaseFirestore.getInstance();

        gotolog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register_Page.this,Login_Page.class);
                startActivity(intent);
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fAuth.createUserWithEmailAndPassword(email.getText().toString(),pasword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            String uid = task.getResult().getUser().getUid();
                            Users users = new Users(name.getText().toString(),email.getText().toString(),address.getText().toString(),pasword.getText().toString(),uid,0);

                            database.getReference().child("Usersregister").child(uid).setValue(users);
                            Toast.makeText(Register_Page.this, "User Create", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register_Page.this, Login_Page.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }
}