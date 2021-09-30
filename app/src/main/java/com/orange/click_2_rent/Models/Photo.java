package com.orange.click_2_rent.Models;
/***********************************************************************
 * Author:  Team click-2-rent
 * Purpose: Defines the Class Categorie
 ***********************************************************************/

import java.util.*;


public class Photo {


   private String idPhoto;

   public Photo() {
   }

   private String nomPhoto;
   private String mImageUrl;
   public Photo(String idPhoto, String nomPhoto, String mImageUrl) {
      this.idPhoto = idPhoto;
      this.nomPhoto = nomPhoto;
      this.mImageUrl = mImageUrl;
   }

   public Photo(String nomPhoto, String mImageUrl) {
      this.nomPhoto = nomPhoto;
      this.mImageUrl = mImageUrl;
   }


   public String getNomPhoto() {
      return nomPhoto;
   }

   public void setNomPhoto(String nomPhoto) {
      this.nomPhoto = nomPhoto;
   }



   public String getmImageUrl() {
      return mImageUrl;
   }

   public void setmImageUrl(String mImageUrl) {
      this.mImageUrl = mImageUrl;
   }

   public String getIdPhoto() {
      return idPhoto;
   }


   public void setIdPhoto(String idPhoto) {
      this.idPhoto = idPhoto;
   }

   public String getId() {
      return idPhoto;
   }


   @Override
   public String toString() {
      return "Photo{" +
              "idPhoto=" + idPhoto +
              '}';
   }
}