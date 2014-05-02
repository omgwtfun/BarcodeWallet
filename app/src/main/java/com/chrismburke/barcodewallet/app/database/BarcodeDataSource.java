package com.chrismburke.barcodewallet.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 4/20/2014.
 */
public class BarcodeDataSource {
    private SQLiteDatabase database;
    private BarcodeDbHelper dbHelper;
    private String[] allColumns = { BarcodeTable.COLUMN_ID,
            BarcodeTable.COLUMN_NAME, BarcodeTable.COLUMN_DATA
    };

    private BarcodeDataSource(Context context) {
        dbHelper = BarcodeDbHelper.newInstance(context);
    }

    public static BarcodeDataSource newInstance(Context context) {
        return new BarcodeDataSource(context);
    }

    public void open() throws SQLiteException {
        database = dbHelper.getWritableDatabase();
        Log.d("DEBUG", "Created database");
    }
    
    public void close() {
        dbHelper.close();
    }

    /**
     * Inserts barcode data and creates a new Barcode from the data
     *
     * @param name Name for Barcode
     * @param data Barcode data
     * @return new Barcode from name and data
     */
    public Barcode addBarcode(String name, String data) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BarcodeTable.COLUMN_NAME, name);
        contentValues.put(BarcodeTable.COLUMN_DATA, data);
        long insertId = database.insert(BarcodeTable.TABLE_BARCODE, null, contentValues);

        return Barcode.newInstance(insertId, name, data);
    }

    /**
     * Deletes barcode from database
     *
     * @param barcode Barcode to be deleted
     */
    public void deleteBarcode(Barcode barcode){
        database.delete(BarcodeTable.TABLE_BARCODE, BarcodeTable.COLUMN_ID + " = " + barcode.getId(), null);
    }


    /**
     * Search Database for Barcode by the barcode id
     *
     * @param id id of the barcode to find
     * @return Barcode with coresponding id
     */
    public Barcode getBarcodeById(long id) {
        Cursor cursor = database.query(BarcodeTable.TABLE_BARCODE, null, BarcodeTable.COLUMN_ID + "=?",
                new String[] { String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        Barcode barcode;
        try {
            barcode = Barcode.newInstance(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
        } catch (CursorIndexOutOfBoundsException e) {
            barcode = null;
        }
        cursor.close();
        return barcode;
    }

    /**
     * Get all the barcodes from the database
     *
     * @return ArrayList of all the barcodes
     */
    public List<Barcode> getAllBarcodes() {
        List<Barcode> barcodes = new ArrayList<Barcode>();
        Cursor cursor = database.query(BarcodeTable.TABLE_BARCODE, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            barcodes.add(Barcode.newInstance(cursor.getLong(0), cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();
        return barcodes;
    }


    /**
     * Delete all barcodes from the database
     */
    public void deleteAll() {
        List<Barcode> barcodes = getAllBarcodes();
        for (Barcode b: barcodes) {
            deleteBarcode(b);
        }
    }


}
