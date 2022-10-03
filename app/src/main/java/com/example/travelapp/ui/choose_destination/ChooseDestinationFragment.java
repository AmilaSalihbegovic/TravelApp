package com.example.travelapp.ui.choose_destination;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;
import com.example.travelapp.adapters.ChooseAdapter;
import com.example.travelapp.adapters.NavCategoryAdapter;
import com.example.travelapp.models.CategoryModel;
import com.example.travelapp.models.ChooseModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ChooseDestinationFragment extends Fragment {
    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<ChooseModel> chooseModels;
    ChooseAdapter chooseAdapter;

    ProgressBar progressBar;
    RadioButton solo, friends, couple, family, summer, fall, spring, winter;
    ImageView img;
    TextView  text2, text3, text4;

    Button commit;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.choose_destination_fragment, container, false);

        commit = root.findViewById(R.id.commit_btn);
        summer = root.findViewById(R.id.summerbtn);
        solo = root.findViewById(R.id.rbsolo);
        friends = root.findViewById(R.id.rbfriends);
        couple = root.findViewById(R.id.rbcouple);
        family = root.findViewById(R.id.rbfamily);
        fall = root.findViewById(R.id.fallbtn);
        spring = root.findViewById(R.id.springbtn);
        winter = root.findViewById(R.id.winterbtn);
        img = root.findViewById(R.id.survey_img);
        text2 = root.findViewById(R.id.question1);
        text3 = root.findViewById(R.id.question2);
        text4 = root.findViewById(R.id.text_headline);
        recyclerView =root.findViewById(R.id.results);
        progressBar = root.findViewById(R.id.progressbar_choose);
        db = FirebaseFirestore.getInstance();

        progressBar.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        chooseModels = new ArrayList<>();
        chooseAdapter = new ChooseAdapter(getActivity(), chooseModels);
        recyclerView.setAdapter(chooseAdapter);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                img.setVisibility(View.GONE);
                text2.setVisibility(View.GONE);
                text3.setVisibility(View.GONE);
                text4.setVisibility(View.GONE);
                commit.setVisibility(View.GONE);
                summer.setVisibility(View.GONE);
                spring.setVisibility(View.GONE);
                fall.setVisibility(View.GONE);
                winter.setVisibility(View.GONE);
                solo.setVisibility(View.GONE);
                couple.setVisibility(View.GONE);
                family.setVisibility(View.GONE);
                friends.setVisibility(View.GONE);

                if (summer.isChecked()==true && solo.isChecked()==true  || spring.isChecked()==true && solo.isChecked()==true )
                {
                    db.collection("SoloTraveler")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            ChooseModel chooseModel = document.toObject(ChooseModel.class);
                                            chooseModels.add(chooseModel);
                                            chooseAdapter.notifyDataSetChanged();
                                            progressBar.setVisibility(View.GONE);
                                            //scrollView.setVisibility(View.VISIBLE);

                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else  if (summer.isChecked()==true && couple.isChecked()==true || spring.isChecked()==true && couple.isChecked()==true){
                    db.collection("CoupleTravelers")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            ChooseModel chooseModel = document.toObject(ChooseModel.class);
                                            chooseModels.add(chooseModel);
                                            chooseAdapter.notifyDataSetChanged();
                                            progressBar.setVisibility(View.GONE);

                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else if (summer.isChecked()==true && family.isChecked()==true || spring.isChecked()==true && family.isChecked()==true){
                    db.collection("FamilyVacation")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            ChooseModel chooseModel = document.toObject(ChooseModel.class);
                                            chooseModels.add(chooseModel);
                                            chooseAdapter.notifyDataSetChanged();
                                            progressBar.setVisibility(View.GONE);

                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else if (summer.isChecked()==true && friends.isChecked()==true || spring.isChecked()==true && friends.isChecked()==true){
                    db.collection("FriendsVacation")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            ChooseModel chooseModel = document.toObject(ChooseModel.class);
                                            chooseModels.add(chooseModel);
                                            chooseAdapter.notifyDataSetChanged();
                                            progressBar.setVisibility(View.GONE);

                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else if (winter.isChecked()==true && solo.isChecked()==true || fall.isChecked()==true && solo.isChecked()==true){
                    db.collection("SoloWinterVacation")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            ChooseModel chooseModel = document.toObject(ChooseModel.class);
                                            chooseModels.add(chooseModel);
                                            chooseAdapter.notifyDataSetChanged();
                                            progressBar.setVisibility(View.GONE);

                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else if (winter.isChecked()==true && couple.isChecked()==true || fall.isChecked()==true && couple.isChecked()==true){
                    db.collection("WinterCoupleVacation")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            ChooseModel chooseModel = document.toObject(ChooseModel.class);
                                            chooseModels.add(chooseModel);
                                            chooseAdapter.notifyDataSetChanged();
                                            progressBar.setVisibility(View.GONE);

                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else if (winter.isChecked()==true && family.isChecked()==true || fall.isChecked()==true && family.isChecked()==true){
                    db.collection("WinterFamilyVacation")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            ChooseModel chooseModel = document.toObject(ChooseModel.class);
                                            chooseModels.add(chooseModel);
                                            chooseAdapter.notifyDataSetChanged();
                                            progressBar.setVisibility(View.GONE);

                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else if (winter.isChecked()==true && friends.isChecked()==true || fall.isChecked()==true && friends.isChecked()==true){
                    db.collection("WinterFriendsVacation")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            ChooseModel chooseModel = document.toObject(ChooseModel.class);
                                            chooseModels.add(chooseModel);
                                            chooseAdapter.notifyDataSetChanged();
                                            progressBar.setVisibility(View.GONE);

                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        return root;
    }

}