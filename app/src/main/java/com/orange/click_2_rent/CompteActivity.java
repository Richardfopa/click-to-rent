package com.orange.click_2_rent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class CompteActivity extends AppCompatActivity {
    private TextInputLayout username;
    private TextInputLayout password;
    private TextInputLayout email;
    private Button valider;
    private FirebaseAuth auth;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);
        username = findViewById(R.id.edittextnom_con);
        password = findViewById(R.id.edittextpassword_con);
        email = findViewById(R.id.edittextusername_con);
        valider = findViewById(R.id.btn_con_valider);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_user = username.getEditText().getText().toString();
                String txt_pass = password.getEditText().getText().toString();
                String txt_email = email.getEditText().getText().toString();
                if(TextUtils.isEmpty(txt_user) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_pass))
                {
                    Toast.makeText(getApplicationContext(),"Veuillez remplir tout les champs",Toast.LENGTH_LONG).show();
                }
                else if(txt_pass.length() < 6 )
                {
                    Toast.makeText(getApplicationContext(), "Mot passe doit etre au moins 6 caracteres", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    register(txt_user,txt_pass,txt_email);
                }
            }
        });


    }

    private void register(String username, String password, String email)
    {
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("name",username);
                            hashMap.put("email",email);
                            hashMap.put("password",password);


                            db.collection("users").get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if(task.isSuccessful())
                                            {
                                                Intent intent = new Intent(getApplicationContext(), ConnexionActivity.class);
                                                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);


                                            }
                                        }
                                    });

                        }
                    }
                });
    }
}