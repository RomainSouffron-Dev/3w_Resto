package com.a3wa.a3wresto.a3wresto.Model;

public class Panier {

    private int id;
    private int qte;

    public Panier() {
    }

    public Panier(int id, int qte) {
        this.id = id;
        this.qte = qte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
