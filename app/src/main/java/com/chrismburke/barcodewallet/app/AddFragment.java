package com.chrismburke.barcodewallet.app;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class AddFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_add, container, false);
    }


}
