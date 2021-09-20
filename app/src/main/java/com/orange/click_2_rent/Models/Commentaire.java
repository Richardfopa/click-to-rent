package com.orange.click_2_rent.Models;

import java.util.Date;

public class Commentaire {

    private String idComment;
    private Date addDate;
    private Users author;
    private String comment;

    public Commentaire(){

    }

    public Commentaire(String idComment, Users author, String comment) {
        this.idComment = idComment;
        this.addDate = new Date();
        this.author = author;
        this.comment = comment;
    }

    public String getIdComment() {
        return idComment;
    }

    public void setIdComment(String idComment) {
        this.idComment = idComment;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate() {
        this.addDate = new Date();
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
