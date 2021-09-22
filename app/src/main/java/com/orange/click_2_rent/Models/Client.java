package com.orange.click_2_rent.Models; /***********************************************************************
 * Module:  Client.java
 * Author:  Mannschaft
 * Purpose: Defines the Class Client
 ***********************************************************************/

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.type.DateTime;

import java.util.*;


@IgnoreExtraProperties
public class Client {

   public String idClient;
   public String nom;
   public String telphone;
   public String email;
   public Timestamp datedarrive;
   public Timestamp datedesorti;
   public String motDePasse;
   public String typeclient;
   public int photoProfil;
   public Photo a_auplus;
   public ArrayList<Service> demande;



   public Client() {
   }

   public Client(String nom, String telphone, String email, String motDePasse,String type) {
//      this.idClient = idClient;
      this.nom = nom;
      this.telphone = telphone;
      this.email = email;
      this.typeclient = type;
      this.motDePasse = motDePasse;
      this.datedarrive = new Timestamp(new Date());
      this.datedesorti = null;
   }

   public String getIdClient() {
      return idClient;
   }

   public void setIdClient(String idClient) {
      this.idClient = idClient;
   }

   public String getNom() {
      return nom;
   }

   public void setNom(String nom) {
      this.nom = nom;
   }

   public String getTelphone() {
      return telphone;
   }

   public void setTelphone(String telphone) {
      this.telphone = telphone;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setDatedarrive(Timestamp datedarrive) {
      this.datedarrive = datedarrive;
   }

   public void setDatedesorti(Timestamp datedesorti) {
      this.datedesorti = datedesorti;
   }

   public String getMotDePasse() {
      return motDePasse;
   }

   public void setMotDePasse(String motDePasse) {
      this.motDePasse = motDePasse;
   }

   public int getPhotoProfil() {
      return photoProfil;
   }

   public void setPhotoProfil(int photoProfil) {
      this.photoProfil = photoProfil;
   }

   public Photo getA_auplus() {
      return a_auplus;
   }

   public void setA_auplus(Photo a_auplus) {
      this.a_auplus = a_auplus;
   }


   public String getTypeclient() {
      return typeclient;
   }

   public void setTypeclient(String typeclient) {
      this.typeclient = typeclient;
   }

/*  public void setIdClient(String idClient) {
//      this.idClient = idClient;
//   }

//   public void setDatedarrive(String datedarrive) {
//      this.datedarrive = datedarrive;
//   }
//
//   public void setDatedesorti(String datedesorti) {
//      this.datedesorti = datedesorti;
/   }*/


   public Timestamp getDatedarrive() {
      return datedarrive;
   }

   public Timestamp getDatedesorti() {
      return datedesorti;
   }

   public ArrayList<Service> getDemande() {
      if (demande == null)
         demande = new ArrayList<Service>();
      return demande;
   }



//   public Iterator getIteratorDemande() {
//      if (demande == null)
//         demande = new ArrayList<Service>();
//      return demande.iterator();
//   }

   public void setDemande(ArrayList<Service> newDemande) {
      removeAllDemande();
      for (Iterator iter = newDemande.iterator(); iter.hasNext();)
         addDemande((Service)iter.next());
   }
   

   public void addDemande(Service newService) {
      if (newService == null)
         return;
      if (this.demande == null)
         this.demande = new ArrayList<Service>();
      if (!this.demande.contains(newService))
         this.demande.add(newService);
   }

   public void removeDemande(Service oldService) {
      if (oldService == null)
         return;
      if (this.demande != null)
         if (this.demande.contains(oldService))
            this.demande.remove(oldService);
   }

   public void removeAllDemande() {
      if (demande != null)
         demande.clear();
   }

   public void seConnecter() {
      // TODO: implement
   }
   public void deConnecter() {
      // TODO: implement
   }

   public void consulterService() {
      // TODO: implement
   }

   public void contacterPrestataire() {
      // TODO: implement
   }

   public void creerCompte() {
      // TODO: implement
   }

   public void ajoutercommentaire() {
      // TODO: implement
   }

   public void voirHistorique() {
      // TODO: implement
   }

   public void afficherProfil() {
      // TODO: implement
   }

   public void rechercher() {
      // TODO: implement
   }


   @Override
   public String toString() {
      return "Client{" +
              "idClient=" + idClient +
              ", nom='" + nom + '\'' +
              ", telphone=" + telphone +
              ", email='" + email + '\'' +
              ", motDePasse='" + motDePasse + '\'' +
              ", photoProfil=" + photoProfil +
              ", a_auplus=" + a_auplus +
//              ", demande=" + demande.toString() +
              '}';
   }
}