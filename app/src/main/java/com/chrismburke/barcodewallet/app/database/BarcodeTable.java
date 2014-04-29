package com.chrismburke.barcodewallet.app.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Chris on 4/20/2014.
 */
public class BarcodeTable {

    //Database table
    public static final String TABLE_BARCODE = "barcode";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DATA = "data";

    // Database creation SQL Statment
    private static final String DATABASE_CREATE = "create table "
            + TABLE_BARCODE
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_DATA + " text not null"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_BARCODE);
        onCreate(database);
    }
}
