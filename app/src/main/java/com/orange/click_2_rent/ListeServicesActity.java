package com.orange.click_2_rent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.orange.click_2_rent.Models.Service;

import java.util.ArrayList;

public class ListeServicesActity extends AppCompatActivity {
    private ArrayList<Service> mServiceList;
    private ListServiceAdapter mAdapter;
    private RecyclerView recyclerView;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_services_actity);
        mServiceList = new ArrayList<Service>();
        recyclerView = findViewById(R.id.recyclerview);
        EventChangeListener();
    }

    public void EventChangeListener() {

        db = FirebaseFirestore.getInstance();
        db.collection("services")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        FirebaseAuth mAuth = FirebaseAuth.getInstance();
                        FirebaseUser user = mAuth.getCurrentUser();

                        for (DocumentSnapshot doc : task.getResult()) {
                            if (user.getUid() == doc.getString("id")){
                                Service model = new Service(doc.getString("title"));
                                mServiceList.add(model);
                            }
                        }
                        mAdapter = new ListServiceAdapter(getApplicationContext(), mServiceList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(mAdapter);
                    }
                });
    }
}