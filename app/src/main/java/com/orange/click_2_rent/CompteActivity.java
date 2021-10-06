package com.orange.click_2_rent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.HashMap;
import java.util.regex.Pattern;

public class CompteActivity extends AppCompatActivity {

    private TextInputLayout username;
    private TextInputLayout password;
    private TextInputLayout email;
    private Button valider;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    private static final Pattern MOT_DE_PASSE_VALIDATION = Pattern.compile(

            "^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-zA-Z])" +
                    "(?=.*[@#$%^&+=])" +
                    "(?=\\S+$)" +
                    ".{6,}" +
                    "$"
    );

    private static final Pattern EMAIL_VALIDATION = Pattern.compile(

            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA0-9][a-zA-Z0-9\\-] {0,24}" +
                    ")"
    );

    private static final Pattern NAME_VALIDATION = Pattern.compile(

            "[a-zA-Z0-9]"

            );

    private static final Pattern NO_WHITE_SPACE = Pattern.compile(

            "(?=\\S+$ )"

            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte2);
        username = findViewById(R.id.edittextnom_con);
        password = findViewById(R.id.edittextpassword_con);
        email = findViewById(R.id.edittextusername_con);
        valider = findViewById(R.id.btn_con_valider);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        validationName();
        validationMotDePasse();

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String txt_user = username.getEditText().getText().toString();
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
                }*/
            }
        });
    }

    private void register(String username, String password, String email) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("name", username);
                            hashMap.put("email", email);
                            hashMap.put("password", password);


                            db.collection("users").get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                Intent intent = new Intent(getApplicationContext(), ConnexionActivity.class);
                                                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);


                                            }
                                        }
                                    });

                        }
                    }
                });
        UserRepository.addUser(password);

    }

    public boolean validationMotDePasse() {

        String txt_pass = password.getEditText().getText().toString().trim();

        if (txt_pass.isEmpty()) {

            password.setError("Le champs Mot de passe ne peut pas etre vide.\nVeuillez le remplir SVP");
            return false;

        }
        else if(!MOT_DE_PASSE_VALIDATION.matcher(txt_pass).matches()){

            password.setError("votre mot de passe ne correspond pas au format");
            return false;

        }else{

            password.setError(null);
            return true;
        }
    }
    public boolean validationEmail() {

        String txt_email = email.getEditText().getText().toString().trim();


     return false;

    }
    public boolean validationName() {

        String nom = username.getEditText().getText().toString().trim();

        if(nom.isEmpty()){

            username.setError("Ce champ ne peu pas etre vide, SVP veuillez renseigner votre nom");
            return false;
        }
        else if(!NAME_VALIDATION.matcher(nom).matches()){

            username.setError("Votre mot de passe ne respecte pas le formalisme");
        }
        else{

            username.setError(null);
        }

        return true;
    }

}

