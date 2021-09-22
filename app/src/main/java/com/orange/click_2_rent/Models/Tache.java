package com.orange.click_2_rent.Models;

import com.google.firebase.Timestamp;

public class Tache {

    private int tCheckboxe;
    private String tLabel;
    private Timestamp date;


    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Tache(int tCheckboxe, String tLabel, Timestamp date) {
        this.tCheckboxe = tCheckboxe;
        this.tLabel = tLabel;
        this.date = date;
    }

    public int gettCheckboxe() {
        return tCheckboxe;
    }

    public void settCheckboxe(int tCheckboxe) {
        this.tCheckboxe = tCheckboxe;
    }

    public String gettLabel() {
        return tLabel;
    }

    public void settLabel(String tLabel) {
        this.tLabel = tLabel;
    }

    @Override
    public String toString() {
        return "Tache{" +
                "tCheckboxe=" + tCheckboxe +
                ", tLabel='" + tLabel + '\'' +
                ", date=" + date +
                '}';
    }

}
