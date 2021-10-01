package com.orange.click_2_rent;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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

        contactezNous = findViewById(R.id.mBtnConctactez);
        myRecyclerContact = findViewById(R.id.myRecyclerProfil);
        contactezNousAdapter = new ContactezNousAdapter(myArrayList);
        myRecyclerContact.setLayoutManager(new LinearLayoutManager(this));
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