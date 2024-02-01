package com.example.food_wastage_reduction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyorderAdapter extends RecyclerView.Adapter<MyorderAdapter.ViewHolder> {
    Context context;
    List<Myordermodel> myordermodelList;

    public MyorderAdapter(Context context, List<Myordermodel> myordermodelList) {
        this.context = context;
        this.myordermodelList = myordermodelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyorderAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.myorder,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(myordermodelList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return myordermodelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.getproduct);
        }
    }
}
