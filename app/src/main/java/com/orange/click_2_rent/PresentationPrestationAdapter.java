package com.orange.click_2_rent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class PresentationPrestationAdapter extends RecyclerView.Adapter<PresentationPrestationAdapter.PresentationPrestationViewHolder> {

    ArrayList<Presentation_prestations> maListe;

    public PresentationPrestationAdapter(ArrayList<Presentation_prestations> maListe) {

        this.maListe = maListe;
    }

    @NonNull
    @Override
    public PresentationPrestationAdapter.PresentationPrestationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View maVue = LayoutInflater.from(parent.getContext()).inflate(R.layout.nos_prestations_affichage,parent,false);

        return new PresentationPrestationViewHolder(maVue);
    }

    @Override
    public void onBindViewHolder(@NonNull PresentationPrestationAdapter.PresentationPrestationViewHolder holder, int position) {

        final Presentation_prestations liste_prestations = this.maListe.get(position);

        holder.mImgProfil.setImageResource(liste_prestations.getImage_profil_prestation());
        holder.mTitredescription.setText(liste_prestations.getTitre_prestation());
        holder.mMinidescription.setText(liste_prestations.getMiniDescription());
        holder.mDateDescription.setText(liste_prestations.getDate_prestation());

        holder.mCadreVue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    @Override
    public int getItemCount() {

        return maListe.size();
    }

    public class PresentationPrestationViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mImgProfil;
        public final TextView mTitredescription;
        public final TextView mMinidescription;
        public final TextView mDateDescription;
        public final CardView mCadreVue;

        public PresentationPrestationViewHolder(@NonNull View itemView) {
            super(itemView);

            mImgProfil = itemView.findViewById(R.id.mProfil);
            mTitredescription = itemView.findViewById(R.id.mDescription);
            mMinidescription = itemView.findViewById(R.id.miniDescription);
            mDateDescription = itemView.findViewById(R.id.mDate);
            mCadreVue = itemView.findViewById(R.id.monCadre);

        }
    }
}
