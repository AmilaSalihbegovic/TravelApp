package com.example.travelapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;
import com.example.travelapp.adapters.MyBucketListAdapter;
import com.example.travelapp.models.MyBucketListModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class BucketListFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;

    RecyclerView recyclerView;
    MyBucketListAdapter myBucketListAdapter;
    List<MyBucketListModel> myBucketListModelList;

    public BucketListFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){

        View root = inflater.inflate(R.layout.bucketlist_fragment, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView = root.findViewById(R.id.recycleviewbucketlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        myBucketListModelList = new ArrayList<>();
        myBucketListAdapter = new MyBucketListAdapter(getActivity(), myBucketListModelList);
        recyclerView.setAdapter(myBucketListAdapter);

        db.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                        String documentId = documentSnapshot.getId();

                        MyBucketListModel bucketListModel = documentSnapshot.toObject(MyBucketListModel.class);
                       bucketListModel.setDocumentId(documentId);

                        myBucketListModelList.add(bucketListModel);
                        myBucketListAdapter.notifyDataSetChanged();
                    }
                }
            }
        });


        return root;
    }

}