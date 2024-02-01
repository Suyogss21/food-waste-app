package com.example.food_wastage_reduction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlaceOrder extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        List<Mycartmodel> list = (ArrayList<Mycartmodel>) getIntent().getSerializableExtra("itemList");

        if (list !=null && list.size()>0 ){
            for (Mycartmodel model : list){
                final HashMap<String,Object> cartMap = new HashMap<>();
                cartMap.put("Name",model.getName());



                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(PlaceOrder.this, "Your Order is Placed", Toast.LENGTH_SHORT).show();

                    }

                });
            }
        }



    }
}