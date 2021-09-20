package com.orange.click_2_rent;

public class Presentation_prestations {

    int image_profil_prestation;
    String titre_prestation;
    String miniDescription;
    int date_prestation;

    public Presentation_prestations(int image_profil_prestation, String titre_prestation, String miniDescription, int date_prestation) {

        this.image_profil_prestation = image_profil_prestation;
        this.titre_prestation = titre_prestation;
        this.miniDescription = miniDescription;
        this.date_prestation = date_prestation;
    }

    public int getImage_profil_prestation() {

        return image_profil_prestation;
    }

    public String getTitre_prestation() {

        return titre_prestation;
    }

    public String getMiniDescription() {

        return miniDescription;
    }

    public int getDate_prestation() {

        return date_prestation;
    }
}
