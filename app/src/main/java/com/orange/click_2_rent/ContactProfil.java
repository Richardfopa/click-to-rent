package com.orange.click_2_rent;

public class ContactProfil {

    int imgProfil;
    String momProfil, descriptionProfil;

    public ContactProfil(int imgProfil, String momProfil, String descriptionProfil) {

        this.imgProfil = imgProfil;
        this.momProfil = momProfil;
        this.descriptionProfil = descriptionProfil;
    }

    public int getImgProfil() {

        return imgProfil;
    }

    public String getMomProfil() {

        return momProfil;
    }

    public String getDescriptionProfil() {

        return descriptionProfil;
    }
}
