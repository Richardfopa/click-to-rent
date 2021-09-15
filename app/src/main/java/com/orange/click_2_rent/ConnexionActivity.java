package com.orange.click_2_rent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ConnexionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        ImageView fab = findViewById(R.id.img_view_google);
        Button emailbtn = findViewById(R.id.btn_con_valider);

        emailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(view.getContext(),ConnexionmdpActivity.class);
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Connexion par google", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ImageView fab_face = findViewById(R.id.img_view_facebook);
        fab_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Connexion par facebook", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}