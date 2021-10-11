package com.orange.click_2_rent;

import static com.firebase.ui.auth.AuthUI.TAG;
import static com.orange.click_2_rent.PrestationsActivity.EXTRA;
import static com.orange.click_2_rent.PrestationsActivity.EXTRA_DESCRIPTION;
import static com.orange.click_2_rent.PrestationsActivity.EXTRA_IMAGE;
import static com.orange.click_2_rent.PrestationsActivity.EXTRA_NOM;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.orange.click_2_rent.Models.FirebasesUtil;
import com.orange.click_2_rent.Models.Service;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactezNousActivity extends AppCompatActivity {

    private RecyclerView myRecyclerContact;
    private ArrayList<ContactProfil> myArrayList;
    private ContactezNousAdapter contactezNousAdapter;
    private Button contactezNous;
    private Dialog mDialog;
    private Dialog mDialog1;
    private FloatingActionButton floatingActionButton;
    private Button btnAnnul;
    private Button btnValid;
    private EditText ed_com;
    private ArrayList<Float> allRating;
    private FirebaseFirestore db;
    private ArrayList<ContactProfil> maListe;
    private float value;
    private FirebaseUser currentUser;
    public static final int RESQUEST = 123;
    public static final int RESQUEST1 = 13;
    public String email;
    public String nomClient;
    public String ImageClient;
    private String commentaire;
    private float note;
    public static final String EXTRA_CONNEXION = "connexion";
    private EditText editText;
    private RatingBar ratingBar;
    private EditText ed_commmentaire;
    private String comment;
    private String Rating;
    private String email_prestataire;
    private String nom_prestataire;
    private String photo_prestataire;
    private TextView nomP;
    private CircleImageView imgProfilPrest;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Service service;

    private CardView card_view_profil;
    private LinearLayout linearLayout;


    @SuppressLint({"WrongViewCast", "RestrictedApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactez_nous);
        service = new Service();

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        imgProfilPrest = findViewById(R.id.profilPrest);
        nomP = findViewById(R.id.monPrest);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        card_view_profil = findViewById(R.id.card_view_profil);
        linearLayout = findViewById(R.id.linear_profil);



        Intent gservice = getIntent();

        service.setTitle(gservice.getStringExtra(EXTRA_NOM));
        service.setId(gservice.getStringExtra(PrestationsActivity.EXTRA_ID_SERVICE));
        service.setTelephone(gservice.getStringExtra(PrestationsActivity.EXTRA_TELEPHONE));
        service.setPhoto_service(gservice.getStringExtra(PrestationsActivity.EXTRA_PHOTO_SERVICE));


        if(savedInstanceState !=null)
        {
            nomClient = savedInstanceState.getString("client");
            ImageClient = savedInstanceState.getString("phoCli");
            comment = savedInstanceState.getString("avis_cli");
            email = savedInstanceState.getString("mail");



            Map<String, Object> data = new HashMap<>();
            data.put("avis", comment);
            data.put("nomCli",nomClient);
            data.put("photoCli",ImageClient);
            db.collection("opinion")
                    .add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });


        }

        Intent intent = getIntent();
        String image = intent.getStringExtra(EXTRA_IMAGE);
        String nom = intent.getStringExtra(EXTRA_NOM);
        String description = intent.getStringExtra(EXTRA_DESCRIPTION);
        ImageView image1 = findViewById(R.id.image1);
        ImageView image2 = findViewById(R.id.image2);
        ImageView profil = findViewById(R.id.profilPrest);
        ImageView image3 = findViewById(R.id.image3);
        TextView tilte = findViewById(R.id.monTitre);
        TextView descption = findViewById(R.id.DescPrestation);
        Picasso.with(this).load(image).into(image1);
        Picasso.with(this).load(image).into(image2);
        Picasso.with(this).load(image).into(image3);
        Picasso.with(this).load(image).into(profil);
        tilte.setText(nom);
        descption.setText(description);

        final DocumentReference docUser = FirebasesUtil.getUsers(mUser.getUid());
        docUser.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null && value.exists()){
                    floatingActionButton.setVisibility(View.VISIBLE);

                }else {

                    Intent connexion = new Intent(getApplicationContext(), ConnexionActivity.class);
                    connexion.putExtra(EXTRA_CONNEXION, "connecter");
                    startActivityForResult(connexion, RESQUEST);
                    floatingActionButton.setVisibility(View.INVISIBLE);

                }
            }
        });


        db = FirebaseFirestore.getInstance();
        db.collection("services")
                .whereEqualTo("nom_prestation", nom)
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

                                    email_prestataire = dc.getDocument().getString("title");

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

        db.collection("users")
                .whereEqualTo("email", email_prestataire)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots,
                                        @Nullable FirebaseFirestoreException e) {
                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            switch (dc.getType()) {
                                case ADDED:
                                    Log.d(TAG, "New city: " + dc.getDocument().getData());
                                    nom_prestataire = dc.getDocument().getString("nom");
                                    photo_prestataire = dc.getDocument().getString("photo_user");

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

        nomP.setText(nom_prestataire);
        Picasso.with(this).load(photo_prestataire).into(imgProfilPrest);

        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.contact_dialog);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        mDialog1 = new Dialog(this);
        mDialog1.setContentView(R.layout.notes_commentaire_layout);
        mDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        floatingActionButton = findViewById(R.id.boutonFlottant2);
        contactezNous = findViewById(R.id.mBtnConctactez);
        myRecyclerContact = findViewById(R.id.myRecyclerProfil);
        floatingActionButton.setVisibility(View.INVISIBLE);

//        Log.d("carlos",currentUser.getEmail()+" "+currentUser.getDisplayName()+" "+currentUser.getUid());
        //floatingActionButton.setVisibility(View.INVISIBLE);
        //test();

        contactezNous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = "connect";

                final DocumentReference docUser = FirebasesUtil.getUsers(mUser.getUid());
                docUser.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (value != null && value.exists()){
                            floatingActionButton.setVisibility(View.VISIBLE);

                            AlertDialog.Builder builder = new AlertDialog.Builder(ContactezNousActivity.this);
                            // Get the layout inflater
                            LayoutInflater inflater = ContactezNousActivity.this.getLayoutInflater();
                            Log.d("Click", "click sur le btn floatant");

                            // Inflate and set the layout for the dialog
                            // Pass null as the parent view because its going in the dialog layout
                            View formView = inflater.inflate(R.layout.contact_dialog, null);
                            TextView tv_name = formView.findViewById(R.id.NomProfil);
                            TextView tv_email = formView.findViewById(R.id.emailProfil);
                            TextView btn_call = formView.findViewById(R.id.btnCalls);
                            TextView btn_sms = formView.findViewById(R.id.btnSms);
                            CircleImageView img_profil = formView.findViewById(R.id.detailsContact);

                            tv_name.setText(service.getTitle());
                            tv_email.setText(value.getString("email"));
                            Picasso.with(getApplicationContext())
                                    .load(service.getPhoto_service())
                                    .centerCrop()
                                    .fit()
                                    .into(img_profil);

                            btn_call.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Uri uri = Uri.parse("tel:"+service.getTelephone());
                                    Intent it = new Intent(Intent.ACTION_DIAL, uri);
                                    startActivity(it);

                                }
                            });

                            builder.setView(formView)
                                    .show();
                        }else {

                            Intent connexion = new Intent(getApplicationContext(), ConnexionActivity.class);
                            connexion.putExtra(EXTRA_CONNEXION, "connecter");
                            startActivityForResult(connexion, RESQUEST);
                            floatingActionButton.setVisibility(View.VISIBLE);

                        }
                    }
                });

                /*if (value.equals("connect") && email == null) {
                    Intent connexion = new Intent(getApplicationContext(), ConnexionActivity.class);
                    connexion.putExtra(EXTRA_CONNEXION, "connecter");
                    startActivityForResult(connexion, RESQUEST);
                    floatingActionButton.setVisibility(View.VISIBLE);

                } else if (value.equals("connect") && email != null) {
                    floatingActionButton.setVisibility(View.VISIBLE);
                    TextView tv_name = findViewById(R.id.NomProfil);
                    TextView tv_email = findViewById(R.id.emailProfil);
                    TextView btn_call = findViewById(R.id.btnCalls);
                    TextView btn_sms = findViewById(R.id.btnSms);
                    CircleImageView img_profil = findViewById(R.id.detailsContact);
                    mDialog.show();

                }*/

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DocumentReference docUser = FirebasesUtil.getUsers(mUser.getUid());
                docUser.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (value != null && value.exists()){

                            AlertDialog.Builder builder = new AlertDialog.Builder(ContactezNousActivity.this);
                            // Get the layout inflater
                            LayoutInflater inflater = ContactezNousActivity.this.getLayoutInflater();
                            Log.d("Click", "click sur le btn floatant");

                            // Inflate and set the layout for the dialog
                            // Pass null as the parent view because its going in the dialog layout
                            View formView = inflater.inflate(R.layout.notes_commentaire_layout, null);
                            ed_commmentaire = formView.findViewById(R.id.textCommentaire);
                            RatingBar ratingBar = formView.findViewById(R.id.myRatingBar);

                            builder.setView(formView)
                                    //
                                    .setTitle("Ajouter un commentaire")
                                    // Add action buttons
                                    .setPositiveButton("Envoyer ", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int id) {

                                            comment = ed_commmentaire.getText().toString();
                                            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                                                @Override
                                                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                                                    float note = ratingBar.getRating();
                                                }
                                            });
                                            note = ratingBar.getRating();

                                            Rating = Float.toString(note);
                                            Log.d("note",Rating);
                                            Log.d("car", comment);
                                            Log.d("car", Rating);
                                            allRating.add(note);
                                            Map<String, Object> data = new HashMap<>();
                                            data.put("avis", comment);
                                            data.put("note", Rating);
                                            data.put("nomCli",nomClient);
                                            data.put("photoCli",ImageClient);
                                            db.collection("opinion")
                                                    .add(data)
                                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                        @SuppressLint("RestrictedApi")
                                                        @Override
                                                        public void onSuccess(DocumentReference documentReference) {
                                                            Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());

                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @SuppressLint("RestrictedApi")
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.w(TAG, "Error adding document", e);
                                                        }
                                                    });
                                        }
                                    })
                                    .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            ed_commmentaire.setText("");
                                            ratingBar.setRating(0);

                                        }
                                    }).show();
                        }else {

                            Intent connexion = new Intent(getApplicationContext(), ConnexionActivity.class);
                            connexion.putExtra(EXTRA_CONNEXION, "connecter");
                            startActivityForResult(connexion, RESQUEST);
                            floatingActionButton.setVisibility(View.VISIBLE);

                        }
                    }
                });

                /*
                AlertDialog.Builder builder = new AlertDialog.Builder(ContactezNousActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = ContactezNousActivity.this.getLayoutInflater();
                Log.d("Click", "click sur le btn floatant");

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                View formView = inflater.inflate(R.layout.notes_commentaire_layout, null);
                ed_commmentaire = formView.findViewById(R.id.textCommentaire);
                RatingBar ratingBar = formView.findViewById(R.id.myRatingBar);

                builder.setView(formView)
                        //
                        .setTitle("Ajouter un commentaire")
                        // Add action buttons
                        .setPositiveButton("Envoyer ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                comment = ed_commmentaire.getText().toString();
                                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                                    @Override
                                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                                        float note = ratingBar.getRating();
                                    }
                                });
                                note = ratingBar.getRating();

                                Rating = Float.toString(note);
                                Log.d("note",Rating);
                                Log.d("car", comment);
                                Log.d("car", Rating);
                                allRating.add(note);
                                Map<String, Object> data = new HashMap<>();
                                data.put("avis", comment);
                                data.put("note", Rating);
                                data.put("nomCli",nomClient);
                                data.put("photoCli",ImageClient);
                                db.collection("opinion")
                                        .add(data)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @SuppressLint("RestrictedApi")
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @SuppressLint("RestrictedApi")
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w(TAG, "Error adding document", e);
                                            }
                                        });
                            }
                        })
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ed_commmentaire.setText("");
                                ratingBar.setRating(0);

                            }
                        }).show();
                */
            }
        });


        allRating = new ArrayList<Float>();
        maListe = new ArrayList();
        db = FirebaseFirestore.getInstance();

        db.collection("opinion")
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

                                    ContactProfil model = new ContactProfil(
                                            dc.getDocument().getString("nomCli"),
                                            dc.getDocument().getString("avis"),
                                            dc.getDocument().getString("photoCli"));

                                    String valuer = dc.getDocument().getString("note");
                                    Log.d("carloNote",valuer);
                                    assert valuer != null;
                                    float entre = Float.parseFloat(valuer);

                                    allRating.add(entre);

                                    maListe.add(model);
                                    myRecyclerContact = findViewById(R.id.myRecyclerProfil);
                                    contactezNousAdapter = new ContactezNousAdapter(maListe, getApplicationContext());
                                    myRecyclerContact.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                                    myRecyclerContact.setHasFixedSize(true);
                                    myRecyclerContact.setAdapter(contactezNousAdapter);

                                    break;
                                case MODIFIED:
                                    Log.d(TAG, "Modified city: " + dc.getDocument().getData());
                                    break;
                                case REMOVED:
                                    Log.d(TAG, "Removed city: " + dc.getDocument().getData());
                                    break;
                            }

                        }

                        int ratingCount = allRating.size();
                        String a = Integer.toString(ratingCount);
                        Log.d("elt",a);
                        float ratingSum = 0.0F;
                        for (Float r : allRating) {
                            ratingSum = ratingSum + r;
                        }
                        float moneyRating = ratingSum / ratingCount;
                        TextView note = findViewById(R.id.noteRating);
                        note.setText("note" + " " + moneyRating);
                        RatingBar ratingBarAll;
                        ratingBarAll = findViewById(R.id.rNote);
                        float ratingAll = (ratingBarAll.getNumStars() * moneyRating) / 5;
                        ratingBarAll.setRating(ratingAll);
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESQUEST) {
            try {
                email = data.getStringExtra(ConnexionActivity.EXTRA_CONNE);
                nomClient = data.getStringExtra(ConnexionActivity.EXTRA_CONNE2);
                ImageClient = data.getStringExtra(ConnexionActivity.EXTRA_CONNE1);
                Log.d("car", email);
            }catch (Exception e)
            {
                Toast.makeText(getApplicationContext(), "Veuillez vous authentifier", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("client",nomClient);
        outState.putString("phoCli",ImageClient);
        outState.putString("avis_cli",ed_commmentaire.getText().toString());
        outState.putString("mail",email);
    }
}

