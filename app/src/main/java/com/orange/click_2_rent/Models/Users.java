package com.orange.click_2_rent.Models;
/***********************************************************************
 * Author:  Team click-2-rent
 * Purpose: Defines the Class Users
 ***********************************************************************/

import static com.orange.click_2_rent.Models.FirebasesUtil.TAG;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orange.click_2_rent.Models.FirebasesUtil;

import java.util.*;

public class Users implements Parcelable {
    private String nom;
    private String telphone;
    private String email;
    private Photo photoClient;
    private String adresse;
    private String motDePasse;
    private String photo_user;
    private Timestamp date_darriver;
    private ArrayList<Service> mesServices;
    private ArrayList<String> iddemesServices;
    private ArrayList<Service> servicesDemande;
    private ArrayList<Commentaire> mesCommentaires;
    private String id;
    private FirebaseFirestore db;

    public Users(String nom, String telphone, String email, Photo photoClient, String adresse, ArrayList<Service> mesServices, ArrayList<Service> servicesDemande, ArrayList<Commentaire> mesCommentaires,ArrayList<String> iddemesservice, String motDePasse,String photo_user, String id,Timestamp date_darriver) {
        this.date_darriver = date_darriver;
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
        this.photo_user = photo_user;
        this.iddemesServices = iddemesservice;
        db = FirebaseFirestore.getInstance();
    }


    protected Users(Parcel in) {
        nom = in.readString();
        telphone = in.readString();
        email = in.readString();
        adresse = in.readString();
        motDePasse = in.readString();
        photo_user = in.readString();
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

    public String getTelphone() {
        return telphone;
    }

    public ArrayList<String> getIddemesServices() {
        return iddemesServices;
    }

    public void setIddemesServices(ArrayList<String> iddemesServices) {
        this.iddemesServices = iddemesServices;
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

    public void addIdservice(String idservice){
        this.iddemesServices.add(idservice);
    }


    public Users() {
    }

    public Timestamp getDate_darriver() {
        return date_darriver;
    }

    public void setDate_darriver(Timestamp date_darriver) {
        this.date_darriver = date_darriver;
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

    public String getPhoto_user() {
        return photo_user;
    }

    public void setPhoto_user(String photo_user) {
        this.photo_user = photo_user;
    }

    public static Creator<Users> getCREATOR() {
        return CREATOR;
    }

    public String getTelphone(String phoneNumber) {
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
                "id ='" + id + '\'' +
                "nom='" + nom + '\'' +
                ", telphone='" + telphone + '\'' +
                ", email='" + email + '\'' +
                ", photoClient=" + photoClient +
                ", adresse='" + adresse + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", photo_user='" + photo_user + '\'' +
                ", date_darriver=" + date_darriver +
                ", mesServices=" + mesServices +
                ", servicesDemande=" + servicesDemande +
                ", mesCommentaires=" + mesCommentaires +
                ", id='" + id + '\'' +
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
        parcel.writeString(photo_user);
    }

    public void addService(Service service){
        if(service != null){
            db.collection(FirebasesUtil.COL_USERS).document(getId())
                    .update("mesServices", FieldValue.arrayUnion(service.getId()));
        }else{
            Log.d(TAG, "addService for users : on faillure");
        }

        //FirebasesUtil.getReferenceFirestore(FirebasesUtil.COL_USERS)
        //FirebasesUtil.addService(service);

    }

    public void addOpinion(Service service, Commentaire opinion){

        DocumentReference serref = db.document(FirebasesUtil.COL_USERS+"/service/"+getId());
        DocumentReference opinionref = db.document(FirebasesUtil.COL_USERS+"/opinion/"+getId());

        serref.set(service);
        opinionref.set(opinionref);

        //FirebasesUtil.addService();


    }

}