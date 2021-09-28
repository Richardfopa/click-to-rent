package com.orange.click_2_rent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactezNousActivity extends AppCompatActivity {

    private RecyclerView myRecyclerContact;
    private ArrayList<ContactProfil> myArrayList;
    private ContactezNousAdapter contactezNousAdapter;
    private Button contactezNous;
    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactez_nous);
        mDialog =  new Dialog(this);
        mDialog.setContentView(R.layout.contact_dialog);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Intent receipprestataire = getIntent();
        Presentation_prestations prestataire = receipprestataire.getParcelableExtra(Presentation_prestations.PRESTATION_CLES);


        contactezNous = findViewById(R.id.mBtnConctactez);
        myRecyclerContact = findViewById(R.id.myRecyclerProfil);
        contactezNousAdapter = new ContactezNousAdapter(myArrayList);
        myRecyclerContact.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myRecyclerContact.setHasFixedSize(true);
        myRecyclerContact.setAdapter(new ContactezNousAdapter(initialisation()));

        contactezNous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView tv_name = findViewById(R.id.NomProfil);
                TextView tv_email = findViewById(R.id.emailProfil);
                TextView btn_call = findViewById(R.id.btnCalls);
                TextView btn_sms = findViewById(R.id.btnSms);
                CircleImageView img_profil = findViewById(R.id.detailsContact);
                mDialog.show();
            }
        });

        if (!prestataire.getIdPrestation().equals(null)){
            TextView titleprestation = findViewById(R.id.profiltitle);
            TextView titlename = findViewById(R.id.profilname);
            TextView titledescription = findViewById(R.id.explications);

            titleprestation.setText(prestataire.getTitre_prestation());
            // Recuperer le prestataire qui a pour service








        }


    }



    private ArrayList<ContactProfil> initialisation() {

        myArrayList = new ArrayList<>();

        myArrayList.add(new ContactProfil(R.drawable.plombier3,"Mr Kamga Orelien","Je suis tres content de  votre service"));
        myArrayList.add(new ContactProfil(R.drawable.plombier3,"Mr Kamga Orelien","Je suis tres content de  votre service"));
        myArrayList.add(new ContactProfil(R.drawable.plombier3,"Mr Kamga Orelien","Je suis tres content de  votre service"));
        myArrayList.add(new ContactProfil(R.drawable.plombier3,"Mr Kamga Orelien","Je suis tres content de  votre service"));
        myArrayList.add(new ContactProfil(R.drawable.plombier3,"Mr Kamga Orelien","Je suis tres content de  votre service"));
        myArrayList.add(new ContactProfil(R.drawable.plombier3,"Mr Kamga Orelien","Je suis tres content de  votre service"));

        return myArrayList;
    }

}