package com.example.erikgarcia.otm;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erikgarcia.otm.SignupLogin.Login;
import com.example.erikgarcia.otm.SignupLogin.SignupActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Spinner resType;
    EditText zipCode;
    private Button  searchButton;
    private TextView advancedSearch;
    SharedPreferences saveSearch;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Requesting Permissions
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                2);

        resType = (Spinner) findViewById(R.id.res_type);
        searchButton = (Button) findViewById(R.id.search_Button);
        zipCode = (EditText) findViewById(R.id.zip_code);
        advancedSearch = (TextView)findViewById(R.id.advancedSearch);
        advancedSearch.setPaintFlags(advancedSearch.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);


        setUpRestaurantSpinner();

        LinearLayout ll = (LinearLayout)findViewById(R.id.advancedSearchContainer);
        final EditText keywordSearch = (EditText)findViewById(R.id.keywordSearch);

        saveSearch = getSharedPreferences("search", Context.MODE_PRIVATE);
        editor = saveSearch.edit();
        if(ll.getVisibility() == View.GONE) editor.remove("keyword").commit();

        zipCode.setText(saveSearch.getString("zip", null), TextView.BufferType.EDITABLE);
        zipCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                editor.putString("zip", zipCode.getText().toString()).apply();
                return false;
            }
        });
        //Saving Restaurant Type when Selecting
        resType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                editor.putString("resType", resType.getSelectedItem().toString()).apply(); //Save Selected Item
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //Putting Variable into SharedPreference and clearing it when Advanced Search is Closed
            keywordSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    editor.putString("keyword", keywordSearch.getText().toString()).apply();
                    return false;
                }
            });

    }

    //Creates and populates the drop down menu
    public void setUpRestaurantSpinner() {

        //Add More Restaurant Types in Res>Values>Strings>@String-Array"res_type"
        ArrayList<String> typeOptions = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.res_type)));
        typeOptions.add(0, "All");

        //Setting Default Restaurant Type From Last Save
        saveSearch = getSharedPreferences("search", Context.MODE_PRIVATE);
        String defaultSpinner = saveSearch.getString("resType", "All");
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, typeOptions);
        resType.setAdapter(adapter);
        if (!defaultSpinner.equals(null)) {
            int spinnerPosition = adapter.getPosition(defaultSpinner);
            resType.setSelection(spinnerPosition);
        }
    }

    //Create the Advanced Search Options
    public void advancedSearch(View v){
        LinearLayout ll = (LinearLayout)findViewById(R.id.advancedSearchContainer);
        ImageView img = (ImageView)findViewById(R.id.advancedSearchArrow);

        //Removing SharedPreference when advanced search is Closed
        saveSearch = getSharedPreferences("search", Context.MODE_PRIVATE);
        editor = saveSearch.edit();
        if(ll.getVisibility() == View.VISIBLE) editor.remove("keyword").commit();

        switch (ll.getVisibility()){
            case View.GONE:
                ll.setVisibility(View.VISIBLE);
                img.setImageResource(R.drawable.arrow_up);
                break;
            case View.VISIBLE:
                ll.setVisibility(View.GONE);
                img.setImageResource(R.drawable.arrow_dn);

        }
    }

    //Starts the Login screen when "Sign in as Owner" button is pressed.
    public void signInAsOwner(View v) {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

    //Starts the sign up activity when "Sign up as an Owner" button is pressed.
    public void signUpAsOwner (View v) {
        Intent intent = new Intent(this,SignupActivity.class);
        startActivity(intent);
    }

    //Search Button reads in the zip and restaurant type
    //TODO create the restaurant search display activity and
    public void search(View v) {

        if(zipCode.getText().toString().equals("") || zipCode.getText().toString().equals(null)){
            Toast.makeText(MainActivity.this, "Please Enter a City Or Zip", Toast.LENGTH_LONG).show();
        } else {
            Intent i = new Intent(this, Search.class);
            startActivity(i);
        }
    }

    //Requesting Permission Method
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }

            case 2: {

                if(grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                } else {

                }
                return;
            }
        }
    }

}
