package com.traffico.manhattan;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
//
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.traffico.manhattan.classes.MyOpenHelper;

public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager = CallbackManager.Factory.create();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        //
        LoginButton loginButton = (LoginButton)

                findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // If using in a fragment
        loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()

        {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
        //
        //
        MyOpenHelper dbHelper = new MyOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        boolean flagUserExists = false;
        if (db != null) {
            flagUserExists = dbHelper.searchUser(db);
            if (flagUserExists == true) {
                Button button = findViewById(R.id.bSingIn);
                button.setEnabled(false);
                //Ir al menu inicial
            }
        }
    }

    public void register(View view) {
        TextView tVName = findViewById(R.id.tVName);
        TextView tVLastName = findViewById(R.id.tVLastName);
        TextView tVEMail = findViewById(R.id.tVEMail);
        //
        EditText eTName = findViewById(R.id.eTName);
        EditText eTLastName = findViewById(R.id.eTLastName);
        EditText eTAddress = findViewById(R.id.eTAddress);
        EditText eTLocation = findViewById(R.id.eTLocation);
        EditText eTEMail = findViewById(R.id.eTEMail);
        if (eTName.getText().toString().isEmpty() && eTLastName.getText().toString().isEmpty() && eTEMail.getText().toString().isEmpty()) {
            tVName.setTextColor(Color.rgb(200, 0, 0));
            tVLastName.setTextColor(Color.rgb(200, 0, 0));
            tVEMail.setTextColor(Color.rgb(200, 0, 0));
        } else {
            MyOpenHelper dbHelper = new MyOpenHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db != null) {
                dbHelper.insertUser(db, eTName, eTLastName, eTAddress, eTLocation, eTEMail);
            }

        }
    }
}
