package com.orange.click_2_rent.Models;
/***********************************************************************
 * Author:  Team click-2-rent
 * Purpose: Defines the Class Categorie
 ***********************************************************************/

import java.util.*;


public class Photo {
<<<<<<< HEAD

   private String id;
=======
   /** @pdOid f3962377-27f1-40e4-8d7c-3b5bc74035a3 */
   private int idPhoto;
   /** @pdOid bf5fadae-969a-453b-8b49-966c5c3c51ee */
   private String nomPhoto;
   private String mImageUrl;

   public Photo(int idPhoto, String nomPhoto, String mImageUrl) {
      this.idPhoto = idPhoto;
      this.nomPhoto = nomPhoto;
      this.mImageUrl = mImageUrl;
   }
>>>>>>> richard

   private String urlPhoto;

   public String getIdPhoto() {
      return id;
   }

<<<<<<< HEAD
   public Photo(String urlPhoto) {
      this.urlPhoto = urlPhoto;
=======

   public String getmImageUrl() {
      return mImageUrl;
   }

   public void setmImageUrl(String mImageUrl) {
      this.mImageUrl = mImageUrl;
   }

   public int getIdPhoto() {
      return idPhoto;
   }


   public void setIdPhoto(int idPhoto) {
      this.idPhoto = idPhoto;
>>>>>>> richard
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