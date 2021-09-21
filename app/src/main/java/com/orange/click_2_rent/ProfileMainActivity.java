package com.orange.click_2_rent;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orange.click_2_rent.Models.Client;


public class ProfileMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_profile);
        TextView email_title = findViewById(R.id.profile_image_title);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference colref = db.collection("users");
        DocumentReference docref = colref.document();


    }


    public void viewCommentList(View view) {
    }

    public void editProfile(View view) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }


    public void showServiceList(View view) {
        Intent profileIntent = new Intent(this, ListeServicesActivity.class);
        startActivity(profileIntent);
    }

    public void showHistoriqueClient(View view) {
        Intent intent = new Intent(this, HistoryListActivity.class);
        startActivity(intent);
    }
}