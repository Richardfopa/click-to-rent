package com.orange.click_2_rent;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
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
import java.util.List;


public class PresentationPrestationAdapter extends RecyclerView.Adapter<PresentationPrestationAdapter.PresentationPrestationViewHolder> implements Filterable  {

    private List<Presentation_prestations> maListe = new ArrayList<>();
    private List<Presentation_prestations> listeALL =  new ArrayList<>();
    private Context context;


    public PresentationPrestationAdapter(List<Presentation_prestations> MaListe,Context context) {

        Log.d("Ma liste", "PresentationPrestationAdapter: "+maListe);
        this.maListe = MaListe;
        this.listeALL = MaListe;
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

        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                Log.d("contenu", "performFiltering: "+constraint+"======"+listeALL);

                FilterResults filterResults  = new FilterResults();

                if(constraint == null || constraint.length() == 0 ) {

                    filterResults.values = listeALL;
                    filterResults.count = listeALL.size();

                }else{

                    String searchMax = constraint.toString().toLowerCase();
                    List<Presentation_prestations> prestations = new ArrayList<>();

                    for (Presentation_prestations presence :listeALL){

                        if(presence.getMiniDescription().toLowerCase().contains(searchMax) || presence.getTitre_prestation().toLowerCase().contains(searchMax))

                         {
                            prestations.add(presence);
                         }
                    }
                    filterResults.values = prestations;
                    filterResults.count = prestations.size();
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {

                maListe = filterResults.values == null ? new ArrayList<>() : ((ArrayList<Presentation_prestations>) filterResults.values);
                notifyDataSetChanged();
            }
        };

        return filter;
    }




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
