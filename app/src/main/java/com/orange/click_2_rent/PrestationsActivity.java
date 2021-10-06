package com.orange.click_2_rent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
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
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot doc: task.getResult())
                        {
                            Service model = new Service();
                            model.setName_provider(doc.getString("name_provider"));
                            model.setDescription(doc.getString("description"));
                            model.setAddDate(doc.getTimestamp("add_date"));
                            model.setPhoto_service(doc.getString("photo_service"));

                            maListe.add(model);
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
        Intent intent = new Intent(getApplicationContext(),AddUserActivity.class);
        startActivity(intent);
    }
}
