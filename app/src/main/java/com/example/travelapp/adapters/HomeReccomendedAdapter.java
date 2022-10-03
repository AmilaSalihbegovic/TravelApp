package com.example.travelapp.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelapp.R;
import com.example.travelapp.activities.DetailedActivity;
import com.example.travelapp.models.HomeReccomendedModel;

import java.util.List;


public class HomeReccomendedAdapter extends RecyclerView.Adapter<HomeReccomendedAdapter.ViewHolder> {

    private Context context;
    private List<HomeReccomendedModel> reccomendedModelList;

    public HomeReccomendedAdapter(Context context, List<HomeReccomendedModel> reccomendedModelList) {
        this.context = context;
        this.reccomendedModelList = reccomendedModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recommended_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(reccomendedModelList.get(position).getImg_url()).into(holder.rec_img);
        holder.name.setText(reccomendedModelList.get(position).getName());
        holder.description.setText(reccomendedModelList.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detail", reccomendedModelList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reccomendedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, description;
        ImageView rec_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rec_img = itemView.findViewById(R.id.recommended_img);
            name = itemView.findViewById(R.id.recommended_text);
            description = itemView.findViewById(R.id.rec_description);
        }
    }
}
