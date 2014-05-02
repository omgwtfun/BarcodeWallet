package com.chrismburke.barcodewallet.app;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.chrismburke.barcodewallet.app.interfaces.BarcodeDataListener;
import com.chrismburke.barcodewallet.app.interfaces.FragmentActionListener;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class AddFragment extends Fragment implements View.OnClickListener{

    Button addButton;
    Button scanButton;

    EditText barcodeName;
    EditText barcodeData;

    BarcodeDataListener mDataListener;
    FragmentActionListener mFragmentActionListener;

    public static AddFragment newInstance() {
        AddFragment fragment = new AddFragment();

        return fragment;
    }
    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        addButton = (Button)view.findViewById(R.id.add);
        scanButton = (Button) view.findViewById(R.id.scan);

        addButton.setOnClickListener(this);
        scanButton.setOnClickListener(this);

        barcodeName = (EditText) view.findViewById(R.id.barcode_name);
        barcodeData = (EditText) view.findViewById(R.id.barcode_data);


        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mDataListener = (BarcodeDataListener) activity;
            mFragmentActionListener = (FragmentActionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().getClass().toString() +
            " must implement BarcodeDataListener");
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                try {
                    String name = barcodeName.getText().toString();
                    String data = barcodeData.getText().toString();
                    mDataListener.addBarcode(name, data);

                } catch(NullPointerException e) {

                }
                break;
            case R.id.scan:
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                startActivityForResult(intent, 0);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        String contents = intent.getStringExtra("SCAN_RESULT");
        String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
        Log.d("DEBUG", contents);
        Log.d("DEBUG", format);

        barcodeData.setText(contents);

    }
}
