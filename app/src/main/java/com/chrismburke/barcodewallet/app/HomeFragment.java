package com.chrismburke.barcodewallet.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chrismburke.barcodewallet.app.interfaces.FragmentActionListener;

public class HomeFragment extends Fragment implements View.OnClickListener{


    private FragmentActionListener mListener;

    private Button showBarcodes;
    private Button addBarcodes;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showBarcodes = (Button) view.findViewById(R.id.button_display);
        addBarcodes = (Button) view.findViewById(R.id.button_add);

        showBarcodes.setOnClickListener(this);
        addBarcodes.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (FragmentActionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FragmentActionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.button_add:
                mListener.changeFragment(FragmentActionListener.FragmentType.AddFragment);
                break;
            case R.id.button_display:
                mListener.changeFragment(FragmentActionListener.FragmentType.BarcodeFragment);
                break;
            default:
                break;
        }
    }


}
