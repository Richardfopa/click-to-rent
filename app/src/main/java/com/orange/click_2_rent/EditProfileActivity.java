package com.orange.click_2_rent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.orange.click_2_rent.Models.FirebasesUtil;

public class EditProfileActivity extends AppCompatActivity {

    private TextInputEditText name;
    private TextInputEditText email_address;
    private TextInputEditText phone_number;
    private TextInputEditText ville;
    private TextInputEditText adresse;
    private Button btn_modif;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        name = findViewById(R.id.name);
        email_address= findViewById(R.id.email_address);
        phone_number=findViewById(R.id.phone_number);
        ville=findViewById(R.id.ville);
        adresse=findViewById(R.id.adresse);
        btn_modif=findViewById(R.id.button_modif);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        name.setText(currentUser.getDisplayName());
        email_address.setText(currentUser.getEmail());

        FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
                .document(currentUser.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            phone_number.setText(String.valueOf(document.getLong("telephone")));
                            ville.setText(document.getString("ville"));
                            adresse.setText(document.getString("adresse"));
                        }
                    }
                });

        btn_modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().length() != 0){
                    UserRepository.updateNamePhoto(name.getText().toString());
                }
                if (email_address.getText().toString().length() !=0){
                    UserRepository.updateEmail(email_address.getText().toString());
                }
                if (phone_number.getText().toString().length() != 0){
                    UserRepository.updateTel(Integer.parseInt(phone_number.getText().toString()));
                }
                if (ville.getText().toString().length() != 0){
                    UserRepository.updateVille(ville.getText().toString());
                }
                if (adresse.getText().toString().length() != 0){
                    UserRepository.updateAdresse(adresse.getText().toString());
                }

                Toast.makeText(getApplicationContext(), "Modifications effectués avec succès", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), ProfileMainActivity.class);
                startActivity(i);
            }
        });
    }


}