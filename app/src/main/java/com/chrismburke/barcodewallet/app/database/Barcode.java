package com.chrismburke.barcodewallet.app.database;



/**
 * Created by Chris on 4/20/2014.
 */
public class Barcode {

    private final long id;
    private final String name;
    private final String data;

    private Barcode(long id, String name, String data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public static Barcode newInstance(long id, String name, String data) {
        return new Barcode(id, name, data);
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public String getData() { return data; }

}
