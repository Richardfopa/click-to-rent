package com.orange.click_2_rent;

import com.google.firebase.Timestamp;
public class Presentation_prestations {


    String titre_prestation;
    String miniDescription;
    String photo;
    Timestamp date_prestation;



    public Presentation_prestations(String titre_prestation, String miniDescription, Timestamp date_prestation, String photo) {
        this.titre_prestation = titre_prestation;
        this.miniDescription = miniDescription;
        this.date_prestation = date_prestation;
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public String getTitre_prestation() {

        return titre_prestation;
    }

    public String getMiniDescription() {

        return miniDescription;
    }

    public Timestamp getDate_prestation() {

        return date_prestation;
    }
}