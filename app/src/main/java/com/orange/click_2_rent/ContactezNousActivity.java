package com.orange.click_2_rent;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactezNousActivity extends AppCompatActivity {

    private RecyclerView myRecyclerContact;
    private ArrayList<ContactProfil> myArrayList;
    private ContactezNousAdapter contactezNousAdapter;
    private Button contactezNous;
    private Dialog mDialog;
    private Dialog mDialog1;
    private FloatingActionButton floatingActionButton;
    private Button btnAnnul;
    private Button btnValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactez_nous);

        mDialog =  new Dialog(this);
        mDialog.setContentView(R.layout.contact_dialog);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //
        mDialog1 = new Dialog(this);
        mDialog1.setContentView(R.layout.notes_commentaire_layout);
        mDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnAnnul = findViewById(R.id.btnAnnuler);
        btnValid = findViewById(R.id.btnValider);
        contactezNous = findViewById(R.id.mBtnConctactez);
        myRecyclerContact = findViewById(R.id.myRecyclerProfil);
        contactezNousAdapter = new ContactezNousAdapter(myArrayList);
        myRecyclerContact.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerContact.setHasFixedSize(true);
        floatingActionButton = findViewById(R.id.boutonFlottant2);
        myRecyclerContact.setAdapter(new ContactezNousAdapter(initialisation()));

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDialog1.show();
            }
        });

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
    }

    private List<ContactProfil> initialisation() {

        myArrayList = new ArrayList<>();

        myArrayList.add(new ContactProfil(R.drawable.elect,"Mr Kamga Orelien","Je suis tres content de  votre service"));
        myArrayList.add(new ContactProfil(R.drawable.elect,"Mr Kamga Orelien","Je suis tres content de  votre service"));
        myArrayList.add(new ContactProfil(R.drawable.elect,"Mr Kamga Orelien","Je suis tres content de  votre service"));
        myArrayList.add(new ContactProfil(R.drawable.elect,"Mr Kamga Orelien","Je suis tres content de  votre service"));
        myArrayList.add(new ContactProfil(R.drawable.elect,"Mr Kamga Orelien","Je suis tres content de  votre service"));
        myArrayList.add(new ContactProfil(R.drawable.elect,"Mr Kamga Orelien","Je suis tres content de  votre service"));

        return myArrayList;
    }
}