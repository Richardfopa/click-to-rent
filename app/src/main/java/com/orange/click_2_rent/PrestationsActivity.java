package com.orange.click_2_rent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PrestationsActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private ArrayList<Presentation_prestations> maListe;
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
        maListe = new ArrayList<Presentation_prestations>();
        db = FirebaseFirestore.getInstance();
        db.collection("services")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot doc: task.getResult())
                        {
                            Presentation_prestations model = new Presentation_prestations(doc.getString("name_provider"),
                                    doc.getString("description"),doc.getTimestamp("add_date"),
                                    doc.getString("photo_service")
                            );
                            maListe.add(model);
                        }
                        maPresentationAdapteur = new PresentationPrestationAdapter(maListe);
                        mRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        mRecycler.setHasFixedSize(true);
                        mRecycler.setAdapter(maPresentationAdapteur);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item = menu.findItem(R.id.Rechercher);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                maPresentationAdapteur.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void AjoutService(View view) {
        Intent intent = new Intent(getApplicationContext(),AddServiceActivity.class);
        startActivity(intent);
    }

//    private ArrayList<Presentation_prestations>  initData(){

//        maListe = new ArrayList<>();
//        maListe.add(new Presentation_prestations(R.drawable.techniciens,"Developpeurs JAKARTA/JAKARTA","Je suis developpeur JAVA depuis 2012 et j'ai 3 logiciels realiser a mon actif","18/12/2021"));
//        maListe.add(new Presentation_prestations(R.drawable.techniciens,"Developpeurs JAKARTA/JAKARTA","Je suis developpeur JAVA depuis 2012 et j'ai 3 logiciels realiser a mon actif","01/06/2021"));
//        maListe.add(new Presentation_prestations(R.drawable.techniciens,"Developpeurs JAKARTA/JAKARTA","Je suis developpeur JAVA depuis 2012 et j'ai 3 logiciels realiser a mon actif","02/03/2021"));
//        maListe.add(new Presentation_prestations(R.drawable.techniciens,"Developpeurs JAKARTA/JAKARTA","Je suis developpeur JAVA depuis 2012 et j'ai 3 logiciels realiser a mon actif","12/05/2021"));
//        maListe.add(new Presentation_prestations(R.drawable.techniciens,"Developpeurs JAKARTA/JAKARTA","Je suis developpeur JAVA depuis 2012 et j'ai 3 logiciels realiser a mon actif","06/02/2021"));
//        maListe.add(new Presentation_prestations(R.drawable.techniciens,"Developpeurs JAKARTA/JAKARTA","Je suis developpeur JAVA depuis 2012 et j'ai 3 logiciels realiser a mon actif","10/02/2021"));
//        maListe.add(new Presentation_prestations(R.drawable.techniciens,"Developpeurs JAKARTA/JAKARTA","Je suis developpeur JAVA depuis 2012 et j'ai 3 logiciels realiser a mon actif","10/07/2021"));
//
//        return maListe;
//    }
}
