package com.orange.click_2_rent;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

class ContactezNousAdapter extends RecyclerView.Adapter<ContactezNousAdapter.ContactezNousViewHolder> {

    List<ContactProfil> myList;
    Context context;

    public ContactezNousAdapter(List<ContactProfil> myList, Context context) {

        this.myList = myList;
        this.context= context;
    }

    @NonNull
    @Override
    public ContactezNousViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vues = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactez_items,parent,false);

        ContactezNousViewHolder viewHlder = new ContactezNousViewHolder(vues);

        viewHlder.contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(parent.getContext(), "Vous avez clique sur le "+String.valueOf(viewHlder.getAdapterPosition()+" element de la liste"), Toast.LENGTH_SHORT).show();
            }
        });
        return viewHlder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactezNousViewHolder holder, int position) {

        final ContactProfil contactez = this.myList.get(position);

        holder.descProfil.setText(contactez.getDescriptionProfil());
        holder.nomProfil.setText(contactez.getMomProfil());
      //  Log.d("rich",contactez.getImagePhoto());
        Picasso.with(holder.imgProfil.getContext()).load(contactez.getImagePhoto()).into(holder.imgProfil);

    }
    @Override
    public int getItemCount() {

        return myList.size();
    }

    public class ContactezNousViewHolder extends RecyclerView.ViewHolder{

        public final CircleImageView imgProfil;
        public final TextView nomProfil, descProfil;
        public final CardView contact;

        public ContactezNousViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProfil = itemView.findViewById(R.id.imgProfile);
            nomProfil = itemView.findViewById(R.id.nomDesc);
            descProfil = itemView.findViewById(R.id.detailsDesc);
            contact = itemView.findViewById(R.id.idContactez);
        }
    }
}