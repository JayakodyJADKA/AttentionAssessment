package com.anuththara18.attentionassessment.home;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.anuththara18.attentionassessment.R;
import com.anuththara18.attentionassessment.consentform.ConsentFormActivity;
import com.google.android.material.navigation.NavigationView;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    ImageView i;

    //FOR FRAGMENTS
    private Fragment fragmentMainDashboard;

    //FOR DATAS
    private static final int FRAGMENT_MAINDASHBOARD = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        // Configure all views
        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();

        // Show First Fragment
        this.showFirstFragment();

    }

    @Override
    public void onBackPressed() {
        //Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //Handle Navigation Item Click
        int id = item.getItemId();

        switch (id){
            case R.id.dashboard:
                this.showFragment(FRAGMENT_MAINDASHBOARD);
                break;
            case R.id.parents:
                Intent intent1 = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                startActivity(intent1);
                break;
            case R.id.healthProfessional:
                Intent intent2 = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                startActivity(intent2);
                break;
            case R.id.doctor:
                Intent intent3 = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                startActivity(intent3);
                break;
            case R.id.admin:
                Intent intent4 = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                startActivity(intent4);
                break;
            case R.id.termsAndConditions:
                Intent intent6 = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                startActivity(intent6);
                break;
            case R.id.consentForm:
                Intent intent7 = new Intent(getApplicationContext(), ConsentFormActivity.class);
                startActivity(intent7);
                break;
            case R.id.aboutUs:
                Intent intent8 = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                startActivity(intent8);
                break;
            case R.id.logOut:
                logout();
                break;
            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    private void logout() {

        //closing activity
        finish();

        //starting login activity
        startActivity(new Intent(getApplicationContext(), MainFragment.class));

    }

    // ---------------------
    // CONFIGURATION
    // ---------------------

    // Configure Toolbar
    private void configureToolBar(){
        this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    //Configure Drawer Layout
    private void configureDrawerLayout(){
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    //Configure NavigationView
    private void configureNavigationView(){
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // ---------------------
    // FRAGMENTS
    // ---------------------

    // Show first fragment when activity is created
    private void showFirstFragment(){
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);
        if (visibleFragment == null){
            // Show main dashboard Fragment
            this.showFragment(FRAGMENT_MAINDASHBOARD);
            // Mark as selected the menu item corresponding to NewsFragment
            this.navigationView.getMenu().getItem(0).setChecked(true);
        }
    }

    // Show fragment according an Identifier

    private void showFragment(int fragmentIdentifier){
        switch (fragmentIdentifier){
            case FRAGMENT_MAINDASHBOARD :
                this.showMainDashboardFragment();
                break;
                /*
            case FRAGMENT_CATEGORIES :
                this.showCategoriesFragment();
                break;
            case FRAGMENT_SMSINTEGRATION:
                this.showSMSIntegrationFragment();
                break;
            case FRAGMENT_PAYABLESANDRECEIVABLES:
                this.showPayablesAndReceivablesFragment();
                break;

                 */
            default:
                break;
        }
    }

    // ---

    // Create each fragment page and show it

    private void showMainDashboardFragment(){
        if (this.fragmentMainDashboard == null) this.fragmentMainDashboard = MainFragment.newInstance();
        this.startTransactionFragment(this.fragmentMainDashboard);
    }


    // ---

    // Generic method that will replace and show a fragment inside the MainActivity Frame Layout
    private void startTransactionFragment(Fragment fragment){
        if (!fragment.isVisible()){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_frame_layout, fragment).commit();
        }
    }
}