package com.orange.click_2_rent.Models; /***********************************************************************
 * Module:  Service.java
 * Author:  Mannschaft
 * Purpose: Defines the Class Service
 ***********************************************************************/

import java.util.*;

/** @pdOid bc0e8760-6093-4821-a026-2c8244a33989 */
public class Service {
   /** @pdOid d46f314f-95ec-4303-b145-327b38791874 */
   private int id;
   /** @pdOid b5b2c7c3-ef14-4a9c-99ac-0ca846875220 */
   private Boolean status;
   /** @pdOid e84c2e4a-7c0c-4da1-b571-a0957ace0e49 */
   private String description;
   /** @pdOid eee5ade6-c392-4d2f-bfb1-09eba0f09a28 */
   private int photoService;
   
   /** @pdRoleInfo migr=no name=Photo assc=association9 coll=java.util.Collection impl=java.util.HashSet mult=1..* */
   public ArrayList<Photo> contient;
   
   
   /** @pdGenerated default getter */
   public ArrayList<Photo> getContient() {
      if (contient == null)
         contient = new ArrayList<Photo>();
      return contient;
   }
   
   /** @pdGenerated default iterator getter */
   public Iterator getIteratorContient() {
      if (contient == null)
         contient = new ArrayList<Photo>();
      return contient.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newContient */
   public void setContient(ArrayList<Photo> newContient) {
      removeAllContient();
      for (Iterator iter = newContient.iterator(); iter.hasNext();)
         addContient((Photo)iter.next());
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
              ", photoService=" + photoService +
              ", contient=" + contient +
              '}';
   }
}