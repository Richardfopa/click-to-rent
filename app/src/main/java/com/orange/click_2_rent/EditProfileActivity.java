package com.orange.click_2_rent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class EditProfileActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //création du menu pour le genre (sexe)
        String[] gender = new String[]{"Homme", "Femme"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.gender_item, gender);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.gender_list);
        textView.setAdapter(adapter);

        //création du menu pour le civilite
        String[] civilite = new String[]{"Mr.", "Mme"};
        ArrayAdapter<String> adapter_2 = new ArrayAdapter<>(this, R.layout.civilite_item, civilite);
        AutoCompleteTextView civilite_textview = (AutoCompleteTextView) findViewById(R.id.civilite_list);
        civilite_textview.setAdapter(adapter_2);
    }


}