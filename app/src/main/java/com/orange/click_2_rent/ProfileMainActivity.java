package com.orange.click_2_rent;

import static com.orange.click_2_rent.DemarrageApp.TAG;
import static com.orange.click_2_rent.MainActivity.SENT_USERS_IT;
import static com.orange.click_2_rent.PhotoProfilActivity.url;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.orange.click_2_rent.Models.FirebasesUtil;
import com.orange.click_2_rent.Models.Users;
import com.squareup.picasso.Picasso;


public class ProfileMainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DECONNEXION = "deconnection_du_profile_main";
    private Users musers = new Users();
    private TextView tv_email;
    private ImageView murl;
    private TextView username;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Button mbtdeconnexion;
    private String photourl;
    private String email;
    private String nom;
    private String adresse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gestion_profile);
        //musers = new Users();
        Intent iuser = getIntent();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        updateUser(mUser);
        Toast.makeText(this,"votre Id "+mUser.getUid(),Toast.LENGTH_LONG).show();

        mbtdeconnexion = findViewById(R.id.deconnexion_button) ;

        mbtdeconnexion.setOnClickListener(this);

        Log.d(TAG, "onCreate: Profile main FireUser " +mUser.toString());


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        //Toast.makeText(this, "id " + mUser.getUid(), Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, "User id " + musers.toString(), Toast.LENGTH_SHORT).show();
        username = findViewById(R.id.profile_image_nom);

        Users users = getIntent().getParcelableExtra(SENT_USERS_IT);

        nom = getIntent().getStringExtra("nom");
        email = getIntent().getStringExtra("email");
        photourl = getIntent().getStringExtra("photo_user");
        adresse = getIntent().getStringExtra("adresse");

        Log.d(TAG, "onCreate: Profilmain " + musers.toString());
        Toast.makeText(this, "onCreate: Profilmain " + nom+" "+email+" "+photourl, Toast.LENGTH_SHORT).show();

        tv_email = findViewById(R.id.profile_image_email);
        murl = findViewById(R.id.profile_image);
        Uri uri = Uri.parse(photourl);

        if (musers != null){
            Picasso.with(this)
                    .load(uri)
                    .into(murl);
            username.setText(nom);
            tv_email.setText(email);
        }
        else{
            startActivity(new Intent(this,ConnexionActivity.class));
        }
        murl.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth != null){
            mUser = mAuth.getCurrentUser();
            updateUser(mUser);
        }
    }

    private void updateUser(FirebaseUser mUser) {

        if (mUser != null){
            final DocumentReference docUser = FirebasesUtil.getUsers(mUser.getUid());
            docUser.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {

                    if (snapshot != null && snapshot.exists()) {

                        //musers.setTelphone(snapshot.getString("telphone"));
                        musers.setEmail(snapshot.getString("email"));
                        musers.setNom(snapshot.getString("nom"));
                        musers.setPhoto_user(snapshot.getString("photo_user"));
                        musers.setDate_darriver(snapshot.getTimestamp("date_darriver"));
                        musers.setAdresse(snapshot.getString("adresse"));
                        Log.d(TAG, "onEvent: " + snapshot.getData().toString());
                        Log.d(TAG, "onEvent: musers " + musers.toString());
                        //Toast.makeText(ProfileMainActivity.this, musers.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void viewCommentList(View view) {
    }

    public void editProfile(View view) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }


    public void showServiceList(View view) {
        startActivity(new Intent(this, ListeServicesActivity.class));

    }

    public void showHistoriqueClient(View view) {
        Intent intent = new Intent(this, HistoryListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.deconnexion_button:
                Intent signout = new Intent(this, ConnexionActivity.class);
                FirebaseAuth.getInstance().signOut();
                signout.putExtra(DECONNEXION,getString(R.string.revenez));
                startActivity(signout);
                finish();
                break;

            case R.id.profile_image:
                Intent gotop = new Intent(this, PhotoProfilActivity.class);
                gotop.putExtra(url,photourl);
                gotop.putExtra("nom",nom);
                gotop.putExtra("email",email);
                gotop.putExtra("photo_user",photourl);
                startActivity(gotop);
                break;

        }

    }
}