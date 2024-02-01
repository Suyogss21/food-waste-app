package com.example.food_wastage_reduction;

import static androidx.fragment.app.FragmentManager.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mycart extends Fragment {
    FirebaseFirestore db;
    FirebaseAuth auth;

    TextView total;
    Button buynow;
    RecyclerView recyclerView;
    MycardAdapter mycardAdapter;
    List<Mycartmodel> mycartmodelList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=  inflater.inflate(R.layout.fragment_mycart, container, false);

        //total=root.findViewById(R.id.totalprice);
        buynow=root.findViewById(R.id.buynow);


        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        recyclerView=root.findViewById(R.id.mycartrec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        mycartmodelList = new ArrayList<>();
        mycardAdapter=new MycardAdapter(getActivity(),mycartmodelList);
        recyclerView.setAdapter(mycardAdapter);

        db.collection("AddtoCard").document(auth.getCurrentUser().getUid())

                .collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                        String documentId = documentSnapshot.getId();


                        Mycartmodel mycartmodel = documentSnapshot.toObject(Mycartmodel.class);

                        mycartmodel.setDocumentId(documentId);

                        mycartmodelList.add(mycartmodel);
                        mycardAdapter.notifyDataSetChanged();
                    }


                }
            }
        });
buynow.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), PlaceOrder.class);
        intent.putExtra("itemList",(Serializable) mycartmodelList);
        startActivity(intent);
        }

});




        return root;
    }

    }

