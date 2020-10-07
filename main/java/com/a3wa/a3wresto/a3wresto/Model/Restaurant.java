package com.a3wa.a3wresto.a3wresto.Model;

import java.io.Serializable;

public class Restaurant implements Serializable {

    private int id;
    private String nom;
    private String photo2;
    private String tel;
    private String email;
    private String description;
    private String adresse;
    private String ville;
    private int cp;
    private double lat;
    private double lng;


    public Restaurant() {
    }

    public Restaurant(int id, String nom, String photo2, String tel, String email, String description, String adresse, String ville, int cp, double lat, double lng) {
        this.id = id;
        this.nom = nom;
        this.photo2 = photo2;
        this.tel = tel;
        this.email = email;
        this.description = description;
        this.adresse = adresse;
        this.ville = ville;
        this.cp = cp;
        this.lat = lat;
        this.lng = lng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
