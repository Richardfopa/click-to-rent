package com.orange.click_2_rent.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.Date;

public class Service implements Parcelable {

    private String id;
    private String title;
    private Boolean status;
    private String description;
    private String photo_service;

    private String doc_service;
    private String categorie;
    private Timestamp add_date;
    private String name_provider;
    private ArrayList<Photo> photos;
    private ArrayList<Users> clients;
    private ArrayList<Commentaire> commentaire;
    private ArrayList<Integer> note;

    public Service() {
    }

    public Service(String title) {
        this.title = title;
    }

    public Service(String id, String title, Boolean status, String description, String photo_service, String categorie, Timestamp addDate, String name_provider, ArrayList<Photo> photos, ArrayList<Users> clients, ArrayList<Commentaire> commentaire, ArrayList<Integer> note) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.photo_service = photo_service;
        this.categorie = categorie;
        this.add_date = addDate;
        this.name_provider = name_provider;
        this.photos = photos;
        this.clients = clients;
        this.commentaire = commentaire;
        this.note = note;
    }

    public Service( Boolean status, String description, String photo_service,
                    String categorie, String name_provider){
        this.status = status;
        this.description = description;
        this.photo_service = photo_service;
        this.categorie = categorie;
        this.add_date =new Timestamp(new Date());
        this.name_provider = name_provider;
    }
    protected Service(Parcel in) {
        id = in.readString();
        title = in.readString();
        byte tmpStatus = in.readByte();
        status = tmpStatus == 0 ? null : tmpStatus == 1;
        description = in.readString();
        photo_service = in.readString();
        doc_service = in.readString();
        categorie = in.readString();
        add_date = in.readParcelable(Timestamp.class.getClassLoader());
        name_provider = in.readString();
        clients = in.createTypedArrayList(Users.CREATOR);
    }

    public static final Creator<Service> CREATOR = new Creator<Service>() {
        @Override
        public Service createFromParcel(Parcel in) {
            return new Service(in);
        }

        @Override
        public Service[] newArray(int size) {
            return new Service[size];
        }
    };

    public String getDoc_service() {
        return doc_service;
    }

    public void setDoc_service(String doc_service) {
        this.doc_service = doc_service;
    }

    public Timestamp getAdd_date() {
        return add_date;
    }

    public void setAdd_date(Timestamp add_date) {
        this.add_date = add_date;
    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getStatus() {
        return status;
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

    public String getPhotoService() {
        return photo_service;
    }

    public void setPhotoService(String photoService) {
        this.photo_service = photoService;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Timestamp getAddDate() {
        return add_date;
    }

    public String getName_provider() {
        return name_provider;
    }

    public void setName_provider(String name_provider) {
        this.name_provider = name_provider;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto_service() {
        return photo_service;
    }

    public void setPhoto_service(String photo_service) {
        this.photo_service = photo_service;
    }

    public void setAddDate(Timestamp addDate) {
        add_date = addDate;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    public void setClients(ArrayList<Users> clients) {
        this.clients = clients;
    }

    public void setCommentaire(ArrayList<Commentaire> commentaire) {
        this.commentaire = commentaire;
    }

    public void setNote(ArrayList<Integer> note) {
        this.note = note;
    }

    public ArrayList<Photo> getPhotos() {
        if( photos == null);
        photos = new ArrayList<Photo>();
        return photos;
    }

    public void addPhotos(Photo photo) {

        this.photos.add(photo);
    }

    public ArrayList<Users> getClients() {
        if (clients == null);
        clients = new ArrayList<Users>();
        return clients;
    }

    public void addClients(Users clients) {

        this.clients.add(clients);
    }

    public ArrayList<Commentaire> getCommentaire() {
        if (commentaire == null);
        commentaire = new ArrayList<Commentaire>();
        return commentaire;
    }

    public void addCommentaire(Commentaire commentaire) {

        this.commentaire.add(commentaire);
    }

    public ArrayList<Integer> getNote() {
        if (note == null);
        note=new ArrayList<Integer>();
        return note;
    }

//        public void setNote(Integer note) {
//
//            this.note.add(note);
//        }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", photoService=" + photo_service +
                ", contient=" + photos +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeByte((byte) (status == null ? 0 : status ? 1 : 2));
        parcel.writeString(description);
        parcel.writeString(photo_service);
        parcel.writeString(doc_service);
        parcel.writeString(categorie);
        parcel.writeParcelable(add_date, i);
        parcel.writeString(name_provider);
        parcel.writeTypedList(clients);
    }
}