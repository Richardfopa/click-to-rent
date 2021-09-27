package com.orange.click_2_rent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;


public class PresentationPrestationAdapter extends RecyclerView.Adapter<PresentationPrestationAdapter.PresentationPrestationViewHolder> implements Filterable {

    ArrayList<Presentation_prestations> maListe;
    ArrayList<String> listeALL;
    // context = PresentationPrestationViewHolder.this;
    private Context context;

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
    public void onBindViewHolder(@NonNull PresentationPrestationViewHolder holder, int position) {

        final Presentation_prestations liste_prestations = this.maListe.get(position);
        //holder.mImgProfil.setImageResource(liste_prestations.getImage_profil_prestation());
        holder.mTitredescription.setText(liste_prestations.getTitre_prestation());
        holder.mMinidescription.setText(liste_prestations.getMiniDescription());
        holder.mDateDescription.setText(String.valueOf(liste_prestations.getDate_prestation()));
       //Picasso.get().load(liste_prestations.getImage_profil_prestation()).into(holder.mImgProfil);
        Picasso.with(holder.mImgProfil.getContext()).load(liste_prestations.getPhoto()).into(holder.mImgProfil);
       // Glide.with(holder.mImgProfil.getContext()).load(liste_prestations.getPhoto()).into(holder.mImgProfil);



    }

    @Override
    public int getItemCount() {

        return maListe.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }


    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> findAll = new ArrayList<>();
            for (String movie : listeALL)
            {
                if(movie.toLowerCase().contains(constraint.toString().toLowerCase()))
                {
                    findAll.add(movie);
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = findAll;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            listeALL.clear();
            listeALL.addAll((Collection<? extends String>) filterResults.values);
            notifyDataSetChanged();
        }
    };


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
