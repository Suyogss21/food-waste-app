package com.example.food_wastage_reduction;

import static androidx.fragment.app.FragmentManager.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class Myorder extends Fragment {
    FirebaseFirestore db;
    FirebaseAuth auth;

    RecyclerView recyclerView;
    MyorderAdapter myorderAdapter;
    List<Myordermodel> myordermodelList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root=  inflater.inflate(R.layout.fragment_myorder, container, false);
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        recyclerView=root.findViewById(R.id.myorderrec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        myordermodelList = new ArrayList<>();
        myorderAdapter=new MyorderAdapter(getActivity(),myordermodelList);
        recyclerView.setAdapter(myorderAdapter);

        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())

                .collection("MyOrder").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                        String documentId = documentSnapshot.getId();
                        Myordermodel myordermodel = documentSnapshot.toObject(Myordermodel.class);

                        myordermodel.setDocumentId(documentId);

                        myordermodelList.add(myordermodel);
                        myorderAdapter.notifyDataSetChanged();

                    }

                }
            }
        });



        return  root;

    }
}