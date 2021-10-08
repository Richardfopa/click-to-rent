package com.orange.click_2_rent;

import static com.google.firebase.firestore.DocumentChange.Type.ADDED;
import static com.orange.click_2_rent.Models.FirebasesUtil.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.orange.click_2_rent.Firebase.FireBaseUtils;
import com.orange.click_2_rent.Models.FirebasesUtil;
import com.orange.click_2_rent.Models.Service;

import java.util.ArrayList;

public class ListeServicesActity extends AppCompatActivity {
    private static final String TAG = "resultats";
    private ArrayList<Service> mServiceList;
    private ListServiceAdapter mAdapter;
    private RecyclerView recyclerView;
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_services_actity);
        mServiceList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        FloatingActionButton fab = findViewById(R.id.fab);
        Intent intent = new Intent(this, AjoutServiceActivity.class);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent);
            }
        });
        EventChangeListener();
    }

    public void EventChangeListener() {

        FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_SERVICES)
                .whereEqualTo("id", user.getUid())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "listen:error", e);
                            return;
                        }
                        for (DocumentChange document : snapshots.getDocumentChanges()) {
                            switch (document.getType()){
                                case ADDED:
                                    Service model = new Service();
                                    model.setTitle(document.getDocument().getString("title"));
                                    mServiceList.add(model);

                                    mAdapter = new ListServiceAdapter(getApplicationContext(), mServiceList);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    recyclerView.setHasFixedSize(true);
                                    recyclerView.setAdapter(mAdapter);
                                    break;
                                case MODIFIED:
                                    Log.d(TAG, "Modified city: " + document.getDocument().getData());
                                    break;
                                case REMOVED:
                                    Log.d(TAG, "Removed city: " + document.getDocument().getData());
                                    break;
                            }
                        }
                    }
                });
    }
}