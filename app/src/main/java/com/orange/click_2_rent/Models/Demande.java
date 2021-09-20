package com.orange.click_2_rent.Models; /***********************************************************************
 * Module:  Demande.java
 * Author:  Mannschaft
 * Purpose: Defines the Class Demande
 ***********************************************************************/

import java.sql.Timestamp;
import java.util.*;

/** @pdOid 90683fd2-cd3c-4a6f-ae75-2df34c675fab */
public class Demande {
   /** @pdOid 51905566-05fb-4606-bacf-0d88e3e15871 */
   private Timestamp date;
   /** @pdOid 7c4bab72-3aae-420b-b3f5-ba323630b44b */
   private int idDemande;
   /** @pdOid 8af457a0-ffa1-4fd4-a7dd-22abaf03b826 */
   private String message;
   /** @pdOid 9a9bb163-b981-4180-8344-a3c4b14afc13 */
   private String note;



   public Demande(String message, String note) {
      this.message = message;
      this.note = note;
   }

   public Demande() {
   }

   public Timestamp getDate() {
      return date;
   }

   public void setDate(Timestamp date) {
      this.date = date;
   }

   public int getIdDemande() {
      return idDemande;
   }

   public void setIdDemande(int idDemande) {
      this.idDemande = idDemande;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String getNote() {
      return note;
   }

   public void setNote(String note) {
      this.note = note;
   }

   @Override
   public String toString() {
      return "Demande{" +
              "date=" + date +
              ", idDemande=" + idDemande +
              ", message='" + message + '\'' +
              ", note='" + note + '\'' +
              '}';
   }
}