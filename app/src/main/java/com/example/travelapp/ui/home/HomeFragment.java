package com.example.travelapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;
import com.example.travelapp.adapters.HomeCategoriesAdapret;
import com.example.travelapp.adapters.HomeReccomendedAdapter;
import com.example.travelapp.adapters.PopularAdapter;
import com.example.travelapp.models.HomeCategoriesModel;
import com.example.travelapp.models.HomeReccomendedModel;
import com.example.travelapp.models.PopularModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ScrollView scrollView;
    ProgressBar progressBar;

    RecyclerView popularDes, homeCategoryRec, reccomendedRec;
    FirebaseFirestore db;

    //popular destinations
    List<PopularModel> popularModelList;
    PopularAdapter popularAdapter;

    //Home categories
    List<HomeCategoriesModel> categoriesList;
    HomeCategoriesAdapret homeCategoriesAdapret;

    //Home reccomended
    List<HomeReccomendedModel> reccomendedModelList;
    HomeReccomendedAdapter homeReccomendedAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        db = FirebaseFirestore.getInstance();


        popularDes = root.findViewById(R.id.pop_des);
        homeCategoryRec = root.findViewById(R.id.cat_des);
        reccomendedRec = root.findViewById(R.id.rec_des);
        progressBar = root.findViewById(R.id.progressbar);
        scrollView = root.findViewById(R.id.scrollview);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        popularDes.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularModelList = new ArrayList<>();
        popularAdapter = new PopularAdapter(getActivity(), popularModelList);
        popularDes.setAdapter(popularAdapter);

        db.collection("PopularDestinations")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                            PopularModel popularModel = document.toObject(PopularModel.class);
                            popularModelList.add(popularModel);
                            popularAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);

                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        homeCategoryRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        categoriesList = new ArrayList<>();
        homeCategoriesAdapret = new HomeCategoriesAdapret(getActivity(), categoriesList);
        homeCategoryRec.setAdapter(homeCategoriesAdapret);

        db.collection("HomeCategories")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeCategoriesModel homeCategoriesModel = document.toObject(HomeCategoriesModel.class);
                                categoriesList.add(homeCategoriesModel);
                                homeCategoriesAdapret.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        reccomendedRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        reccomendedModelList = new ArrayList<>();
        homeReccomendedAdapter = new HomeReccomendedAdapter(getActivity(), reccomendedModelList);
        reccomendedRec.setAdapter(homeReccomendedAdapter);

        db.collection("Reccomended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeReccomendedModel homeReccomendedModel = document.toObject(HomeReccomendedModel.class);
                                reccomendedModelList.add(homeReccomendedModel);
                                homeReccomendedAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return root;
    }
}