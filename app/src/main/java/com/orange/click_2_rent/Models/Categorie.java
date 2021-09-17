package com.orange.click_2_rent.Models; /***********************************************************************
 * Module:  Categorie.java
 * Author:  Mannschaft
 * Purpose: Defines the Class Categorie
 ***********************************************************************/

import java.util.*;

/** @pdOid 583913c1-d84c-41f1-b386-ddb0bbce7289 */
public class Categorie {
   /** @pdOid 7274e7e6-1929-4388-a72e-ffe5c152d4df */
   private int id;
   /** @pdOid 8be049bc-8494-44ab-a73f-5382e0a90d33 */
   private String nom;
   /** @pdOid f309355c-39ab-4982-b9e0-b820af09509e */
   private String description;

   public Categorie() {

   }

   public Categorie(String nom, String description) {
      this.nom = nom;
      this.description = description;
   }

   public String getNom() {
      return nom;
   }

   public void setNom(String nom) {
      this.nom = nom;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   /** @pdOid 5abac4a6-118c-40ca-9a38-f2870ba6479c */
   public void consulter() {
      // TODO: implement
   }
   
   /** @pdOid 6eded7ef-e7b3-49da-942e-dc04b0af52c9 */
   public void modifer() {
      // TODO: implement
   }
   
   /** @pdOid f1aaca70-8886-466b-8298-88e8be8b3603 */
   public void ajouter() {
      // TODO: implement
   }
   
   /** @pdOid ccc42308-faae-4866-819a-adc0dcbffd25 */
   public void supprimer() {
      // TODO: implement
   }

   @Override
   public String toString() {
      return "Categorie{" +
              "id=" + id +
              ", nom='" + nom + '\'' +
              ", description='" + description + '\'' +
              '}';
   }

}