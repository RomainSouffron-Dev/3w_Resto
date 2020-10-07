package com.a3wa.a3wresto.a3wresto.Manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.a3wa.a3wresto.a3wresto.Helper.MySqliteHelper;
import com.a3wa.a3wresto.a3wresto.Model.Recette;

import java.util.ArrayList;
import java.util.List;

public class FavoriManager {

    private SQLiteDatabase database;
    private MySqliteHelper dbHelper;

    public FavoriManager(Context context){
        this.dbHelper = new MySqliteHelper(context);
    }

    private void open(){
        this.database = dbHelper.getWritableDatabase();
    }

    private void close(){
        this.dbHelper.close();
    }

    public long add(int idRecette){

        ContentValues values = new ContentValues();

        values.put("id",idRecette);

        open();
        long id = this.database.insert("favori", null, values);
        close();

        return id;

    }

    public List<Integer> getAll(){
        List<Integer> list = new ArrayList<>();

        open();

        Cursor cursor = this.database.query("favori", new String[]{"id"}, null, null, null, null, "id" );

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            list.add(cursor.getInt(cursor.getColumnIndex("id")));
            cursor.moveToNext();

        }
        cursor.close();
        close();

        return list;
    }

    public void delete(int recetteSelect){
        this.open();
        this.database.delete("favori","id=?",new String[]{String.valueOf(recetteSelect)});
        this.close();
    }
}
