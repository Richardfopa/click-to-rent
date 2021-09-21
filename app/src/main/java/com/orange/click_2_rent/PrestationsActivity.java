package com.orange.click_2_rent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class PrestationsActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private ArrayList<Presentation_prestations> maListe;
    private PresentationPrestationAdapter maPresentationAdapteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nos_prestations);

        mRecycler = findViewById(R.id.idNosPrestations);

        maPresentationAdapteur = new PresentationPrestationAdapter(maListe);
        mRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecycler.setHasFixedSize(true);
        mRecycler.setAdapter(new PresentationPrestationAdapter(initData()));
    }
    private ArrayList<Presentation_prestations>  initData(){

        maListe = new ArrayList<>();
        maListe.add(new Presentation_prestations(R.drawable.techniciens,"Developpeurs JAKARTA/JAKARTA","Je suis developpeur JAVA depuis 2012 et j'ai 3 logiciels realiser a mon actif","18/12/2021"));
        maListe.add(new Presentation_prestations(R.drawable.techniciens,"Developpeurs JAKARTA/JAKARTA","Je suis developpeur JAVA depuis 2012 et j'ai 3 logiciels realiser a mon actif","01/06/2021"));
        maListe.add(new Presentation_prestations(R.drawable.techniciens,"Developpeurs JAKARTA/JAKARTA","Je suis developpeur JAVA depuis 2012 et j'ai 3 logiciels realiser a mon actif","02/03/2021"));
        maListe.add(new Presentation_prestations(R.drawable.techniciens,"Developpeurs JAKARTA/JAKARTA","Je suis developpeur JAVA depuis 2012 et j'ai 3 logiciels realiser a mon actif","12/05/2021"));
        maListe.add(new Presentation_prestations(R.drawable.techniciens,"Developpeurs JAKARTA/JAKARTA","Je suis developpeur JAVA depuis 2012 et j'ai 3 logiciels realiser a mon actif","06/02/2021"));
        maListe.add(new Presentation_prestations(R.drawable.techniciens,"Developpeurs JAKARTA/JAKARTA","Je suis developpeur JAVA depuis 2012 et j'ai 3 logiciels realiser a mon actif","10/02/2021"));
        maListe.add(new Presentation_prestations(R.drawable.techniciens,"Developpeurs JAKARTA/JAKARTA","Je suis developpeur JAVA depuis 2012 et j'ai 3 logiciels realiser a mon actif","10/07/2021"));

        return maListe;
    }
}
