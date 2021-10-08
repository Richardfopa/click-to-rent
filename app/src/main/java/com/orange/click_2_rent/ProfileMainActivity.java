package com.orange.click_2_rent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.orange.click_2_rent.Models.FirebasesUtil;
import com.orange.click_2_rent.Models.Photo;
import com.orange.click_2_rent.Models.Users;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileMainActivity extends AppCompatActivity {
    private CircleImageView mImageProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.gestion_profile);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            //on récupere la dépot des photos
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://click-to-rent.appspot.com");
        //crée une réference de stockage
        StorageReference storageRef = storage.getReference();
        StorageReference imagesRef = storageRef.child("users");
        mImageProfile = findViewById(R.id.profile_image);
        Glide.with(this /* context */)
                .load(imagesRef)
                .into(mImageProfile);

    }


    public void viewCommentList(View view) {
    }

    public void editProfile(View view) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }


    public void showServiceList(View view) {
        startActivity(new Intent(this, ListeServicesActity.class));

    }

    public void showHistoriqueClient(View view) {
        Intent intent = new Intent(this, HistoryListActivity.class);
        startActivity(intent);
    }
}