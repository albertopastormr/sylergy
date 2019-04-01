package com.example.sylergy.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.sylergy.R;
import com.example.sylergy.fragments.BarcodeProductFragment;
import com.example.sylergy.fragments.SearchFragment;
import com.example.sylergy.objects.SimpleIdlingResource;

public class MainActivity extends AppCompatActivity {
    public static android.content.Context context;
    public static SimpleIdlingResource mIdlingResource; // for espresso test
    private BottomNavigationView bottomNavigationView;
    private Fragment[] mFragments={new BarcodeProductFragment(),new SearchFragment()};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        loadFragment(new BarcodeProductFragment());
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_index:
                        loadFragment(mFragments[0]);
                        return true;
                    case R.id.navigation_search:
                        loadFragment(mFragments[1]);
                        return true;

                }
                return false;
            }
        });


    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    @VisibleForTesting
    public SimpleIdlingResource getIdlingResource() {

        if(mIdlingResource == null)
            mIdlingResource = new SimpleIdlingResource();

        return mIdlingResource;
    }
}
