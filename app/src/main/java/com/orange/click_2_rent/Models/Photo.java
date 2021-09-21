package com.orange.click_2_rent.Models;
/***********************************************************************
 * Author:  Team click-2-rent
 * Purpose: Defines the Class Categorie
 ***********************************************************************/

import java.util.*;


public class Photo {

   private String id;

   private String urlPhoto;

   public String getIdPhoto() {
      return id;
   }

   public Photo(String urlPhoto) {
      this.urlPhoto = urlPhoto;
   }

   public String getId() {
      return id;
   }

   public String getUrlPhoto() {
      return urlPhoto;
   }

   public Photo() {
   }


   public void setUrlPhoto(String nomPhoto) {
      this.urlPhoto = nomPhoto;
   }

   @Override
   public String toString() {
      return "Photo{" +
              "idPhoto=" + id +
              ", urlPhoto='" + urlPhoto + '\'' +
              '}';
   }
}