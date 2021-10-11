package com.orange.click_2_rent;

import static com.firebase.ui.auth.AuthUI.TAG;
import static com.orange.click_2_rent.ConnexionActivity.EXTRA_CONNE;
import static com.orange.click_2_rent.PrestationsActivity.EXTRA_ADD_DATE;
import static com.orange.click_2_rent.PrestationsActivity.EXTRA_NAME_PROVIDER;
import static com.orange.click_2_rent.PrestationsActivity.EXTRA_PHOTO_SERVICE;
import static com.orange.click_2_rent.PrestationsActivity.EXTRA_TELEPHONE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.orange.click_2_rent.Models.Service;

import java.util.ArrayList;

public class PresentationMenuiserie extends AppCompatActivity implements PresentationPrestationAdapter.OnItemClickListener {

    private static final String EXTRA_COMPTE = "compte";
    private RecyclerView mRecycler;
    private final ArrayList<Service> maListe = new ArrayList<>();;
    private PresentationPrestationAdapter maPresentationAdapteur;
    private FirebaseFirestore db;
    public static final String EXTRA_IMAGE = "image";
    public static final String EXTRA_NOM = "nom";
    public static final String EXTRA_DESCRIPTION = "description";
    public static final String EXTRA_CONNEXION = "connexion";
    public static final String EXTRA = "extra";
    public String connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation_menuiserie);


        mRecycler = findViewById(R.id.idNosPrestations);

        EventChangeListener();
    }


    public void EventChangeListener()
    {

        db = FirebaseFirestore.getInstance();
        db.collection(getString(R.string.services))
                .whereEqualTo(getString(R.string.categorie), getString(R.string.menuiserie))
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "listen:error", e);
                            return;
                        }

                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            switch (dc.getType()) {
                                case ADDED:
                                    Log.d(TAG, "New city: " + dc.getDocument().getData());
                                    Service model = new Service ();
                                    model.setName_provider(dc.getDocument().getString("nom_prestataire"));
                                    model.setDescription(dc.getDocument().getString("description"));
                                    model.setAdd_date(dc.getDocument().getTimestamp("add_date"));
                                    model.setPhoto_service(dc.getDocument().getString("photo_service"));
                                    maListe.add(model);
                                    maPresentationAdapteur = new PresentationPrestationAdapter(maListe,getApplicationContext());
                                    mRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    mRecycler.setHasFixedSize(true);
                                    mRecycler.setAdapter(maPresentationAdapteur);
                                    maPresentationAdapteur.setOnItemClickListener(PresentationMenuiserie.this);
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

//        FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_SERVICES)
////        db = FirebaseFirestore.getInstance();
////        db.collection("services")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        for(DocumentSnapshot doc: task.getResult())
//                        {
//                            Presentation_prestations model = new Presentation_prestations(doc.getString("name_provider"),
//                                    doc.getString("description"),doc.getTimestamp("add_date"),
//                                    doc.getString("photo_service")
//                            );
//                            maListe.add(model);
//                        }
//                        maPresentationAdapteur = new PresentationPrestationAdapter(maListe,getApplicationContext());
//                        mRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                        mRecycler.setHasFixedSize(true);
//                        mRecycler.setAdapter(maPresentationAdapteur);
//                    }
//                });
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
        Intent intent = new Intent(getApplicationContext(),AjoutServiceActivity.class);
        //intent.putExtra(EXTRA_COMPTE,"compte");
        startActivity(intent);
    }

    @Override
    public void onIntemClick(int position) {


        String connect;
        Intent intent2 = getIntent();
        connect = intent2.getStringExtra(EXTRA_CONNE);

        if(connect == "null")
        {
            Intent connexion = new Intent(getApplicationContext(),ConnexionActivity.class);
            connexion.putExtra(EXTRA_CONNEXION,"connecter");
            startActivity(connexion);

        }
        else if(connect != "null")
        {
            Intent detail = new Intent(getApplicationContext(), ContactezNousActivity.class);
            Service presentation_prestations = maListe.get(position);
            detail.putExtra(EXTRA_IMAGE,presentation_prestations.getPhoto_service());
            detail.putExtra(EXTRA_NOM, presentation_prestations.getTitle());
            detail.putExtra(EXTRA_DESCRIPTION, presentation_prestations.getDescription());
            detail.putExtra(EXTRA_TELEPHONE, presentation_prestations.getTelephone());
            detail.putExtra(EXTRA_PHOTO_SERVICE, presentation_prestations.getPhoto_service());
            detail.putExtra(EXTRA_NAME_PROVIDER, presentation_prestations.getName_provider());
            detail.putExtra(EXTRA_ADD_DATE, presentation_prestations.getAdd_date());
            detail.putExtra(EXTRA,connect);
            startActivity(detail);
        }

    }
}