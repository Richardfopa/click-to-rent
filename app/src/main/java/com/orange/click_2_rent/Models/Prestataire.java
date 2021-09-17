package com.orange.click_2_rent.Models; /***********************************************************************
 * Module:  Prestataire.java
 * Author:  Mannschaft
 * Purpose: Defines the Class Prestataire
 ***********************************************************************/

import java.util.*;

/** @pdOid 0d38c0c1-b6c5-4754-98a6-9f6b627a7e44 */
public class Prestataire extends Client {
   /** @pdOid 053d0eec-0f11-4785-986c-7f12991a1919 */
   private int idPrestataire;

   public Prestataire(int idPrestataire, ArrayList<Categorie> appartient) {
      this.idPrestataire = idPrestataire;
      this.appartient = appartient;
   }

   /** @pdRoleInfo migr=no name=Categorie assc=association6 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public ArrayList<Categorie> appartient;

   public Prestataire() {
   }


   
   /** @pdOid 866952d3-1008-479d-912e-856fad307271 */
   public void ajouterService() {
      // TODO: implement
   }
   
   /** @pdOid b9453d2a-ddb8-4e0f-949a-e692d6d7dbb8 */
   public void afficherListeService() {
      // TODO: implement
   }
   
   /** @pdOid 66a2d3de-26e9-4f78-9500-98b8dac4a95b */
   public void afficherHistoriqueClient() {
      // TODO: implement
   }
   
   /** @pdOid e27c3a47-c51c-4933-9092-54f37af453d8 */
   public void supprimerService() {
      // TODO: implement
   }
   
   
   /** @pdGenerated default getter */
   public ArrayList<Categorie> getAppartient() {
      if (appartient == null)
         appartient = new ArrayList<Categorie>();
      return appartient;
   }
   
   /** @pdGenerated default iterator getter */
   public Iterator getIteratorAppartient() {
      if (appartient == null)
         appartient = new ArrayList<Categorie>();
      return appartient.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAppartient */
   public void setAppartient(Collection<Categorie> newAppartient) {
      removeAllAppartient();
      for (Iterator iter = newAppartient.iterator(); iter.hasNext();)
         addAppartient((Categorie)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newCategorie */
   public void addAppartient(Categorie newCategorie) {
      if (newCategorie == null)
         return;
      if (this.appartient == null)
         this.appartient = new ArrayList<Categorie>();
      if (!this.appartient.contains(newCategorie))
         this.appartient.add(newCategorie);
   }
   
   /** @pdGenerated default remove
     * @param oldCategorie */
   public void removeAppartient(Categorie oldCategorie) {
      if (oldCategorie == null)
         return;
      if (this.appartient != null)
         if (this.appartient.contains(oldCategorie))
            this.appartient.remove(oldCategorie);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllAppartient() {
      if (appartient != null)
         appartient.clear();
   }

   @Override
   public String toString() {
      return "Prestataire{" +
              "a_auplus=" + a_auplus +
              ", demande=" + demande +
              ", idPrestataire=" + idPrestataire +
              ", appartient=" + appartient +
              '}';
   }
}