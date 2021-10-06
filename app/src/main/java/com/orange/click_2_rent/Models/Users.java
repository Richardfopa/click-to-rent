package com.orange.click_2_rent.Models;
/***********************************************************************
 * Author:  Team click-2-rent
 * Purpose: Defines the Class Users
 ***********************************************************************/

import android.os.Parcel;
import android.os.Parcelable;

import java.util.*;

public class Users implements Parcelable {
    private String nom;
    private String telphone;
    private String email;
    private Photo photoClient;
    private String adresse;
    private String motDePasse;
    private ArrayList<Service> mesServices;
    private ArrayList<Service> servicesDemande;
    private ArrayList<Commentaire> mesCommentaires;
    private String id;

    public Users(String nom, String telphone, String email, Photo photoClient, String adresse, ArrayList<Service> mesServices, ArrayList<Service> servicesDemande, ArrayList<Commentaire> mesCommentaires, String motDePasse, String id) {
        this.nom = nom;
        this.telphone = telphone;
        this.email = email;
        this.photoClient = photoClient;
        this.adresse = adresse;
        this.mesServices = mesServices;
        this.servicesDemande = servicesDemande;
        this.mesCommentaires = mesCommentaires;
        this.motDePasse = motDePasse;
        this.id = id;
    }

    protected Users(Parcel in) {
        nom = in.readString();
        telphone = in.readString();
        email = in.readString();
        adresse = in.readString();
        motDePasse = in.readString();
        id = in.readString();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    public void setMesServices(ArrayList<Service> mesServices) {
        this.mesServices = mesServices;
    }

    public void setServicesDemande(ArrayList<Service> servicesDemande) {
        this.servicesDemande = servicesDemande;
    }

    public void setMesCommentaires(ArrayList<Commentaire> mesCommentaires) {
        this.mesCommentaires = mesCommentaires;
    }



    public Users(String nom, String telphone, String email, String motDePasse) {
        this.nom = nom;
        this.telphone = telphone;
        this.email = email;
        this.motDePasse = motDePasse;
        this.mesServices=new ArrayList<Service>();
        this.servicesDemande=new ArrayList<Service>();
        this.mesCommentaires=new ArrayList<Commentaire>();

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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
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
                ", photoClient=" + photoClient +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nom);
        parcel.writeString(telphone);
        parcel.writeString(email);
        parcel.writeString(adresse);
        parcel.writeString(motDePasse);
        parcel.writeString(id);
    }
}