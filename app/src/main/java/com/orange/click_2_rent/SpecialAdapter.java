package com.orange.click_2_rent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.SpecialViewHolder>{

    List<Specialite> mesSpecialites;

    public SpecialAdapter(List<Specialite> mesSpecialites) {

        this.mesSpecialites = mesSpecialites;
    }

    @NonNull
    @Override
    public SpecialAdapter.SpecialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.specialite_menu,parent,false);

        return new SpecialViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialAdapter.SpecialViewHolder holder, int position) {

        Specialite sp = this.mesSpecialites.get(position);
        holder.myImagePP.setImageResource(sp.getImageDesc());
        holder.myDescription.setText(sp.getNomDesc());

        holder.myCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {

        return mesSpecialites.size();
    }

    public class SpecialViewHolder extends RecyclerView.ViewHolder{

        public final CardView myCard;
        public final TextView myDescription;
        public final ImageView myImagePP;

        public SpecialViewHolder(@NonNull View itemView) {
            super(itemView);

            myCard = itemView.findViewById(R.id.monCadre);
            myDescription = itemView.findViewById(R.id.monTitre);
            myImagePP = itemView.findViewById(R.id.monImageProfil);
        }
    }
}
