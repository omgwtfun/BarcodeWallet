package com.chrismburke.barcodewallet.app;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.chrismburke.barcodewallet.app.database.Barcode;
import com.chrismburke.barcodewallet.app.interfaces.BarcodeDataListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A fragment representing a list of Items.
 * <p />
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p />
 * Activities containing this fragment MUST implement the
 * interface.
 */
public class BarcodeFragment extends Fragment implements AbsListView.OnItemClickListener {


    private BarcodeDataListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private BarcodeAdapter mAdapter;

    public static BarcodeFragment newInstance(Bundle bundle) {
        BarcodeFragment mFragment = new BarcodeFragment();
        mFragment.setArguments(bundle);
        return mFragment;
    }

    public static BarcodeFragment newInstance(String[] barcodeNames) {
        BarcodeFragment mFragment = new BarcodeFragment();
        Bundle tempBundle = new Bundle();
        tempBundle.putStringArray("NAMES", barcodeNames);
        mFragment.setArguments(tempBundle);
        return mFragment;
    }

    public static BarcodeFragment newInstance() {
        return new BarcodeFragment();
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BarcodeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
         //   savedInstanceState.get
        }

     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_barcode, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        mListView.setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (BarcodeDataListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement BarcodeDataListner");
        }
        List<Barcode> barcodes = mListener.getAllBarcodes();
        mAdapter = new BarcodeAdapter(getActivity(), barcodes);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            //mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
        }

        Log.d("DEBUG", String.valueOf(position));
    }
}
