package com.example.food_wastage_reduction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ViewAll extends AppCompatActivity {
TextView name,address,state,pincode,city,quantity,food;
    Button addtocard;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        //name=findViewById(R.id.name);
        //address=findViewById(R.id.address);
        //state=findViewById(R.id.state);
        //pincode=findViewById(R.id.pincode);
        //city=findViewById(R.id.city);
        //quantity=findViewById(R.id.kg);
        addtocard=findViewById(R.id.addtocardbtn);
        firebaseFirestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();


        Intent i = getIntent();

        String Foodname = i.getStringExtra("name");
        name = findViewById(R.id.name);
        name.setText(Foodname);

        String Address = i.getStringExtra("address");
        address = findViewById(R.id.address);
        address.setText(Address);

        String State = i.getStringExtra("state");
        state = findViewById(R.id.state);
        state.setText(State);

        String PinCode = i.getStringExtra("pincode");
        pincode = findViewById(R.id.pincode);
        pincode.setText(PinCode);

        String City = i.getStringExtra("city");
        city = findViewById(R.id.city);
        city.setText(City);

        String Quantity = i.getStringExtra("quantity");
        quantity = findViewById(R.id.kg);
        quantity.setText(Quantity);


        addtocard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String saveCurrentDate;
                Calendar calForDate = Calendar.getInstance();


                SimpleDateFormat currentDate = new SimpleDateFormat("MM, dd, yyyy");
                saveCurrentDate = currentDate.format(calForDate.getTime());




                final HashMap<String,Object> cartMap = new HashMap<>();
                cartMap.put("Name",Foodname);
                cartMap.put("Address",Address);
                cartMap.put("State",State);
                cartMap.put("PinCode",PinCode);
                cartMap.put("City",City);
                cartMap.put("Quntity",Quantity);
                cartMap.put("Time",saveCurrentDate);


                firebaseFirestore.collection("AddtoCard").document(auth.getCurrentUser().getUid())
                        .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(ViewAll.this, "Add To Cart", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                });

            }

        });







    }
}