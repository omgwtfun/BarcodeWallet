package com.chrismburke.barcodewallet.app;

import android.app.Activity;
import android.app.ActionBar;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import com.chrismburke.barcodewallet.app.database.Barcode;
import com.chrismburke.barcodewallet.app.database.BarcodeDataSource;
import com.chrismburke.barcodewallet.app.interfaces.BarcodeDataListener;
import com.chrismburke.barcodewallet.app.interfaces.FragmentActionListener;

import java.util.List;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, BarcodeDataListener, FragmentActionListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private BarcodeDataSource mBarcodeDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    protected void onResume() {
        super.onResume();
        mBarcodeDataSource = BarcodeDataSource.newInstance(this);
        mBarcodeDataSource.open();

        Log.d("DEBUG", "onResume()");

    }
    @Override
    protected void onPause() {
        super.onPause();
        //TODO: Remove the delete all when real add happens

        mBarcodeDataSource.close();

        Log.d("DEBUG", "onPause()");
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentType type;
        switch(position) {
            case 0:
                Log.d("DEBUG", "Case: 0, Home Framgnet");
                type = FragmentType.HomeFragment;
                break;
            case 1:
                Log.d("DEBUG", "Case: 1, Barcode Fragment");
                type = FragmentType.BarcodeFragment;

                break;
            case 2:
                Log.d("DEBUG", "Case: 2, Add Framgnet");
                type = FragmentType.AddFragment;
                break;
            default:
                type = null;
        }
        switchFragment(type);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public List<Barcode> getAllBarcodes() { return mBarcodeDataSource.getAllBarcodes(); }

    @Override
    public void addBarcode(String name, String data) { mBarcodeDataSource.addBarcode(name, data); }

    @Override
    public void deleteBarcode(Barcode barcode) { mBarcodeDataSource.deleteBarcode(barcode); }

    @Override
    public Barcode getBarcode(long id) { return mBarcodeDataSource.getBarcodeById(id); }

    @Override
    public void changeFragment(FragmentType fragmentType) {
        switchFragment(fragmentType);
    }

    public void switchFragment(FragmentType type) {
        FragmentManager fragmentManager = getFragmentManager();
        switch(type) {
            case HomeFragment:
                fragmentManager.beginTransaction().replace(R.id.container, HomeFragment.newInstance()).commit();
                break;
            case BarcodeFragment:
                fragmentManager.beginTransaction().replace(R.id.container, BarcodeFragment.newInstance()).commit();
                break;
            case AddFragment:
                fragmentManager.beginTransaction().replace(R.id.container, AddFragment.newInstance()).commit();
                break;
            default:
                throw new IllegalArgumentException("Unknown fragment type");
        }
    }
 }
