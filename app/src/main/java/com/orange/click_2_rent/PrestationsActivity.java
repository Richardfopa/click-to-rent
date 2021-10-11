package com.orange.click_2_rent;

import static com.orange.click_2_rent.Models.FirebasesUtil.TAG;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.orange.click_2_rent.Models.FirebasesUtil;
import com.orange.click_2_rent.Models.Service;
import com.orange.click_2_rent.Models.Users;

import java.util.ArrayList;

public class PrestationsActivity extends AppCompatActivity implements PresentationPrestationAdapter.OnItemClickListener {


    public static final String EXTRA_TELEPHONE = "extra_telephone";
    public static final String EXTRA_NAME_PROVIDER = "extra_NAME_PROVIDER";
    public static final String EXTRA_PHOTO_SERVICE = "extra_photo_service";
    public static final String EXTRA_ID_SERVICE = "extra_id_service";
    public static final String EXTRA_ADD_DATE = "extra_add_date";
    private RecyclerView mRecycler;
    private final ArrayList<Service> maListe = new ArrayList<>();;
    private PresentationPrestationAdapter maPresentationAdapteur;
    private FirebaseFirestore db;
    public static final String EXTRA_IMAGE = "image";
    public static final String EXTRA_NOM = "nom";
    public static final String EXTRA_DESCRIPTION = "description";
    public static final String EXTRA = "extra";
    public String connect;
    public static final String EXTRA_COMPTE = "compte";
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Users user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nos_prestations);
        user = new Users();
        mRecycler = findViewById(R.id.idNosPrestations);

        FloatingActionButton  fabadd_service = findViewById(R.id.boutonFlottant);
        fabadd_service.hide();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        Toast.makeText(this,"votre Id "+mUser.getUid(),Toast.LENGTH_LONG).show();

        final DocumentReference docUser = FirebasesUtil.getUsers(mUser.getUid());

        docUser.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@androidx.annotation.Nullable DocumentSnapshot snapshot, @androidx.annotation.Nullable FirebaseFirestoreException e) {

                if (snapshot != null && snapshot.exists()) {

                    user.setTelphone(snapshot.getString("telphone"));
                    user.setEmail(snapshot.getString("email"));
                    user.setNom(snapshot.getString("nom"));
                    user.setPhoto_user(snapshot.getString("photo_user"));
                    user.setDate_darriver(snapshot.getTimestamp("date_darriver"));
                    user.setAdresse(snapshot.getString("adresse"));
                    fabadd_service.show();
                }else{

                }
            }
        });

        EventChangeListener();
    }

    public void EventChangeListener()
    {


        db = FirebaseFirestore.getInstance();
        db.collection(getString(R.string.services))
                .orderBy("add_date", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots,
                                        @Nullable FirebaseFirestoreException e) {
                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            switch (dc.getType()) {
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
                                    maListe.add(service);

                                    maPresentationAdapteur = new PresentationPrestationAdapter(maListe, getApplicationContext());
                                    mRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    mRecycler.setHasFixedSize(true);
                                    mRecycler.setAdapter(maPresentationAdapteur);
                                    maPresentationAdapteur.setOnItemClickListener(PrestationsActivity.this);
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

//        db = FirebaseFirestore.getInstance();
//        db.collection("services")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        for(DocumentSnapshot doc: task.getResult())
//                        {
//                            Service model = new Service();
//                            model.setName_provider(doc.getString("name_provider"));
//                            model.setDescription(doc.getString("description"));
//                            model.setAddDate(doc.getTimestamp("add_date"));
//                            model.setPhoto_service(doc.getString("photo_service"));
//
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

        final DocumentReference docUser = FirebasesUtil.getUsers(mUser.getUid());

        docUser.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@androidx.annotation.Nullable DocumentSnapshot snapshot, @androidx.annotation.Nullable FirebaseFirestoreException e) {

                if (snapshot != null && snapshot.exists()) {

                    user.setTelphone(snapshot.getString("telphone"));
                    user.setEmail(snapshot.getString("email"));
                    user.setNom(snapshot.getString("nom"));
                    user.setPhoto_user(snapshot.getString("photo_user"));
                    user.setDate_darriver(snapshot.getTimestamp("date_darriver"));
                    user.setAdresse(snapshot.getString("adresse"));
                    Log.d(DemarrageApp.TAG, "onEvent: " + snapshot.getData().toString());
                    Intent iput = new Intent(PrestationsActivity.this, AjoutServiceActivity.class);
                    iput.putExtra("nom",user.getNom());
                    iput.putExtra("email",user.getEmail());
                    iput.putExtra("photo_user",user.getPhoto_user());
                    iput.putExtra("adresse",user.getAdresse());
                    iput.putExtra("date_darriver",user.getDate_darriver());
                    startActivity(iput);
                }else{
                    startActivity(new Intent(PrestationsActivity.this,ConnexionActivity.class));
                }
            }
        });

        Intent intent = new Intent(getApplicationContext(),ProfileMainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onIntemClick(int position) {


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
        detail.putExtra(EXTRA_ID_SERVICE,presentation_prestations.getId());
        startActivity(detail);
    }
}
