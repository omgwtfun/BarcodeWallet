package com.chrismburke.barcodewallet.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Chris on 4/20/2014.
 */
public class BarcodeDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "barcode.db";
    private static final int DATABASE_VERSION = 1;

    private BarcodeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static BarcodeDbHelper newInstance(Context context) {
        return new BarcodeDbHelper(context);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        BarcodeTable.onCreate(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        BarcodeTable.onUpgrade(database, oldVersion, newVersion);
    }

}
