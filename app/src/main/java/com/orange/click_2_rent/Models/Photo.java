package com.orange.click_2_rent.Models;
/***********************************************************************
 * Author:  Team click-2-rent
 * Purpose: Defines the Class Categorie
 ***********************************************************************/

import java.util.*;


public class Photo {

   private String idPhoto;

   private String nomPhoto;

   public String getIdPhoto() {
      return idPhoto;
   }

   public Photo(String idPhoto, String nomPhoto) {
      this.idPhoto = idPhoto;
      this.nomPhoto = nomPhoto;
   }

   public Photo() {
   }

   public String getNomPhoto() {
      return nomPhoto;
   }

   public void setNomPhoto(String nomPhoto) {
      this.nomPhoto = nomPhoto;
   }

   @Override
   public String toString() {
      return "Photo{" +
              "idPhoto=" + idPhoto +
              ", nomPhoto='" + nomPhoto + '\'' +
              '}';
   }
}