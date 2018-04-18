package com.anotherdgf.deviceinfo.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.anotherdgf.deviceinfo.R;
import com.anotherdgf.deviceinfo.fragment.DeviceInfoFragment;
import com.anotherdgf.deviceinfo.fragment.SettingsFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        initView();
        initFragmentView();
    }

    private void initView(){
        toolbar =findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    private void initFragmentView(){
        toolbar.setTitle(R.string.nav_devices);
        addFragment(DeviceInfoFragment.newInstance());
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
           // case R.id.nav_header:
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_settings:
                toolbar.setTitle(R.string.nav_settings);
                addFragment(SettingsFragment.newInstance());
                break;
            case R.id.nav_deviceinfo:
                toolbar.setTitle(R.string.nav_devices);
                addFragment(DeviceInfoFragment.newInstance());
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.fragment_container;
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}