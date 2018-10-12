package com.traffico.manhattan;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.traffico.manhattan.classes.MyOpenHelper;
import com.traffico.manhattan.entities.Usuario;

public class EditProfileActivity extends AppCompatActivity {

    Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        //
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
            email.setEnabled(false);
        }
        //
    }
}
