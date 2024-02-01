package com.example.food_wastage_reduction;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{
    private Context context;
    private List<Foodmodel> foodmodelList;

    public FoodAdapter(Context context, List<Foodmodel> foodmodelList) {
        this.context = context;
        this.foodmodelList = foodmodelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fooditem,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(foodmodelList.get(position).getFood());
        holder.address.setText(foodmodelList.get(position).getUserAddress());
        holder.state.setText(foodmodelList.get(position).getUserState());
        holder.pincode.setText(foodmodelList.get(position).getUserPincode());
        holder.city.setText(foodmodelList.get(position).getUsercity());
        holder.quantity.setText(foodmodelList.get(position).getQuantity());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ViewAll.class);
                i.putExtra("name",foodmodelList.get(position).getFood());
                i.putExtra("address",foodmodelList.get(position).getUserAddress());
                i.putExtra("state",foodmodelList.get(position).getUserState());
                i.putExtra("pincode",foodmodelList.get(position).getUserPincode());
                i.putExtra("city",foodmodelList.get(position).getUsercity());
                i.putExtra("quantity",foodmodelList.get(position).getQuantity());

                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodmodelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,address,state,pincode,city,quantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.foodname);
            address=itemView.findViewById(R.id.foodaddress);
            state=itemView.findViewById(R.id.foodstate);
            pincode = itemView.findViewById(R.id.foodpincode);
            city=itemView.findViewById(R.id.foodcity);
            quantity=itemView.findViewById(R.id.foodquantity);
        }
    }
}
