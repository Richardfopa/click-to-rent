package com.orange.click_2_rent.Models;
/***********************************************************************
 * Author:  Team click-2-rent
 * Purpose: Defines the Class Categorie
 ***********************************************************************/

import android.os.Parcel;
import android.os.Parcelable;

import java.util.*;


public class Photo implements Parcelable {


   private String idPhoto;
   private String nomPhoto;
   private String mImageUrl;

   public Photo() {
   }


   public Photo(String idPhoto, String nomPhoto, String mImageUrl) {
      this.idPhoto = idPhoto;
      this.nomPhoto = nomPhoto;
      this.mImageUrl = mImageUrl;
   }

   public Photo(String nomPhoto, String mImageUrl) {
      this.nomPhoto = nomPhoto;
      this.mImageUrl = mImageUrl;
   }


   protected Photo(Parcel in) {
      idPhoto = in.readString();
      nomPhoto = in.readString();
      mImageUrl = in.readString();
   }

   public static final Creator<Photo> CREATOR = new Creator<Photo>() {
      @Override
      public Photo createFromParcel(Parcel in) {
         return new Photo(in);
      }

      @Override
      public Photo[] newArray(int size) {
         return new Photo[size];
      }
   };

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
              "idPhoto='" + idPhoto + '\'' +
              ", nomPhoto='" + nomPhoto + '\'' +
              ", mImageUrl='" + mImageUrl + '\'' +
              '}';
   }

   @Override
   public int describeContents() {
      return 0;
   }

   @Override
   public void writeToParcel(Parcel parcel, int i) {
      parcel.writeString(idPhoto);
      parcel.writeString(nomPhoto);
      parcel.writeString(mImageUrl);
   }
}