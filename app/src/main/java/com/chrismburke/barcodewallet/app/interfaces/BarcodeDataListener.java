package com.chrismburke.barcodewallet.app.interfaces;

import com.chrismburke.barcodewallet.app.database.Barcode;

import java.util.List;

/**
 * Created by Chris on 4/18/2014.
 */
public interface BarcodeDataListener {
    public final String TAG = "BARCODE_HANDLER";
    public List<Barcode> getAllBarcodes();
    public void addBarcode(String name, String data);
    public void deleteBarcode(Barcode barcode);
    public Barcode getBarcode(long id);
}
