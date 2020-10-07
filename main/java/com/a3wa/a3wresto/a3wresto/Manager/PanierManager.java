package com.a3wa.a3wresto.a3wresto.Manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.a3wa.a3wresto.a3wresto.Helper.MySqliteHelper;
import com.a3wa.a3wresto.a3wresto.Model.Panier;
import com.a3wa.a3wresto.a3wresto.Model.Recette;

import java.util.ArrayList;
import java.util.List;

public class PanierManager {

    private SQLiteDatabase database;
    private MySqliteHelper dbHelper;

    public PanierManager(Context context) {
        this.dbHelper = new MySqliteHelper(context);
    }

    private void open() {
        this.database = dbHelper.getWritableDatabase();
    }

    private void close() {
        this.dbHelper.close();
    }

    public long add(Panier panier) {

        ContentValues values = new ContentValues();

        values.put("qte", panier.getQte());

        open();
        long id = this.database.insert("panier", null, values);
        close();

        return id;

    }

    public List<Panier> getAll() {

        List<Panier> list = new ArrayList<>();

        open();

        Cursor cursor = this.database.query("panier", new String[]{"id, qte"}, null, null, null, null, "id");
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            Panier panier = new Panier();
            panier.setId(cursor.getInt(cursor.getColumnIndex("id")));
            panier.setQte(cursor.getInt(cursor.getColumnIndex("qte")));

            list.add(panier);
            cursor.moveToNext();

        }

        cursor.close();
        close();

        return list;

    }

    public void delete(int recetteSelect) {
        this.open();
        this.database.delete("panier", "id=?", new String[]{String.valueOf(recetteSelect)});
        this.close();
    }

    public Panier getProduct (int idRecette){
        Panier panier = null;

        open();
        String[] colonne = {"id","qte"};
        Cursor cursor = this.database.query("panier", colonne, "id=" + idRecette, null, null, null, "id");

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            panier = new Panier();
            panier.setId(cursor.getInt(cursor.getColumnIndex("id")));
            panier.setQte(cursor.getInt(cursor.getColumnIndex("qte")));
            cursor.moveToNext();
        }
        cursor.close();
        this.close();

        return panier;
    }
}


