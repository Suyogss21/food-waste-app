package com.example.food_wastage_reduction;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


public class MyProfile extends Fragment {
    TextView name,email,address;
    private FirebaseUser user;
    private DatabaseReference reference;
    String userID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_profile, container, false);
        /*name=root.findViewById(R.id.pname);
        email=root.findViewById(R.id.pemail);
        address=root.findViewById(R.id.paddress);*/
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Usersregister");
        userID = user.getUid();


        final TextView na = (TextView)root.findViewById(R.id.pname);
        final TextView em = (TextView)root.findViewById(R.id.pemail);
        final TextView ad = (TextView)root.findViewById(R.id.paddress);
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users profile = snapshot.getValue(Users.class);
                if (profile != null){
                    String name = profile.getName();
                    String email = profile.getEmail();
                    String add = profile.getAddress();


                    na.setText(name);
                    em.setText(email);
                    ad.setText(add);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return root;
    }
}