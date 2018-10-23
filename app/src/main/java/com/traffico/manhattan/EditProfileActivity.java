package com.traffico.manhattan;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.traffico.manhattan.classes.MyOpenHelper;
import com.traffico.manhattan.entities.Usuario;

public class EditProfileActivity extends AppCompatActivity {

    Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        MyOpenHelper dbHelper = new MyOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            Usuario usuario = new Usuario();
            usuario = dbHelper.getUsuario(db);
            EditText name = findViewById(R.id.eTName);
            EditText lastName = findViewById(R.id.eTLastName);
            EditText address = findViewById(R.id.eTAddress);
            EditText location = findViewById(R.id.eTLocation);
            EditText email = findViewById(R.id.eTEMail);
            name.setText(usuario.getNombreUsuario());
            lastName.setText(usuario.getApellidoUsuario());
            address.setText(usuario.getDireccionUsuario());
            location.setText(usuario.getCoordenadasUsuario());
            email.setText(usuario.getEmailUsuario());
            if (usuario.getFacebookUsuario() != null) {
                email.setEnabled(false);
            } else {
                email.setEnabled(true);
            }
        }
    }

    public void upDate(View view) {
        Usuario usuario = new Usuario();
        EditText name = findViewById(R.id.eTName);
        usuario.setNombreUsuario(name.getText().toString());
        EditText lastName = findViewById(R.id.eTName);
        usuario.setApellidoUsuario(lastName.getText().toString());
        EditText address = findViewById(R.id.eTAddress);
        usuario.setDireccionUsuario(address.getText().toString());
        EditText location = findViewById(R.id.eTLocation);
        usuario.setCoordenadasUsuario(location.getText().toString());
        EditText email = findViewById(R.id.eTEMail);
        usuario.setEmailUsuario(email.getText().toString());
        //
        MyOpenHelper dbHelper = new MyOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            dbHelper.updateUser(db, usuario);
            Toast toast = Toast.makeText(getApplicationContext(), R.string.ua, Toast.LENGTH_SHORT);
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
        }

    }
}
