package com.orange.click_2_rent.Models; /***********************************************************************
 * Module:  Photo.java
 * Author:  Mannschaft
 * Purpose: Defines the Class Photo
 ***********************************************************************/

import java.util.*;

/** @pdOid bb98ae54-8819-4753-a7a9-a40f93f86c0b */
public class Photo {
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

   public Photo(int idPhoto, String nomPhoto) {
      this.idPhoto = idPhoto;
      this.nomPhoto = nomPhoto;
   }

   public Photo() {
   }


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
   }

   public String getNomPhoto() {
      return nomPhoto;
   }

   public void setNomPhoto(String nomPhoto) {
      this.nomPhoto = nomPhoto;
   }

   /** @pdOid 50b6272a-7375-47f3-8337-643d8cc40086 */
   public void modifierphoto() {
      // TODO: implement
   }
   
   /** @pdOid dfaaecc8-ab4e-4e05-be97-efd3272448bc */
   public void ajouterPhoto() {
      // TODO: implement
   }
   
   /** @pdOid 6e9710a4-adf9-4bc4-917c-e243aeaaa2da */
   public void supprimerPhoto() {
      // TODO: implement
   }

   @Override
   public String toString() {
      return "Photo{" +
              "idPhoto=" + idPhoto +
              ", nomPhoto='" + nomPhoto + '\'' +
              '}';
   }
}