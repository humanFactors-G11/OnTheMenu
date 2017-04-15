package com.example.erikgarcia.otm.ViewRestaurant;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.erikgarcia.otm.R;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Maverick on 3/31/2017.
 */

public class ViewResDetails extends AppCompatActivity {

    private GoogleApiClient mClient;
    Button menuSwitch;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.textColor));

        menuSwitch = (Button) findViewById(R.id.switchToMenu);

        loadFragment(new FragmentResDetailsView());
        menuSwitch.setText("Switch to Menu View");

        menuSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (menuSwitch.getText().toString()){
                    case"Switch to Menu View" :
                        loadFragment(new FragmentResMenuView());
                        menuSwitch.setText("Switch to Details View");
                        break;
                    case "Switch to Details View":
                        loadFragment(new FragmentResDetailsView());
                        menuSwitch.setText("Switch to Menu View");
                }

            }
        });

    }

    //Change to the Fragments for the View Menu
    private void loadFragment(android.support.v4.app.Fragment frag){
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.restaurantDetails, frag);
        ft.commit();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
