package com.orange.click_2_rent;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.orange.click_2_rent.Firebase.FireBaseUtils;

import java.util.ArrayList;
import java.util.LinkedList;


public class PresentationPrestationAdapter extends RecyclerView.Adapter<PresentationPrestationAdapter.PresentationPrestationViewHolder> {

    LinkedList<Presentation_prestations> maListe;

    public PresentationPrestationAdapter(Context context) {

        this.maListe = new LinkedList<>();

        FireBaseUtils.getReferenceFirestore(FireBaseUtils.TASK_COLLECTION)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null){
                            Log.w("error:",error);
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            switch (dc.getType()){
                                case ADDED:
                                    Log.d("ADD:",dc.getDocument().getData().toString());

//                                    Tache tache = dc.getDocument().toObject(Tache.class);
                                    Presentation_prestations prestations = new Presentation_prestations(
                                            dc.getDocument().getId(),
                                            dc.getDocument().getString("name_provider"),
                                            dc.getDocument().getString("description"),
                                            dc.getDocument().getTimestamp("add_date").toDate());
                                    maListe.add(prestations);
//                                    maListe.add(dc.getDocument().toObject(Presentation_prestations.class));
                                    PresentationPrestationAdapter.this.notifyItemInserted(0);
                                    break;
                                case MODIFIED:
                                    break;
                                case REMOVED:
                                    break;
                            }
                        }
                    }
                });

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

//        holder.mImgProfil.setImageResource(liste_prestations.getImage_profil_prestation());
        holder.mTitredescription.setText(liste_prestations.getTitre_prestation());
        holder.mMinidescription.setText(liste_prestations.getMiniDescription());
        holder.mDateDescription.setText(String.valueOf(liste_prestations.getDate_prestation()));

    }

    @Override
    public int getItemCount() {

        return maListe.size();
    }

    public class PresentationPrestationViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

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

            mCadreVue.setOnLongClickListener(this);

        }

        @Override
        public boolean onLongClick(View view) {

            switch(view.getId()){
                case R.id.monCadre:
                    Presentation_prestations prestataire = maListe.get(getLayoutPosition());
                    Intent service = new Intent(view.getContext(),ContactezNousActivity.class);
                    service.putExtra(Presentation_prestations.PRESTATION_CLES,prestataire);
                    break;
            }
            return false;
        }
    }
}
