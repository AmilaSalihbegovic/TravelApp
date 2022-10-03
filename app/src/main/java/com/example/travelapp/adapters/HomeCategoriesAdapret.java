package com.example.travelapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelapp.R;
import com.example.travelapp.activities.LearnMoreActivity;
import com.example.travelapp.models.HomeCategoriesModel;

import java.util.List;

public class HomeCategoriesAdapret extends RecyclerView.Adapter<HomeCategoriesAdapret.ViewHolder> {

    Context context;
    List<HomeCategoriesModel> categoriesList;

    public HomeCategoriesAdapret(Context context, List<HomeCategoriesModel> categoriesList) {
        this.context = context;
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cat_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(categoriesList.get(position).getImg_url()).into(holder.catImg);
        holder.name.setText(categoriesList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LearnMoreActivity.class);
                intent.putExtra("type", categoriesList.get(holder.getAdapterPosition()).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        ImageView catImg;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catImg = itemView.findViewById(R.id.icon_home_category);
            name = itemView.findViewById(R.id.category_name);
        }
    }
}
