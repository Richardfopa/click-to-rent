package com.orange.click_2_rent;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class PresentationPrestationAdapter extends RecyclerView.Adapter<PresentationPrestationAdapter.PresentationPrestationViewHolder> implements Filterable  {

    private List<Presentation_prestations> maListe;
    private List<Presentation_prestations> listeALL;
    private Context context;


    public PresentationPrestationAdapter(List<Presentation_prestations> MaListe,Context context) {

        this.maListe = MaListe;
        this.listeALL = new ArrayList<>(MaListe);
        this.context = context;

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

        holder.mTitredescription.setText(liste_prestations.getTitre_prestation());
        holder.mMinidescription.setText(liste_prestations.getMiniDescription());
        holder.mDateDescription.setText(String.valueOf(liste_prestations.getDate_prestation()));

        Picasso.with(holder.mImgProfil.getContext()).load(liste_prestations.getPhoto()).into(holder.mImgProfil);

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

        //Lancer le thread au niveau en arriere plan.

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Presentation_prestations> filteredList = new ArrayList<>();

            try {
                if(constraint.toString().isEmpty()) {

                    filteredList.addAll(listeALL);
                }else{
                    for (Presentation_prestations presence :listeALL)
                        if ((presence.getTitre_prestation().toLowerCase().contains(constraint.toString().toLowerCase())) || (presence.getMiniDescription().toLowerCase().contains(constraint.toString().toLowerCase())))
                            filteredList.add(presence);

                }
            }catch (Exception ex){

                ex.getMessage();
                ex.printStackTrace();
            }

            FilterResults filterResults  = new FilterResults();

            filterResults.values = filteredList;
            filterResults.count = filteredList.size();
            return filterResults;
        }
        //Executer le thread sur l'affichage
        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {

            maListe.clear();
            maListe.addAll((Collection <? extends Presentation_prestations>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class PresentationPrestationViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mImgProfil;
        public final TextView mTitredescription;
        public final TextView mMinidescription;
        public final TextView mDateDescription;
        public final RelativeLayout mRelativeLayout;

        public PresentationPrestationViewHolder(@NonNull View itemView) {
            super(itemView);

            mImgProfil = itemView.findViewById(R.id.mProfil);
            mTitredescription = itemView.findViewById(R.id.mDescription);
            mMinidescription = itemView.findViewById(R.id.miniDescription);
            mDateDescription = itemView.findViewById(R.id.mDate);
            mRelativeLayout = itemView.findViewById(R.id.mCardVue);

            mRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, ContactezNousActivity.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                    context.startActivity(intent);
                }
            });

        }
    }
}