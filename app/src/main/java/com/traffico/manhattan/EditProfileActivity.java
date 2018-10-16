package com.traffico.manhattan;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        TextView id = findViewById(R.id.tVId);
        id.setEnabled(false);

        MyOpenHelper dbHelper = new MyOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            Usuario usuario = new Usuario();
            usuario = dbHelper.getUsuario(db);
            id = findViewById(R.id.tVId);
            EditText name = findViewById(R.id.eTName);
            EditText lastName = findViewById(R.id.eTLastName);
            EditText address = findViewById(R.id.eTAddress);
            EditText location = findViewById(R.id.eTLocation);
            EditText email = findViewById(R.id.eTEMail);
            id.setText(usuario.getIdUsuario() + "");
            name.setText(usuario.getNombreUsuario());
            lastName.setText(usuario.getApellidoUsuario());
            address.setText(usuario.getDireccionUsuario());
            location.setText(usuario.getCoordenadasUsuario());
            email.setText(usuario.getEmailUsuario());
            if (!usuario.getFacebookUsuario().isEmpty()) {
                email.setEnabled(false);
            } else {
                email.setEnabled(true);
            }
        }
    }

    public void upDate(View view) {
        Usuario usuario = new Usuario();
        TextView id = findViewById(R.id.tVId);
        usuario.setIdUsuario((Integer.parseInt(id.getText().toString())));
        EditText name = findViewById(R.id.eTName);
        usuario.setNombreUsuario(name.getText().toString());
        EditText lastName = findViewById(R.id.eTName);
        usuario.setNombreUsuario(lastName.getText().toString());
        EditText address = findViewById(R.id.eTAddress);
        usuario.setNombreUsuario(address.getText().toString());
        EditText location = findViewById(R.id.eTLocation);
        usuario.setNombreUsuario(location.getText().toString());
        EditText email = findViewById(R.id.eTEMail);
        usuario.setNombreUsuario(email.getText().toString());

    }
}
