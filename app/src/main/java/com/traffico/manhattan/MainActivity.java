package com.traffico.manhattan;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
//
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.traffico.manhattan.classes.MyOpenHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    //
    String userIdFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        loginWithFacebook();
        //
        MyOpenHelper dbHelper = new MyOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        boolean flagUserExists = false;
        if (db != null) {
            flagUserExists = dbHelper.searchUser(db);
            if (flagUserExists == true) {
                Button button = findViewById(R.id.bSingIn);
                button.setEnabled(false);
                Intent menu = new Intent(this, MenuActivity.class);
                startActivity(menu);
            }
        }
    }

    private void loginWithFacebook() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        //
        callbackManager = CallbackManager.Factory.create();

        //
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");


        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.i("onSuccess", "UserId: " + loginResult.getAccessToken().getUserId());
                Log.i("onSuccess", "Token: " + loginResult.getAccessToken().getToken());
                Log.i("onSuccess", "Recently Granted Permissions " + loginResult.getRecentlyGrantedPermissions());
                setFacebookData(loginResult);
            }

            @Override
            public void onCancel() {
                // App code
                Log.i("onCancel", "Cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.i("onError", "Error" + exception.getMessage());
            }
            //
            //
            private void setFacebookData(final LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                // Application code
                                try {
                                    Log.i("Response", response.toString());

                                    String email = response.getJSONObject().getString("email");
                                    String firstName = response.getJSONObject().getString("first_name");
                                    String lastName = response.getJSONObject().getString("last_name");

                                    Profile profile = Profile.getCurrentProfile();
                                    String id = profile.getId();
                                    String link = profile.getLinkUri().toString();
                                    Log.i("Link", link);
                                    if (Profile.getCurrentProfile() != null) {
                                        Log.i("Login", "ProfilePic" + Profile.getCurrentProfile().getProfilePictureUri(200, 200));
                                    }
                                    Log.i("Login" + "Email", email);
                                    EditText eTEMail = findViewById(R.id.eTEMail);
                                    eTEMail.setText(email);
                                    Log.i("Login" + "FirstName", firstName);
                                    EditText eTName = findViewById(R.id.eTName);
                                    eTName.setText(firstName);
                                    Log.i("Login" + "LastName", lastName);
                                    EditText eTLastName = findViewById(R.id.eTLastName);
                                    eTLastName.setText(lastName);
                                    Button singIn = findViewById(R.id.bSingIn);
                                    userIdFacebook = loginResult.getAccessToken().getUserId().toString();
                                    singIn.callOnClick();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,email,first_name,last_name");
                request.setParameters(parameters);
                request.executeAsync();
                //
            }
            //
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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
                dbHelper.insertUser(db, eTName, eTLastName, eTAddress, eTLocation, eTEMail, userIdFacebook);
                Toast toast = Toast.makeText(getApplicationContext(), "Usuario Registrado", Toast.LENGTH_SHORT);
                toast.show();
                final Intent mainActivity = new Intent(this, MainActivity.class);
                //
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Magic here
                        startActivity(mainActivity);
                    }
                }, 1000); // Millisecond 1000 = 1 sec
                //
            }

        }
    }
}
