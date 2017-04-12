package com.example.erikgarcia.otm.SignupLogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erikgarcia.otm.R;
import com.example.erikgarcia.otm.CreateRestaurant.RestaurantCreate;

public class Login extends AppCompatActivity {

    private EditText usernameEntry;
    private EditText passwordEntry;
    private Button   nextButton;
    SharedPreferences saveUser;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.textColor));

        usernameEntry = (EditText) findViewById(R.id.username_input);
        passwordEntry = (EditText) findViewById(R.id.password_input);
        nextButton = (Button) findViewById(R.id.next_button);

        //Save last Log In Info (Username)
        saveUser = getSharedPreferences("User", Context.MODE_PRIVATE);
        usernameEntry.setText(saveUser.getString("username", null), TextView.BufferType.EDITABLE);
    }

    /*
       Grabs the username and password and stores them into these variables
       when the next button is pressed. haven't done anything with them though
    */
    public void readInput(View v) {
        Intent i = new Intent(this, RestaurantCreate.class);
        final String username = usernameEntry.getText().toString().toLowerCase();
        String password = passwordEntry.getText().toString();
        saveUser = getSharedPreferences("User", Context.MODE_PRIVATE);
        editor = saveUser.edit();

        //Change to check file for Values
        String checkUser = "admin", checkPass = "Password";

        //If User does not provide a username or password
        if(username.equals("") || username.equals(null) || password.equals("") || password.equals(null)) {
            if (username.equals("") && password.equals("") || username.equals(null) && password.equals(null)){
                Toast.makeText(this, "Please Enter a Username & Password", Toast.LENGTH_LONG).show();
            }else if(username.equals("") || username.equals(null)){
                Toast.makeText(this, "Please Enter a Username", Toast.LENGTH_LONG).show();
            } else if (password.equals("") || password.equals(null)){
                Toast.makeText(this, "Please Enter a Password", Toast.LENGTH_LONG).show();
            }
        }

        //Checking if username and password match file
        if(!username.equals("") && !password.equals("")) {
            if (username.equals(checkUser) && password.equals(checkPass)) {
                usernameEntry.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        editor.putString("username", usernameEntry.getText().toString()).apply();
                        return false;
                    }
                });
                startActivity(i);
            } else if (!username.equals(checkUser)) {
                Toast.makeText(this, "Username Not Found", Toast.LENGTH_LONG).show();
            } else if (!username.equals(checkUser) || !password.equals(checkPass)) {
                if (!username.equals(checkUser)) {
                    Toast.makeText(this, "Username Not Found", Toast.LENGTH_LONG).show();
                }
                if (!password.equals(checkPass)) {
                    Toast.makeText(this, "Password Incorrect", Toast.LENGTH_LONG).show();
                }
            }
        }

        //By Pass Log In for Testing Purposes
        /*if(username.equals("Admin") && password.equals("Password")){
            startActivity(i);
        }*/
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
