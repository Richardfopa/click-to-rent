package com.orange.click_2_rent;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
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

import com.orange.click_2_rent.Models.Service;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


public class PresentationPrestationAdapter extends RecyclerView.Adapter<PresentationPrestationAdapter.PresentationPrestationViewHolder> implements Filterable  {

    private final List<Service> maListe;
    private final List<Service> listeALL;
    private final Context context;


    public PresentationPrestationAdapter(List<Service> MaListe,Context context) {

        this.maListe = MaListe;
        this.listeALL = new ArrayList<>();
        this.context = context;
        PresentationPrestationAdapter.this.notifyItemInserted(maListe.size());
    }

    @NonNull
    @Override
    public PresentationPrestationAdapter.PresentationPrestationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View maVue = LayoutInflater.from(parent.getContext()).inflate(R.layout.nos_prestations_affichage,parent,false);

        return new PresentationPrestationViewHolder(maVue);
    }

    @Override
    public void onBindViewHolder(@NonNull PresentationPrestationViewHolder holder, int position) {

        final Service liste_prestations = this.maListe.get(position);
        holder.mTitredescription.setText(liste_prestations.getTitle());
        holder.mMinidescription.setText(liste_prestations.getDescription());
//        liste_prestations.getAdd_date().toDate();
//        if(liste_prestations.getAdd_date() != null){
//            Date date = liste_prestations.getAdd_date().toDate();
//
//            holder.mDateDescription.setText(TextUtils.substring("Poster le "+date.toString(), 0, 30));
//            ;
//        }else{
//            holder.mDateDescription.setText(String.valueOf(liste_prestations.getAdd_date()));
//            holder.mTitredescription.setText(liste_prestations.getTitle());
//            holder.mMinidescription.setText(liste_prestations.getDescription());
//        }


        Picasso.with(holder.mImgProfil.getContext())
                .load(liste_prestations.getPhoto_service())
                .into(holder.mImgProfil);

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

            List<Service> filteredList = new ArrayList<>();

            try {
                if(constraint.toString().isEmpty()) {

                    filteredList.addAll(listeALL);
                }else{
                    for (Service presence :listeALL)
                        if ((presence.getTitle().toLowerCase().contains(constraint.toString().toLowerCase())) || (presence.getDescription().toLowerCase().contains(constraint.toString().toLowerCase())))
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
            maListe.addAll((Collection <? extends Service>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class PresentationPrestationViewHolder extends RecyclerView.ViewHolder {

        private static final String PRESTATIONS_LAYOUT_POSITION = "prestations_layout_position";
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

            mRelativeLayout.setOnClickListener(view -> {

                int position = getLayoutPosition();
                Service liste_prestations = maListe.get(position);
                Intent intent = new Intent(context, ContactezNousActivity.class);
                intent.putExtra(PRESTATIONS_LAYOUT_POSITION, liste_prestations);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                context.startActivity(intent);
            });

        }
    }
}