package com.chrismburke.barcodewallet.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chrismburke.barcodewallet.app.database.Barcode;

import java.util.List;

/**
 * Created by Chris on 4/18/2014.
 */
public class BarcodeAdapter extends ArrayAdapter<Barcode> {
    private Context context;
    private List<Barcode> barcodes;
    private TextView mTextView;

    public BarcodeAdapter(Context context, List<Barcode> objects) {
        super(context, R.layout.barcode_layout, objects);
        this.context = context;
        this.barcodes = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Barcode barcode = barcodes.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.barcode_layout, null);
        }

        mTextView = (TextView) convertView.findViewById(R.id.barcode_text);
        mTextView.setText(barcode.getName());

        return convertView;
    }
}
