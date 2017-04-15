package com.example.erikgarcia.otm.CreateRestaurant;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.erikgarcia.otm.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class NewRestaurantDetails extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;
    ResTempSaveService tempSave = new ResTempSaveService();
    FragmentResDetails fSave = new FragmentResDetails();

    Button menuSwitch;

    TextView sunOpen;
    TextView monOpen;
    TextView tuesOpen;
    TextView wedOpen;
    TextView thuOpen;
    TextView friOpen;
    TextView satOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.textColor));

        Intent saveService = new Intent(this, ResTempSaveService.class);
        startService(saveService);

        Bundle extras = getIntent().getExtras();
        String getEditResDetails = "";
        if(extras != null) {
            getEditResDetails = extras.getString("Edit");
            tempSave.tempSave().editRes = getEditResDetails;
        }

        menuSwitch = (Button) findViewById(R.id.switchToMenu);

        loadFragment(new FragmentResDetails());
        menuSwitch.setText("Switch to Menu View");

        menuSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                menuSwitch.setFocusable(true);
                menuSwitch.requestFocusFromTouch();

                //Hide Keyboard when switching Tabs
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(
                        Context.INPUT_METHOD_SERVICE);

                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),
                        InputMethodManager.RESULT_UNCHANGED_SHOWN);

                switch (menuSwitch.getText().toString()){
                    case"Switch to Menu View" :
                        loadFragment(new FragmentResMenu());
                        menuSwitch.setText("Switch to Details View");
                        break;
                    case "Switch to Details View":
                        loadFragment(new FragmentResDetails());
                        menuSwitch.setText("Switch to Menu View");
                }

            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void loadFragment(android.support.v4.app.Fragment frag){
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.restaurantDetails, frag);
        ft.commit();
    }

    public void time(final View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(NewRestaurantDetails.this);
        View timePickerLayout = getLayoutInflater().inflate(R.layout.time_picker, null);
        builder.setView(timePickerLayout);

        final AlertDialog alert = builder.create();
        alert.show();

        final TimePicker time = (TimePicker) alert.findViewById(R.id.timePicker);
        final TextView textView = (TextView) v;

        if(textView.getId() == R.id.sunOpen
                || textView.getId() == R.id.monOpen
                || textView.getId() == R.id.tuesOpen
                || textView.getId() == R.id.wedOpen
                || textView.getId() == R.id.thuOpen
                || textView.getId() == R.id.friOpen
                || textView.getId() == R.id.satOpen) {

            time.setCurrentHour(0);
        } else {
            time.setCurrentHour(12);
        }

        time.setCurrentMinute(0);

        Button save = (Button) alert.findViewById(R.id.timePickerSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int h = time.getCurrentHour(), m = time.getCurrentMinute();
                int hour = h % 12;
                if (hour == 0) {
                    hour = 12;
                }
                textView.setText(String.format("%2d:%02d %s", hour == 0 ? 12 : hour,
                        m, h < 12 ? "am" : "pm"));

                switch (textView.getId()) {
                    case R.id.sunOpen:
                        textView.append("  -");
                        tempSave.tempSave().resTime[0] = textView.getText().toString();
                        Toast.makeText(getApplicationContext(), tempSave.tempSave().resTime[0], Toast.LENGTH_LONG).show();
                        break;
                    case R.id.monOpen:
                        textView.append("  -");
                        tempSave.tempSave().resTime[1] = textView.getText().toString();
                        break;
                    case R.id.tuesOpen:
                        textView.append("  -");
                        tempSave.tempSave().resTime[2] = textView.getText().toString();
                        break;
                    case R.id.wedOpen:
                        textView.append("  -");
                        tempSave.tempSave().resTime[3] = textView.getText().toString();
                        break;
                    case R.id.thuOpen:
                        textView.append("  -");
                        tempSave.tempSave().resTime[4] = textView.getText().toString();
                        break;
                    case R.id.friOpen:
                        textView.append("  -");
                        tempSave.tempSave().resTime[5] = textView.getText().toString();
                        break;
                    case R.id.satOpen:
                        textView.append("  -");
                        tempSave.tempSave().resTime[6] = textView.getText().toString();
                        break;

                    //===== Saving to Close =====
                    case R.id.sunClose:
                        tempSave.tempSave().resTime[7] = textView.getText().toString();
                        break;
                    case R.id.monClose:
                        tempSave.tempSave().resTime[8] = textView.getText().toString();
                        break;
                    case R.id.tuesClose:
                        tempSave.tempSave().resTime[9] = textView.getText().toString();
                        break;
                    case R.id.wedClose:
                        tempSave.tempSave().resTime[10] = textView.getText().toString();
                        break;
                    case R.id.thuClose:
                        tempSave.tempSave().resTime[11] = textView.getText().toString();
                        break;
                    case R.id.friClose:
                        tempSave.tempSave().resTime[12] = textView.getText().toString();
                        break;
                    case R.id.satClose:
                        tempSave.tempSave().resTime[13] = textView.getText().toString();
                }

                String setTime = h + ":" + m;
                if (setTime.equals("") || setTime.equals(null)) {
                    textView.setText("Closed");
                }

                switchText(v, textView);

                alert.dismiss();
            }
        });

        Button closed = (Button) alert.findViewById(R.id.timePickerClosed);
        closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("Closed");
                switchText(v, textView);
                alert.dismiss();

                switch(v.getId()){
                    case R.id.sunOpen:
                        tempSave.tempSave().resTime[0] = "Closed";
                        tempSave.tempSave().resTime[7] = "12:00 pm";
                        break;
                    case R.id.monOpen:
                        tempSave.tempSave().resTime[1] = "Closed";
                        tempSave.tempSave().resTime[8] = "12:00 pm";
                        break;
                    case R.id.tuesOpen:
                        tempSave.tempSave().resTime[2] = "Closed";
                        tempSave.tempSave().resTime[9] = "12:00 pm";
                        break;
                    case R.id.wedOpen:
                        tempSave.tempSave().resTime[3] = "Closed";
                        tempSave.tempSave().resTime[10] = "12:00 pm";
                        break;
                    case R.id.thuOpen:
                        tempSave.tempSave().resTime[4] = "Closed";
                        tempSave.tempSave().resTime[11] = "12:00 pm";
                        break;
                    case R.id.friOpen:
                        tempSave.tempSave().resTime[5] = "Closed";
                        tempSave.tempSave().resTime[12] = "12:00 pm";
                        break;
                    case R.id.satOpen:
                        tempSave.tempSave().resTime[6] = "Closed";
                        tempSave.tempSave().resTime[13] = "12:00 pm";
                }
            }
        });
    }

    public void switchText(View v, TextView t) {
        sunOpen = (TextView)v.findViewById(R.id.sunOpen);
        monOpen = (TextView)v.findViewById(R.id.monOpen);
        tuesOpen = (TextView)v.findViewById(R.id.tuesOpen);
        wedOpen = (TextView)v.findViewById(R.id.wedOpen);
        thuOpen = (TextView)v.findViewById(R.id.thuOpen);
        friOpen = (TextView)v.findViewById(R.id.friOpen);
        satOpen = (TextView)v.findViewById(R.id.satOpen);

        switch (v.getId()) {
            case R.id.sunOpen:
                if (t.getText().toString().equals("Closed")) {
                    findViewById(R.id.sunClose).setVisibility(View.GONE);
                }
                if (!t.getText().toString().equals("Closed")) {
                    findViewById(R.id.sunClose).setVisibility(View.VISIBLE);
                }
                //Save Values Here
                break;
            case R.id.sunClose:
                if (t.getText().toString().equals("Closed")) {
                    findViewById(R.id.sunClose).setVisibility(View.GONE);
                    t.setText("12:00 pm");
                    sunOpen.setText("Closed");
                }
                //Save Values Here
                break;
            case R.id.monOpen:
                if (t.getText().toString().equals("Closed")) {
                    findViewById(R.id.monClose).setVisibility(View.GONE);
                }
                if (!t.getText().toString().equals("Closed")) {
                    findViewById(R.id.monClose).setVisibility(View.VISIBLE);
                }
                //Save Values Here
                break;
            case R.id.monClose:
                if (t.getText().toString().equals("Closed")) {
                    findViewById(R.id.monClose).setVisibility(View.GONE);
                    t.setText("12:00 pm");
                    monOpen.setText("Closed");
                }
                //Save Values Here
                break;
            case R.id.tuesOpen:
                if (t.getText().toString().equals("Closed")) {
                    findViewById(R.id.tuesClose).setVisibility(View.GONE);
                }
                if (!t.getText().toString().equals("Closed")) {
                    findViewById(R.id.tuesClose).setVisibility(View.VISIBLE);
                }
                //Save Values Here
                break;
            case R.id.tuesClose:
                if (t.getText().toString().equals("Closed")) {
                    findViewById(R.id.tuesClose).setVisibility(View.GONE);
                    t.setText("12:00 pm");
                    tuesOpen.setText("Closed");
                }
                //Save Values Here
                break;
            case R.id.wedOpen:
                if (t.getText().toString().equals("Closed")) {
                    findViewById(R.id.wedClose).setVisibility(View.GONE);
                }
                if (!t.getText().toString().equals("Closed")) {
                    findViewById(R.id.wedClose).setVisibility(View.VISIBLE);
                }
                //Save Values Here
                break;
            case R.id.wedClose:
                if (t.getText().toString().equals("Closed")) {
                    findViewById(R.id.wedClose).setVisibility(View.GONE);
                    t.setText("12:00 pm");
                    wedOpen.setText("Closed");
                }
                //Save Values Here
                break;
            case R.id.thuOpen:
                if (t.getText().toString().equals("Closed")) {
                    findViewById(R.id.thuClose).setVisibility(View.GONE);
                }
                if (!t.getText().toString().equals("Closed")) {
                    findViewById(R.id.thuClose).setVisibility(View.VISIBLE);
                }
                //Save Values Here
                break;
            case R.id.thuClose:
                if (t.getText().toString().equals("Closed")) {
                    findViewById(R.id.thuClose).setVisibility(View.GONE);
                    t.setText("12:00 pm");
                    thuOpen.setText("Closed");
                }
                //Save Values Here
                break;
            case R.id.friOpen:
                if (t.getText().toString().equals("Closed")) {
                    findViewById(R.id.friClose).setVisibility(View.GONE);
                }
                if (!t.getText().toString().equals("Closed")) {
                    findViewById(R.id.friClose).setVisibility(View.VISIBLE);
                }
                //Save Values Here
                break;
            case R.id.friClose:
                if (t.getText().toString().equals("Closed")) {
                    findViewById(R.id.friClose).setVisibility(View.GONE);
                    t.setText("12:00 pm");
                    friOpen.setText("Closed");
                }
                //Save Values Here
                break;
            case R.id.satOpen:
                if (t.getText().toString().equals("Closed")) {
                    findViewById(R.id.satClose).setVisibility(View.GONE);
                }
                if (!t.getText().toString().equals("Closed")) {
                    findViewById(R.id.satClose).setVisibility(View.VISIBLE);
                }
                //Save Values Here
                break;
            case R.id.satClose:
                if (t.getText().toString().equals("Closed")) {
                    findViewById(R.id.satClose).setVisibility(View.GONE);
                    t.setText("12:00 pm");
                    satOpen.setText("Closed");
                }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        alert();

        return super.onOptionsItemSelected(item);
    }

    public void alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(NewRestaurantDetails.this);
        View alertView = getLayoutInflater().inflate(R.layout.delete_res_popup, null);
        builder.setView(alertView);
        final AlertDialog alert = builder.create();
        alert.show();

        Button yesDelete = (Button)alert.findViewById(R.id.yesDelete);
        Button noDelete = (Button)alert.findViewById(R.id.noDelete);
        TextView textView = (TextView)alert.findViewById(R.id.deleteResTextView);
        textView.setText("Are you sure you want to stop editing?\nAll changes will be deleted.");
        yesDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), RestaurantCreate.class);
                startActivity(i);

                tempSave.tempSave().clearCache = true;
                alert.dismiss();
            }
        });
        noDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("NewRestaurantDetails Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        AppIndex.AppIndexApi.start(mClient, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(mClient, getIndexApiAction());
        mClient.disconnect();
    }

    public void cancel(View v) {
        alert();

    }

    public void next(View v) {

        menuSwitch = (Button)findViewById(R.id.switchToMenu);

        //Hide Keyboard when switching Tabs
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);

        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);

        loadFragment(new FragmentResMenu());
        menuSwitch.setText("Switch to Details View");


    }

    @Override
    public void onBackPressed(){
        alert();
    }

}
