package com.chrismburke.barcodewallet.app.interfaces;

/**
 * Created by Chris on 4/27/2014.
 */
public interface FragmentActionListener {
    public enum FragmentType { HomeFragment, AddFragment, BarcodeFragment }

    public void changeFragment(FragmentType fragmentType);
}
