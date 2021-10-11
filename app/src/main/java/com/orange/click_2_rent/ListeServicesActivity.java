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

public class ListeServicesActivity extends AppCompatActivity implements PresentationPrestationAdapter.OnItemClickListener {
    private static final String TAG = "resultats";
    private ArrayList<Service> mServiceList;
    private ListServiceAdapter mAdapter;
    private RecyclerView recyclerView;
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_services);
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
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("services")
                .whereEqualTo("name_provider", ""+user.getUid()+"")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException e) {
                        for (DocumentChange dc: snapshots.getDocumentChanges()) {
                            switch (dc.getType()){
                                case ADDED:
                                    Log.d(TAG, "New city: " + dc.getDocument().getData());

                                    Service service = new Service();
                                    service.setId(dc.getDocument().getString("id"));
                                    service.setTitle(dc.getDocument().getString("title"));
                                    service.setDescription(dc.getDocument().getString("description"));
                                    service.setPhoto_service(dc.getDocument().getString("photo_service"));
                                    service.setName_provider(dc.getDocument().getString("name_provider"));
                                    service.setId(dc.getDocument().getString("id"));
                                    service.setAdd_date(dc.getDocument().getTimestamp("add_date"));
                                    service.setDoc_service(dc.getDocument().getString("doc_service"));
                                    service.setTelephone(dc.getDocument().getString("telephone"));
                                    mServiceList.add(service);

                                    PresentationPrestationAdapter maPresentationAdapteur = new PresentationPrestationAdapter(mServiceList, getApplicationContext());
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    recyclerView.setHasFixedSize(true);
                                    recyclerView.setAdapter(maPresentationAdapteur);
                                    maPresentationAdapteur.setOnItemClickListener(ListeServicesActivity.this);
                                    break;
                                case MODIFIED:
                                    Log.d(TAG, "Modified city: " + dc.getDocument().getData());
                                    break;
                                case REMOVED:
                                    Log.d(TAG, "Removed city: " + dc.getDocument().getData());
                                    break;
                            }
                        }
                    }
                });
    }

    @Override
    public void onIntemClick(int position) {

    }
}