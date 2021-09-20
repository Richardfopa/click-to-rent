package com.orange.click_2_rent.Models;
/***********************************************************************
 * Author:  Team click-2-rent
 * Purpose: Defines the Class Users
 ***********************************************************************/

import java.util.*;


public class Administrateur extends Users {

   private int idAdmin;
   public ArrayList<Service> valide;
   

   public void validerService() {
      // TODO: implement
   }
   

   public void modifier() {
      // TODO: implement
   }
   

   public void supprimerUser() {
      // TODO: implement
   }
   
   

   public Collection<Service> getValide() {
      if (valide == null)
         valide = new ArrayList<Service>();
      return valide;
   }

   public Iterator getIteratorValide() {
      if (valide == null)
         valide = new ArrayList<Service>();
      return valide.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newValide */
   public void setValide(ArrayList<Service> newValide) {
      removeAllValide();
      for (Iterator iter = newValide.iterator(); iter.hasNext();)
         addValide((Service)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newService */
   public void addValide(Service newService) {
      if (newService == null)
         return;
      if (this.valide == null)
         this.valide = new ArrayList<Service>();
      if (!this.valide.contains(newService))
         this.valide.add(newService);
   }
   
   /** @pdGenerated default remove
     * @param oldService */
   public void removeValide(Service oldService) {
      if (oldService == null)
         return;
      if (this.valide != null)
         if (this.valide.contains(oldService))
            this.valide.remove(oldService);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllValide() {
      if (valide != null)
         valide.clear();
   }

}