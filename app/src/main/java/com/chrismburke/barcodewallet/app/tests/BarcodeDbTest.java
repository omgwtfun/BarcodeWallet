package com.chrismburke.barcodewallet.app.tests;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.chrismburke.barcodewallet.app.database.Barcode;
import com.chrismburke.barcodewallet.app.database.BarcodeDataSource;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Chris on 4/21/2014.
 */
public class BarcodeDbTest extends AndroidTestCase {
    private BarcodeDataSource mBarcodeDataSource;

    @Override
    protected void setUp() {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        mBarcodeDataSource = BarcodeDataSource.newInstance(context);
        mBarcodeDataSource.open();
    }

    public void testAddBarcode() {

        Barcode testBarcode = mBarcodeDataSource.addBarcode("Test", "1234");
        assertNotNull(testBarcode);
        assertEquals("Barcode Name Test", "Test", testBarcode.getName());

        assertEquals("Barcode Data Test", "1234", testBarcode.getData());

    }

    public void testGetBarcode() {
        Barcode testBarcode = mBarcodeDataSource.addBarcode("Test 2", "12345");
        Barcode barcode = mBarcodeDataSource.getBarcodeById(testBarcode.getId());
        assertEquals(testBarcode.getId(), barcode.getId());
        assertEquals(testBarcode.getName(), barcode.getName());
        assertEquals(testBarcode.getData(), barcode.getData());
    }

    public void testDeleteBarcode() {
        Barcode testBarcode = mBarcodeDataSource.addBarcode("Test3", "11235");
        mBarcodeDataSource.deleteBarcode(testBarcode);
        Barcode barcode = mBarcodeDataSource.getBarcodeById(testBarcode.getId());

        assertNull("Barcode Delete Test", barcode);

    }

    public void testGetAllBarcodes() {
        List<Barcode> testBarcodes = new ArrayList<Barcode>();
        testBarcodes.add(mBarcodeDataSource.addBarcode("test1", "1"));
        testBarcodes.add(mBarcodeDataSource.addBarcode("test2", "2"));
        testBarcodes.add(mBarcodeDataSource.addBarcode("test3", "3"));
        List<Barcode> barcodes = mBarcodeDataSource.getAllBarcodes();

        assertNotNull(barcodes);
        assertEquals(3, barcodes.size());
    }

    public void testDeleteAll() {
        List<Barcode> barcodes = new ArrayList<Barcode>();

        barcodes.add(mBarcodeDataSource.addBarcode("Test 1", "1"));
        barcodes.add(mBarcodeDataSource.addBarcode("Test 2", "2"));
        barcodes.add(mBarcodeDataSource.addBarcode("Test 3", "3"));

        mBarcodeDataSource.deleteAll();
        for(Barcode b : barcodes) {
            assertNull(mBarcodeDataSource.getBarcodeById(b.getId()));
        }
    }

    @Override
    protected void tearDown() {
        mBarcodeDataSource.close();
    }


}
