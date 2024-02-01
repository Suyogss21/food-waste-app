package com.example.food_wastage_reduction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MycardAdapter extends RecyclerView.Adapter<MycardAdapter.ViewHolder>{
    Context context;
    List<Mycartmodel> mycartmodelList;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;

    public MycardAdapter(Context context, List<Mycartmodel> mycartmodelList) {
        this.context = context;
        this.mycartmodelList = mycartmodelList;
        firebaseFirestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mycartmodelList.get(position).getName());

        holder.deleteitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseFirestore.collection("AddtoCard").document(auth.getCurrentUser().getUid())
                        .collection("CurrentUser")
                        .document(mycartmodelList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    mycartmodelList.remove(mycartmodelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Item Delete", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }

    @Override
    public int getItemCount() {
        return mycartmodelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price;
        ImageView deleteitem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.cartproduct);
            deleteitem=itemView.findViewById(R.id.delete);

        }
    }
}
