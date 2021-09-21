package com.orange.click_2_rent.Models; /***********************************************************************
 * Module:  Client.java
 * Author:  Mannschaft
 * Purpose: Defines the Class Client
 ***********************************************************************/

import java.util.*;

/** @pdOid ade7f29d-6cfb-47e8-a014-3170afe1509f */
public class Client {
   /** @pdOid 90b4d57f-c88d-4022-a1bd-f84e629ba7fb */
   private int idClient;
   /** @pdOid 8663c700-2e65-476e-9db4-1283be16bc75 */
   private String nom;
   /** @pdOid 4346754e-a58e-4793-82ef-e60c224fec53 */
   private Number telphone;
   /** @pdOid b1def15a-68e9-4682-bab9-1f1c6ffffab8 */
   private String email;

   public int getIdClient() {
      return idClient;
   }

   public String getNom() {
      return nom;
   }

   public void setNom(String nom) {
      this.nom = nom;
   }

   public Number getTelphone() {
      return telphone;
   }

   public void setTelphone(Number telphone) {
      this.telphone = telphone;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
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

   /** @pdOid 760c8c14-ed70-42cb-ab05-760ea5426120 */
   private String motDePasse;
   /** @pdOid 66a06010-59dd-4b74-9165-319f2a829d21 */
   private int photoProfil;

   public Client() {
   }

   /** @pdRoleInfo migr=no name=Photo assc=association8 mult=1..1 */
   public Photo a_auplus;
   /** @pdRoleInfo migr=no name=Service assc=association5 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public ArrayList<Service> demande;
   
   /** @pdOid 87b05549-5f4a-4ae4-9d05-9b1e522335b6 */
   public void seConnecter() {
      // TODO: implement
   }
   
   /** @pdOid 01759749-1bdb-4904-a5c6-3d7f371a37ec */
   public void deConnecter() {
      // TODO: implement
   }
   
   /** @pdOid 5ae9a724-306f-4a57-ba2d-c7f1a4779680 */
   public void consulterService() {
      // TODO: implement
   }
   
   /** @pdOid 87eb4c5e-afc0-412f-bbdb-096033f2c2b5 */
   public void contacterPrestataire() {
      // TODO: implement
   }
   
   /** @pdOid 437d6534-ef57-43b3-8740-6041d8b58d3d */
   public void creerCompte() {
      // TODO: implement
   }
   
   /** @pdOid 574092bb-e73f-476d-b9ca-d639464feacf */
   public void ajoutercommentaire() {
      // TODO: implement
   }
   public void voirCommentaire(){

   }
   /** @pdOid 108a8c4d-421c-4df9-8257-300f643184ca */
   public void voirHistorique() {
      // TODO: implement
   }
   
   /** @pdOid d2799075-45d4-42c3-9b98-4d8aadfd6a30 */
   public void afficherProfil() {
      // TODO: implement
   }
   
   /** @pdOid 1d874d92-efe1-46a0-956d-78d803c02267 */
   public void rechercher() {
      // TODO: implement
   }
   
   
   /** @pdGenerated default getter */
   public ArrayList<Service> getDemande() {
      if (demande == null)
         demande = new ArrayList<Service>();
      return demande;
   }
   
   /** @pdGenerated default iterator getter */
   public Iterator getIteratorDemande() {
      if (demande == null)
         demande = new ArrayList<Service>();
      return demande.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newDemande */
   public void setDemande(Collection<Service> newDemande) {
      removeAllDemande();
      for (Iterator iter = newDemande.iterator(); iter.hasNext();)
         addDemande((Service)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newService */
   public void addDemande(Service newService) {
      if (newService == null)
         return;
      if (this.demande == null)
         this.demande = new ArrayList<Service>();
      if (!this.demande.contains(newService))
         this.demande.add(newService);
   }
   
   /** @pdGenerated default remove
     * @param oldService */
   public void removeDemande(Service oldService) {
      if (oldService == null)
         return;
      if (this.demande != null)
         if (this.demande.contains(oldService))
            this.demande.remove(oldService);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllDemande() {
      if (demande != null)
         demande.clear();
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
              ", demande=" + demande.toString() +
              '}';
   }
}