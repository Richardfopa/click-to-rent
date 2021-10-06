package com.orange.click_2_rent;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileMainActivity extends AppCompatActivity {

    private TextView tv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        tv_email = findViewById(R.id.profile_image_title);

        tv_email.setText(currentUser.getEmail());
            setContentView(R.layout.gestion_profile);

    }


    public void viewCommentList(View view) {
    }

    public void editProfile(View view) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }


    public void showServiceList(View view) {

    }

    public void showHistoriqueClient(View view) {
        Intent intent = new Intent(this, HistoryListActivity.class);
        startActivity(intent);
    }
}