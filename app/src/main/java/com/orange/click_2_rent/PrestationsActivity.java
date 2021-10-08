package com.orange.click_2_rent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.orange.click_2_rent.Models.Service;

import java.util.ArrayList;

public class PrestationsActivity extends AppCompatActivity {



    private RecyclerView mRecycler;
    private final ArrayList<Service> maListe = new ArrayList<>();;
    private PresentationPrestationAdapter maPresentationAdapteur;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nos_prestations);
        mRecycler = findViewById(R.id.idNosPrestations);

        EventChangeListener();
    }

    public void EventChangeListener()
    {

        db = FirebaseFirestore.getInstance();
        db.collection("services")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null){
                            Log.w("error:",error);
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            switch (dc.getType()){
                                case ADDED:
                                    Log.d("ADD:",dc.getDocument().getData().toString());
//                                    Tache tache = dc.getDocument().toObject(Tache.class);
                                    Service service = new Service();
                                    service.setPhoto_service(dc.getDocument().getString("photo_service"));
                                    service.setName_provider(dc.getDocument().getString("name_provider"));
                                    service.setId(dc.getDocument().getString("id"));
                                    service.setAdd_date(dc.getDocument().getTimestamp("add_date"));
                                    maListe.add(service);
//                                    maPresentationAdapteur.this.notifyItemInserted(0);
                                    break;
                                case MODIFIED:
                                    break;
                                case REMOVED:
                                    break;
                                default:
                                    throw new IllegalStateException("Unexpected value: " + dc.getType());
                            }
                        }
                        maPresentationAdapteur = new PresentationPrestationAdapter(maListe,getApplicationContext());
                        mRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        mRecycler.setHasFixedSize(true);
                        mRecycler.setAdapter(maPresentationAdapteur);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.searchmenu, menu);
        MenuItem menuItem = menu.findItem(R.id.Rechercher);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("rechercher un prestataire");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String nouveauText) {

                maPresentationAdapteur.getFilter().filter(nouveauText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.Rechercher){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void AjoutService(View view) {
        Intent intent = new Intent(getApplicationContext(), AjoutServiceActivity.class);
        startActivity(intent);
    }
}