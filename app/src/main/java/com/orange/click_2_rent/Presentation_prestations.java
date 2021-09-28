package com.orange.click_2_rent;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Presentation_prestations implements Parcelable {

    public static final String PRESTATION_CLES = "com.orange.click.to.rent.prestataire";

    private String IdPrestation;
    private String image_profil_prestation;
    private String titre_prestation;
    private String miniDescription;
    private Date date_prestation;

    public Presentation_prestations(String idPrestation, String image_profil_prestation, String titre_prestation, String miniDescription, Date date_prestation) {
        IdPrestation = idPrestation;
        this.image_profil_prestation = image_profil_prestation;
        this.titre_prestation = titre_prestation;
        this.miniDescription = miniDescription;
        this.date_prestation = date_prestation;
    }

    public Presentation_prestations(String image_profil_prestation, String titre_prestation, String miniDescription, Date date_prestation) {
        this.image_profil_prestation = image_profil_prestation;
        this.titre_prestation = titre_prestation;
        this.miniDescription = miniDescription;
        this.date_prestation = date_prestation;
    }

    protected Presentation_prestations(Parcel in) {
        IdPrestation = in.readString();
        image_profil_prestation = in.readString();
        titre_prestation = in.readString();
        miniDescription = in.readString();
    }

    public static final Creator<Presentation_prestations> CREATOR = new Creator<Presentation_prestations>() {
        @Override
        public Presentation_prestations createFromParcel(Parcel in) {
            return new Presentation_prestations(in);
        }

        @Override
        public Presentation_prestations[] newArray(int size) {
            return new Presentation_prestations[size];
        }
    };

    public String getIdPrestation() {
        return IdPrestation;
    }

    public void setIdPrestation(String idPrestation) {
        IdPrestation = idPrestation;
    }

    public String getImage_profil_prestation() {
        return image_profil_prestation;
    }

    public void setImage_profil_prestation(String image_profil_prestation) {
        this.image_profil_prestation = image_profil_prestation;
    }

    public String getTitre_prestation() {
        return titre_prestation;
    }

    public void setTitre_prestation(String titre_prestation) {
        this.titre_prestation = titre_prestation;
    }

    public String getMiniDescription() {
        return miniDescription;
    }

    public void setMiniDescription(String miniDescription) {
        this.miniDescription = miniDescription;
    }

    public Date getDate_prestation() {
        return date_prestation;
    }

    public void setDate_prestation(Date date_prestation) {
        this.date_prestation = date_prestation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(IdPrestation);
        parcel.writeString(image_profil_prestation);
        parcel.writeString(titre_prestation);
        parcel.writeString(miniDescription);
    }
}
