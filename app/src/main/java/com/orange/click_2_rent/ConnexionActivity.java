package com.orange.click_2_rent;



import static com.orange.click_2_rent.EditProfileActivity.SENTUSERS;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.orange.click_2_rent.Models.FirebasesUtil;
import com.orange.click_2_rent.Models.Users;

public class ConnexionActivity extends AppCompatActivity {

    public static final String SENTUSERS_FORMAUTH = "sentuserfromauthemail";
    private FirebaseAuth mAuth;
    private TextView forget_password;
    public static final String EXTRA_CONNE = "connex";
    public static final String EXTRA_CONNE1 = "connex1";
    public static final String EXTRA_CONNE2 = "connex2";
    public static final String EXTRA_PHOTO = "PHOTO";
    public static final String EXTRA_DATA = "data";
    public static final int RESQUEST_DATA = 12345690;
    private TextInputLayout password;
    private TextInputLayout email;
    private Button login;
    private String carl;
    private Users muser;
    private FirebaseUser mUser;
    FirebaseFirestore db;
    String tof;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        muser = new Users();
        password = findViewById(R.id.edittextpassword_con);
        email = findViewById(R.id.edittextusername_con);
        login = findViewById(R.id.btn_con_login);
        forget_password = findViewById(R.id.forget_pass);
        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passe = new Intent(getApplicationContext(),Activity2.class);
                startActivity(passe);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getEditText().getText().toString();
                String txt_pass = password.getEditText().getText().toString();

                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_pass))
                {
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tout les champs", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    mAuth.signInWithEmailAndPassword(txt_email,txt_pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {

                                        final DocumentReference docUser = FirebasesUtil.getUsers(mUser.getUid());

                                        docUser.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                            @Override
                                            public void onEvent(@androidx.annotation.Nullable DocumentSnapshot snapshot, @androidx.annotation.Nullable FirebaseFirestoreException e) {

                                                if (snapshot != null && snapshot.exists()) {

                                                    muser.setTelphone(snapshot.getString("telphone"));
                                                    muser.setEmail(snapshot.getString("email"));
                                                    muser.setNom(snapshot.getString("nom"));
                                                    muser.setPhoto_user(snapshot.getString("photo_user"));
                                                    muser.setDate_darriver(snapshot.getTimestamp("date_darriver"));
                                                    muser.setAdresse(snapshot.getString("adresse"));
                                                    Log.d(DemarrageApp.TAG, "onEvent: " + snapshot.getData().toString());
                                                    Intent iput = new Intent(ConnexionActivity.this, ProfileMainActivity.class);
                                                    iput.putExtra("nom",muser.getNom());
                                                    iput.putExtra("email",muser.getEmail());
                                                    iput.putExtra("photo_user",muser.getPhoto_user());
                                                    iput.putExtra("adresse",muser.getAdresse());
                                                    iput.putExtra("date_darriver",muser.getDate_darriver());
                                                    startActivity(iput);
                                                }else{
                                                    startActivity(new Intent(ConnexionActivity.this,ConnexionActivity.class));
                                                }
                                            }
                                        });

                                        Intent intent1 = new Intent(getApplicationContext(), ProfileMainActivity.class);
                                        intent1.putExtra("email",txt_email);
                                        muser.setId(task.getResult().getUser().getUid());
                                        muser.setEmail(txt_email);
                                        muser.setMotDePasse(txt_pass);
                                        intent1.putExtra(SENTUSERS_FORMAUTH,muser);
                                        intent1.putExtra("password",txt_pass);
                                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent1);

                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "Connexion echouer: Verifier votre votre connexion", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESQUEST_DATA)
        {
            carl = data.getStringExtra(CompteActivity.REQUEST_SELECT);
        }
    }

    public void fct_envoie(View view) {
        Intent intent = new Intent(getApplicationContext(),CompteActivity.class);
        startActivity(intent);
    }
}