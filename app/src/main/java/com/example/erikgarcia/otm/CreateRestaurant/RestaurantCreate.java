package com.example.erikgarcia.otm.CreateRestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.erikgarcia.otm.MainActivity;
import com.example.erikgarcia.otm.R;

public class RestaurantCreate extends AppCompatActivity {

    String msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        toolbar.setTitleTextColor(getResources().getColor(R.color.textColor));
        actionBar.setTitle(R.string.app_name);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_plus_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), NewRestaurantDetails.class);
                startActivity(i);

            }
        });

        //Rap addRestaurant(resName) in a For loop [for(int i=0; i<(restaurant dir of this user files).length; i++)
        addRestaurant("Restaurant Name"); //<-- Replace Text with future Restaurant Names

        //Example
        String[] restaurants = {"Restaurant, San Antonio", "Restaurant, Houston", "Restaurant, Dallas"};
        for(int i=0;i<restaurants.length;i++){
            addRestaurant(restaurants[i]);
        }

    }

    public void addRestaurant(String name){
        //Test Restaurant
        final LinearLayout ll = (LinearLayout)findViewById(R.id.createResContainer);
        final View v = getLayoutInflater().inflate(R.layout.restaurant_list_layout, null); //Setting Layout View
        ll.addView(v); //Adding the View to the Container
        //Grabbing Buttons for Layout
        final TextView resName = (TextView)v.findViewById(R.id.resNameTextView);
        resName.setText(name);//Setting Restaurant Name
        Button edit = (Button)v.findViewById(R.id.resEditButton);
        Button delete = (Button)v.findViewById(R.id.resDeleteButton);
        //On Button Clicks
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Handle Editing Restaurant Here
                Intent i = new Intent(getApplicationContext(), NewRestaurantDetails.class);
                //test
                String editString = "~Details\n"+resName.getText().toString()+"\nItalian\n<Time\n2:00 am - 2:00 pm\nClosed\n8:30 am - 12:00 pm\n12:00 am - 12:00 pm\n";
                i.putExtra("Edit", editString); //test
                startActivity(i);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RestaurantCreate.this);
                View alertView = getLayoutInflater().inflate(R.layout.delete_res_popup, null);
                builder.setView(alertView);
                final AlertDialog alert = builder.create();
                alert.show();

                Button yesDelete = (Button)alert.findViewById(R.id.yesDelete);
                Button noDelete = (Button)alert.findViewById(R.id.noDelete);
                TextView textView = (TextView)alert.findViewById(R.id.deleteResTextView);
                textView.setText("Are You Sure You Want To Delete "+resName.getText().toString()+"?");

                yesDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ll.removeView(v);
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
        });
    }

    public void logOut(View v){
        logOutHandler();
    }

    public void logOutHandler(){
        AlertDialog.Builder builder = new AlertDialog.Builder(RestaurantCreate.this);
        View alertView = getLayoutInflater().inflate(R.layout.delete_res_popup, null);
        builder.setView(alertView);
        final AlertDialog alert = builder.create();
        alert.show();

        Button yesDelete = (Button)alert.findViewById(R.id.yesDelete);
        Button noDelete = (Button)alert.findViewById(R.id.noDelete);
        TextView textView = (TextView)alert.findViewById(R.id.deleteResTextView);
        textView.setText("Log Out?");

        yesDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add Logout function here

                Intent i = new Intent(RestaurantCreate.this, MainActivity.class);
                startActivity(i);
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

    @Override
    public void onBackPressed(){
        logOutHandler();

    }

}
