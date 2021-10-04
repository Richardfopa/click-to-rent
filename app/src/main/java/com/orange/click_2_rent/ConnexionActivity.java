package com.orange.click_2_rent;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ConnexionActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputLayout password;
    private TextInputLayout email;
    private Button login;
    private TextView forget_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
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
                                        Intent intent1 = new Intent(getApplicationContext(),AjoutServiceActivity.class);
                                        intent1.addFlags(intent1.FLAG_ACTIVITY_CLEAR_TASK | intent1.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent1);

                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "Connexion echouer", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    public void fct_envoie(View view) {
        Intent intent = new Intent(getApplicationContext(),CompteActivity.class);
        startActivity(intent);
    }
}