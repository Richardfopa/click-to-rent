package com.orange.click_2_rent.Models;
/***********************************************************************
 * Author:  Team click-2-rent
 * Purpose: Defines the Class Categorie
 ***********************************************************************/

import java.util.*;

public class Categorie {
   private String id;
   private String nom;
   private String description;
   private ArrayList<Service> serviceTab;

   public Categorie() {

   }

   public Categorie(String nom, String description) {
      this.nom = nom;
      this.description = description;
      this.serviceTab = new ArrayList<Service>();
   }

   public String getNom() {
      return nom;
   }

   public String getId() {
      return id;
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

   public ArrayList<Service> getServiceTab() {
      return serviceTab;
   }

   public void modifer() {
      // TODO: implement
   }
   

   public void ajouterService(Service service) {

      this.serviceTab.add(service);

   }

   public void supprimer(Service service) {

      this.serviceTab.remove(service);
   }

   @Override
   public String toString() {
      return "Categorie{" +

              ", nom='" + nom + '\'' +
              ", description='" + description + '\'' +
              '}';
   }

}