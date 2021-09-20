package com.orange.click_2_rent.Models;
/***********************************************************************
 * Author:  Team click-2-rent
 * Purpose: Defines the Class Categorie
 ***********************************************************************/

import com.firebase.ui.auth.data.model.User;

import java.util.*;


public class Service {

   private String id;
   private Boolean status;
   private String description;
   private int photoService;
   private String categorie;
   private Date AddDate;
   private String nom_prestataire;
   private ArrayList<Photo> photos;
   private ArrayList<Users> clients;
   private ArrayList<Commentaire> commentaire;
   private ArrayList<Integer> note;

   public Service() {
   }

   public String getId() {
      return id;
   }

   public Service(String id, Boolean status, String description, int photoService,
                  String categorie, String nom_prestataire ){
      this.id = id;
      this.status = status;
      this.description = description;
      this.photoService = photoService;
      this.categorie = categorie;
      AddDate = new Date();
      this.nom_prestataire = nom_prestataire;
   }

   public Boolean getStatus() {
      return status;
   }

   public void setStatus(Boolean status) {
      this.status = status;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public int getPhotoService() {
      return photoService;
   }

   public void setPhotoService(int photoService) {
      this.photoService = photoService;
   }

   public String getCategorie() {
      return categorie;
   }

   public void setCategorie(String categorie) {
      this.categorie = categorie;
   }

   public Date getAddDate() {
      return AddDate;
   }

   public String getNom_prestataire() {
      return nom_prestataire;
   }

   public void setNom_prestataire(String nom_prestataire) {
      this.nom_prestataire = nom_prestataire;
   }

   public ArrayList<Photo> getPhotos() {
      if( photos == null);
         photos = new ArrayList<Photo>();
      return photos;
   }

   public void addPhotos(Photo photo) {
      this.photos.add(photo);
   }

   public ArrayList<Users> getClients() {
      if (clients == null);
         clients = new ArrayList<Users>();
      return clients;
   }

   public void addClients(Users clients) {
      this.clients.add(clients);
   }

   public ArrayList<Commentaire> getCommentaire() {
      if (commentaire == null);
         commentaire = new ArrayList<Commentaire>();
      return commentaire;
   }

   public void addCommentaire(Commentaire commentaire) {
      this.commentaire.add(commentaire);
   }

   public ArrayList<Integer> getNote() {
      if (note == null);
         note=new ArrayList<Integer>();
      return note;
   }

   public void setNote(Integer note) {
      this.note.add(note);
   }

   @Override
   public String toString() {
      return "Service{" +
              "id=" + id +
              ", status=" + status +
              ", description='" + description + '\'' +
              ", photoService=" + photoService +
              ", contient=" + photos +
              '}';
   }
}