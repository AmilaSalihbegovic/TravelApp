package com.example.travelapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.travelapp.R;
import com.example.travelapp.models.CategoryModel;
import com.example.travelapp.models.ChooseModel;
import com.example.travelapp.models.HomeReccomendedModel;
import com.example.travelapp.models.PopularModel;
import com.example.travelapp.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView name, description;
    Button addBucketList;
    Toolbar toolbar;

    ViewAllModel viewAllModel = null;
    HomeReccomendedModel homeRecommendedModel = null;
    PopularModel popularModel = null;
    ChooseModel chooseModel = null;

    FirebaseFirestore firestore;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        detailedImg = findViewById(R.id.detailed_img);
        name = findViewById(R.id.detailed_name);
        description = findViewById(R.id.detailed_description);
        addBucketList = findViewById(R.id.add_bucket_list);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        toolbar = findViewById(R.id.toolbar_detailed);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ViewAllModel){
            viewAllModel = (ViewAllModel) object;
        }

        if (viewAllModel != null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImg);
            name.setText(viewAllModel.getName());
            description.setText(viewAllModel.getDescription());
        }
        if (object instanceof HomeReccomendedModel){
            homeRecommendedModel = (HomeReccomendedModel) object;
        }

        if (homeRecommendedModel != null){
            Glide.with(getApplicationContext()).load(homeRecommendedModel.getImg_url()).into(detailedImg);
            name.setText(homeRecommendedModel.getName());
            description.setText(homeRecommendedModel.getDescription());
        }
        if (object instanceof PopularModel){
            popularModel = (PopularModel) object;
        }

        if (popularModel != null){
            Glide.with(getApplicationContext()).load(popularModel.getImg_url()).into(detailedImg);
            name.setText(popularModel.getName());
            description.setText(popularModel.getDescription());
        }
        if (object instanceof ChooseModel){
            chooseModel = (ChooseModel) object;
        }

        if (chooseModel != null){
            Glide.with(getApplicationContext()).load(chooseModel.getImg_url()).into(detailedImg);
            name.setText(chooseModel.getName());
            description.setText(chooseModel.getDescription());
        }

        addBucketList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBucketList();
            }
        });
    }

    private void addBucketList() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());
        final Object object = getIntent().getSerializableExtra("detail");
        final HashMap<String, Object> cartMap = new HashMap<>();

        if (object instanceof ViewAllModel) {
            cartMap.put("DestinationName", viewAllModel.getName());
            cartMap.put("DestinationDescription", viewAllModel.getDescription());
            cartMap.put("CurrentDate", saveCurrentDate);
            cartMap.put("CurrentTime", saveCurrentTime);
        }else if (object instanceof HomeReccomendedModel) {
            cartMap.put("DestinationName", homeRecommendedModel.getName());
            cartMap.put("DestinationDescription", homeRecommendedModel.getDescription());
            cartMap.put("CurrentDate", saveCurrentDate);
            cartMap.put("CurrentTime", saveCurrentTime);
        }else if (object instanceof ChooseModel) {
            cartMap.put("DestinationName", chooseModel.getName());
            cartMap.put("DestinationDescription", chooseModel.getDescription());
            cartMap.put("CurrentDate", saveCurrentDate);
            cartMap.put("CurrentTime", saveCurrentTime);
        } else if (object instanceof PopularModel) {
            cartMap.put("DestinationName", popularModel.getName());
            cartMap.put("DestinationDescription", popularModel.getDescription());
            cartMap.put("CurrentDate", saveCurrentDate);
            cartMap.put("CurrentTime", saveCurrentTime);
        }
        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailedActivity.this, "Added to the bucket list", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}