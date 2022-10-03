package com.example.travelapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;
import com.example.travelapp.models.MyBucketListModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyBucketListAdapter extends RecyclerView.Adapter<MyBucketListAdapter.ViewHolder> {


    Context context;
    List<MyBucketListModel> myBucketListModelList;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public MyBucketListAdapter(Context context, List<MyBucketListModel> myBucketListModelList) {
        this.context = context;
        this.myBucketListModelList = myBucketListModelList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_bucket_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(myBucketListModelList.get(position).getDestinationName());
        holder.description.setText(myBucketListModelList.get(position).getDestinationDescription());
        holder.date.setText(myBucketListModelList.get(position).getCurrentDate());
        holder.time.setText(myBucketListModelList.get(position).getCurrentTime());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                        .collection("CurrentUser")
                        .document((myBucketListModelList.get(holder.getAdapterPosition()).getDocumentId()))
                        .delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            myBucketListModelList.remove(myBucketListModelList.get(holder.getAdapterPosition()));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Destination removed", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return myBucketListModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       TextView name, description, date, time;
        ImageView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.destination_name);
            description = itemView.findViewById(R.id.destination_description);
            date = itemView.findViewById(R.id.destination_date);
            time = itemView.findViewById(R.id.destination_time);
            delete = itemView.findViewById(R.id.delete_item);
        }
    }
}
