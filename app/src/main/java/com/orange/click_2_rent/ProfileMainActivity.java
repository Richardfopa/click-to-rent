package com.orange.click_2_rent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ProfileMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_profile);
        //création des données
        ArrayList<PersonProfile> personProfiles = new ArrayList<>();
        personProfiles.add(new PersonProfile(R.drawable.outline_edit_black_24,
                "Modifiez le profil"));
        personProfiles.add(new PersonProfile(R.drawable.outline_comment_black_24,
                "Commentaire"));
        personProfiles.add(new PersonProfile(R.drawable.outline_history_black_24,
                "Historique des prestations"));
        //réference le listview
        ListView listview = findViewById(R.id.profile_listview);
        //on crée l'adaptateur
        ProfileAdapter mAdapter = new ProfileAdapter(this,R.layout.profile_item,
                personProfiles);
        listview.setAdapter(mAdapter);


        
    }
}