package com.orange.click_2_rent.Models; /***********************************************************************
 * Module:  Administrateur.java
 * Author:  Mannschaft
 * Purpose: Defines the Class Administrateur
 ***********************************************************************/

import java.util.*;

/** @pdOid 990daef2-766d-400c-a83b-498f2dd7d77d */
public class Administrateur extends Prestataire {
   /** @pdOid de04778b-4ad3-42b8-acd3-0e544969bebe */
   private int idAdmin;
   
   /** @pdRoleInfo migr=no name=Service assc=association4 coll=java.util.Collection impl=java.util.HashSet mult=1..* */
   public ArrayList<Service> valide;
   
   /** @pdOid 17c62c5a-7d68-4f60-8c2a-805c64f71fbe */
   public void validerService() {
      // TODO: implement
   }
   
   /** @pdOid 21d968fc-5d42-4530-95ba-e777a7063e4c */
   public void modifier() {
      // TODO: implement
   }
   
   /** @pdOid 867387b8-fbfc-4b43-9e3f-71cbdde1cbda */
   public void supprimerUser() {
      // TODO: implement
   }
   
   
   /** @pdGenerated default getter */
   public Collection<Service> getValide() {
      if (valide == null)
         valide = new ArrayList<Service>();
      return valide;
   }
   
   /** @pdGenerated default iterator getter */
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