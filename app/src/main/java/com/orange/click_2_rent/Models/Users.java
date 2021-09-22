package com.orange.click_2_rent.Models;
/***********************************************************************
 * Author:  Team click-2-rent
 * Purpose: Defines the Class Users
 ***********************************************************************/

import java.util.*;

public class Users {
    private String nom;
    private Number telphone;
    private String email;
    private Photo photoClient;
    private String adresse;
    private String ville;
    private ArrayList<Service> mesServices;
    private ArrayList<Service> servicesDemande;
    private ArrayList<Commentaire> mesCommentaires;
    private String motDePasse;
    private int photoProfil;
    private String id;

    public Users(String nom, Number telphone, String email, String motDePasse) {
        this.nom = nom;
        this.telphone = telphone;
        this.email = email;
        this.motDePasse = motDePasse;
        this.mesServices=new ArrayList<Service>();
        this.servicesDemande=new ArrayList<Service>();
        this.mesCommentaires=new ArrayList<Commentaire>();
        FirebasesUtil.addUser(this);
    }

    public Users() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Service> getMesServices() {
        if (mesServices == null);
            mesServices = new ArrayList<Service>();
        return mesServices;
    }

    public void newServices(Service service) {
        this.mesServices.add(service);
    }

    public ArrayList<Service> getServicesDemande() {
        if (servicesDemande == null);
            servicesDemande=new ArrayList<Service>();
        return servicesDemande;
    }

    public void newServiceRequest(Service service) {
        this.servicesDemande.add(service);
    }

    public ArrayList<Commentaire> getMesCommentaires() {
        if (mesCommentaires == null);
            mesCommentaires=new ArrayList<Commentaire>();
        return mesCommentaires;
    }

    public void newCommentaires(Commentaire comment) {
        this.mesCommentaires.add(comment);
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

    public Photo getPhotoClient() {
        return photoClient;
    }

    public void setPhotoClient(Photo photoClient) {
        this.photoClient = photoClient;
    }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }


    //Ajouter les services demandé d'un utilisateur
    public void addMesService(Service service){
        this.mesServices.add(service);
    }

    public void addServiceDemande(Service service){
        this.servicesDemande.add(service);
    }

    public void addCommentaire(Commentaire comment){
        this.mesCommentaires.add(comment);
    }



    @Override
    public String toString() {
        return "Users{" +
                ", nom='" + nom + '\'' +
                ", telphone=" + telphone +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", photoProfil=" + photoProfil +
                ", photoClient=" + photoClient +
                '}';
    }
}