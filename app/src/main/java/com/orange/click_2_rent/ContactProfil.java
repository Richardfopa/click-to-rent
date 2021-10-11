package com.orange.click_2_rent;

public class ContactProfil {

    private String imgProfil;
    private String momProfil;
    private String descriptionProfil;
    private String imagePhoto;


    public ContactProfil(String nomProfil, String descriptionProfil, String imagePhoto) {

        this.imgProfil = imgProfil;
        this.descriptionProfil = descriptionProfil;
        this.imagePhoto = imagePhoto;
    }

    public String getImgProfil() {

        return imgProfil;
    }

    public String getMomProfil() {

        return momProfil;
    }

    public String getDescriptionProfil() {

        return descriptionProfil;
    }

    public String getImagePhoto() {
        return imagePhoto;
    }

    public void setImagePhoto(String imagePhoto) {
        this.imagePhoto = imagePhoto;
    }
}
