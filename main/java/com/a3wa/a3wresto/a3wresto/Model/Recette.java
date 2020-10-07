package com.a3wa.a3wresto.a3wresto.Model;

import java.io.Serializable;

public class Recette implements Serializable {
    private int id;
    private String title;
    private String photo;
    private int note;
    private  String tempsPreparation;
    private String tempsCookRime;
    private String calorie;
    private String ingredients;
    private String instructions;
    private Restaurant restaurant;

    public Recette() {
    }

    public Recette(int id, String title, String photo, int note) {
        this.id = id;
        this.title = title;
        this.photo = photo;
        this.note = note;
    }

    public Recette(int id, String title, String photo, int note, String tempsPreparation, String tempsCookRime, String calorie, String ingredients, String instructions, Restaurant restaurant) {
        this.id = id;
        this.title = title;
        this.photo = photo;
        this.note = note;
        this.tempsPreparation = tempsPreparation;
        this.tempsCookRime = tempsCookRime;
        this.calorie = calorie;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.restaurant = restaurant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getTempsPreparation() {
        return tempsPreparation;
    }

    public void setTempsPreparation(String tempsPreparation) {
        this.tempsPreparation = tempsPreparation;
    }

    public String getTempsCookRime() {
        return tempsCookRime;
    }

    public void setTempsCookRime(String tempsCookRime) {
        this.tempsCookRime = tempsCookRime;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
