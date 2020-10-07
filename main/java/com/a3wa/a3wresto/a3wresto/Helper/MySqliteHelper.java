package com.a3wa.a3wresto.a3wresto.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME;
    private static int VERSION;

    static{
        DATABASE_NAME = "troisw.db";
        VERSION = 1;
    }

    public MySqliteHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE favori(id integer primary key);");
        db.execSQL("CREATE TABLE panier(id integer primary key, qte integer );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
