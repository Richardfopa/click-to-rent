package com.orange.click_2_rent.Models;
/***********************************************************************
 * Author:  Team click-2-rent
 * Purpose: Defines the Class Categorie
 ***********************************************************************/

<<<<<<< HEAD
import com.firebase.ui.auth.data.model.User;
=======
import com.google.firebase.Timestamp;
>>>>>>> richard

import java.util.*;


public class Service {
<<<<<<< HEAD

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

   public Service( Boolean status, String description, int photoService,
                  String categorie, String nom_prestataire ){
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
=======
   private int id;
   private Boolean status;
   private String description;
   private String title;
   private String categorie;

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
>>>>>>> richard
   }

   public String getCategorie() {
      return categorie;
   }

   public void setCategorie(String categorie) {
      this.categorie = categorie;
   }

<<<<<<< HEAD
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
=======
   public void setDatedajout(Timestamp datedajout) {
      this.datedajout = datedajout;
   }

   private Timestamp datedajout;

   public Timestamp getDatedajout() {
      return datedajout;
   }

   /** @pdOid eee5ade6-c392-4d2f-bfb1-09eba0f09a28 */
//   private int photoService;
   
   /** @pdRoleInfo migr=no name=Photo assc=association9 coll=java.util.Collection impl=java.util.HashSet mult=1..* */
   public ArrayList<Photo> contient;


   public Service(Boolean status,String title,String categorie, String description, ArrayList<Photo> contient) {
      this.status = status;
      this.categorie = categorie;
      this.title = title;
      this.description = description;
      this.contient = contient;
      datedajout = new Timestamp(new Date());
   }

   public Service(int id, Boolean status, String description, ArrayList<Photo> contient) {
      this.id = id;
      this.status = status;
      this.description = description;
      this.contient = contient;
      this.datedajout = new Timestamp(new Date());
   }

   /** @pdGenerated default getter */
   public ArrayList<Photo> getContient() {
      if (contient == null)
         contient = new ArrayList<Photo>();
      return contient;
   }
   
//   /** @pdGenerated default iterator getter */
//   public Iterator getIteratorContient() {
//      if (contient == null)
//         contient = new ArrayList<Photo>();
//      return contient.iterator();
//   }
   
   /** @pdGenerated default setter
     * @param newContient */
   public void setContient(ArrayList<Photo> newContient) {
      removeAllContient();
      for (Iterator iter = newContient.iterator(); iter.hasNext();)
         addContient((Photo)iter.next());
   }

   public int getId() {
      return id;
   }

   public Boolean getStatus() {
      return status;
   }

   public Service() {
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

   /** @pdGenerated default add
     * @param newPhoto */
   public void addContient(Photo newPhoto) {
      if (newPhoto == null)
         return;
      if (this.contient == null)
         this.contient = new ArrayList<Photo>();
      if (!this.contient.contains(newPhoto))
         this.contient.add(newPhoto);
   }
   
   /** @pdGenerated default remove
     * @param oldPhoto */
   public void removeContient(Photo oldPhoto) {
      if (oldPhoto == null)
         return;
      if (this.contient != null)
         if (this.contient.contains(oldPhoto))
            this.contient.remove(oldPhoto);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllContient() {
      if (contient != null)
         contient.clear();
>>>>>>> richard
   }

   @Override
   public String toString() {
      return "Service{" +
              "id=" + id +
              ", status=" + status +
              ", description='" + description + '\'' +
<<<<<<< HEAD
              ", photoService=" + photoService +
              ", contient=" + photos +
=======
              ", contient=" + contient +
>>>>>>> richard
              '}';
   }
}