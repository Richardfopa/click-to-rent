package com.orange.click_2_rent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.orange.click_2_rent.Models.FirebasesUtil;
import com.orange.click_2_rent.Models.Users;

import java.util.Objects;

public class DemarrageApp extends AppCompatActivity {

    public static final String TAG = "debug";
    private FirebaseAuth mAuth;
    private FloatingActionButton btnStrat;
    public Users users;
    public static final String SENT_USERS = "users";
    public FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        users = new Users();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demarrage_app);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
        mAuth = FirebaseAuth.getInstance();

        btnStrat = findViewById(R.id.btStart);
        btnStrat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(DemarrageApp.this, "Veuiller patienter s'il vous plait ", Toast.LENGTH_SHORT).show();
                mAuth.signInAnonymously()
                        .addOnCompleteListener(DemarrageApp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInAnonymously:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(DemarrageApp.this, "Authentication success.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInAnonymously:failure", task.getException());
                                    Toast.makeText(DemarrageApp.this, "Authentication failed. verifier votre connexion",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth != null){
            // Check if user is signed in (non-null) and update UI accordingly.
            currentUser = mAuth.getCurrentUser();


            if(currentUser != null){
                Toast.makeText(this,"votre Id "+currentUser.getUid(),Toast.LENGTH_LONG).show();
                Log.d(TAG, "onStart: "+currentUser.toString());
                updateUI(currentUser);
            }
        }



    }

    void updateUI(FirebaseUser user){
        if (user != null){
            Toast.makeText(this,"User id"+user.getUid(),Toast.LENGTH_LONG).show();
            Log.d(TAG, "updateUI: "+user.getUid());
            /*
            Intent i = new Intent(DemarrageApp.this, MainActivity.class);


            i.putExtra(SENT_USERS,user);
            startActivity(i);
            finish();*/

            users.setId(user.getUid());
            Log.d(TAG, "updateUI: "+users.getId());

            final DocumentReference docUser = FirebasesUtil.getUsers(users.getId());

            docUser.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {

                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e);
                        return;
                    }

                    String source = snapshot != null && snapshot.getMetadata().hasPendingWrites()
                            ? "Local" : "Server";

                    if (snapshot != null && snapshot.exists()) {

                        Log.d(TAG, source + " data: " + snapshot.getData());
                        users.setTelphone(snapshot.getString("telphone"));
                        users.setEmail(snapshot.getString("email"));
                        users.setNom(snapshot.getString("nom"));
                        users.setDate_darriver(snapshot.getTimestamp("date_darriver"));
                        users.setAdresse(snapshot.getString("adresse"));
                        Log.d(TAG, "onEvent: "+snapshot.getData().toString());
                        Log.d(TAG, "onEvent: "+users.toString());

                    } else {
                        Log.d(TAG, source + " data: null");
                    }

                }
            });

            if (users != null){
                Intent i = new Intent(DemarrageApp.this, MainActivity.class);
                i.putExtra(SENT_USERS,users);
                startActivity(i);
                finish();
            }
        }
    }

}