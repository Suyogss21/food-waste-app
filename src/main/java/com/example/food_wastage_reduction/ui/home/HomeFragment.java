package com.example.food_wastage_reduction.ui.home;

import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.food_wastage_reduction.FoodAdapter;
import com.example.food_wastage_reduction.Foodmodel;
import com.example.food_wastage_reduction.R;
import com.example.food_wastage_reduction.ViewPagerAdapter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ViewPager mViewPager;
    int[] images = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4,
            R.drawable.a5};
    ViewPagerAdapter mViewPagerAdapter;

    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    List<Foodmodel> foodmodelList;
    FoodAdapter foodAdapter;
 SearchView sear;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mViewPager = view.findViewById(R.id.viewPagerMain);

        mViewPagerAdapter = new ViewPagerAdapter(getActivity(), images);
        mViewPager.setAdapter(mViewPagerAdapter);


        firebaseFirestore=FirebaseFirestore.getInstance();
        recyclerView=view.findViewById(R.id.popularrec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        foodmodelList = new ArrayList<>();
        foodAdapter = new FoodAdapter(getActivity(),foodmodelList);
        recyclerView.setAdapter(foodAdapter);




        firebaseFirestore.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult() ){
                                Foodmodel foodmodel = document.toObject(Foodmodel.class);
                               foodmodelList.add(foodmodel);
                                foodAdapter.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



        sear=view.findViewById(R.id.search);
        return view;
    }


}