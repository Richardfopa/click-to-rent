package com.orange.click_2_rent.Models; /***********************************************************************
 * Module:  Service.java
 * Author:  Mannschaft
 * Purpose: Defines the Class Service
 ***********************************************************************/

import com.google.firebase.Timestamp;

import java.util.*;

/** @pdOid bc0e8760-6093-4821-a026-2c8244a33989 */
public class Service {
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
   }

   public String getCategorie() {
      return categorie;
   }

   public void setCategorie(String categorie) {
      this.categorie = categorie;
   }

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
   }

   @Override
   public String toString() {
      return "Service{" +
              "id=" + id +
              ", status=" + status +
              ", description='" + description + '\'' +
              ", contient=" + contient +
              '}';
   }
}