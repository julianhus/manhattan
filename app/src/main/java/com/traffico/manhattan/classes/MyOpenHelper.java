package com.traffico.manhattan.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.traffico.manhattan.MainActivity;
import com.traffico.manhattan.R;
import com.traffico.manhattan.entities.Departamento;
import com.traffico.manhattan.entities.Municipio;
import com.traffico.manhattan.entities.Tienda;
import com.traffico.manhattan.entities.Usuario;
import com.traffico.manhattan.interfaces.StringsCreacion;

import java.util.ArrayList;

public class MyOpenHelper extends SQLiteOpenHelper implements StringsCreacion {

    private static final String DB_NAME = "manhattan.sqlite";
    private static final int DB_VERSION = 1;
    //
    private static final String QRY_SEARCH_USER = "select * from usuario";
    private static final String QRY_INSERT_USER = "insert into usuario (?,?,?,?,?,?,?)";
    private static final String QRY_SEARCH_STORE = "select * from tienda t left outer join municipio m on m.id_municipio = t.id_municipio left outer join departamento d on d.id_departamento = m.id_departamento";

    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DEPARTAMENTO_TABLE_CREATE);
        db.execSQL(MUNICIPIO_TABLE_CREATE);
        db.execSQL(MUNICIPIO_INDEX_CREATE);
        db.execSQL(TIENDA_TABLE_CREATE);
        db.execSQL(TIENDA_INDEX_CREATE);
        db.execSQL(PRODUCTO_TABLE_CREATE);
        db.execSQL(PRODUCTO_INDEX_UNIQUE_CREATE);
        db.execSQL(PRODUCTO_INDEX_CREATE);
        db.execSQL(USUARIO_TABLE_CREATE);
        db.execSQL(USUARIO_UNIUQE_EMAIL);
        db.execSQL(MERCADO_TABLE_CREATE);
        db.execSQL(MERCADO_INDEX_CREATE_PRODUCTO);
        db.execSQL(MERCADO_INDEX_CREATE_USUARIO);
        db.execSQL(MERCADO_INDEX_UNIQUE_CREATE_USUARIO);
        db.execSQL(MERCADO_INDEX_UNIQUE_CREATE_PRODUCTO);
        db.execSQL(VALOR_PRODUCTO_TABLE_CREATE);
        db.execSQL(MERCADO_INDEX_CREATE_VALOR_PRODUCTO);
        db.execSQL(CARGA_DEPARTAMENTOS);
        db.execSQL(CARGA_MUNICIPIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean searchUser(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery(QRY_SEARCH_USER, null);
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void insertUser(SQLiteDatabase db, EditText eTName, EditText eTLastName, EditText eTAddress, EditText eTLocation, EditText eTEMail, String userIdFacebook) {
        Usuario usuario = new Usuario(eTName.getText().toString(), eTLastName.getText().toString(), eTAddress.getText().toString(), eTLocation.getText().toString(), eTEMail.getText().toString(), userIdFacebook, null);
        ContentValues cv = new ContentValues();
        cv.put("nombre_usuario", usuario.getNombreUsuario());
        cv.put("apellido_usuario", usuario.getApellidoUsuario());
        cv.put("direccion_usuario", usuario.getDireccionUsuario());
        cv.put("coordenadas_usuario", usuario.getCoordenadasUsuario());
        cv.put("e_mail_usuario", usuario.getEmailUsuario());
        cv.put("facebook", usuario.getFacebookUsuario());
        cv.put("google", usuario.getGoogleUsuario());
        db.insert("usuario", null, cv);

    }

    public Usuario getUsuario(SQLiteDatabase db) {
        Usuario usuario = new Usuario();
        Cursor cursor = db.rawQuery(QRY_SEARCH_STORE, null);
        while (cursor.moveToNext()) {
            usuario.setIdUsuario(cursor.getInt(0));
            usuario.setNombreUsuario(cursor.getString(1));
            usuario.setApellidoUsuario(cursor.getString(2));
            usuario.setDireccionUsuario(cursor.getString(3));
            usuario.setCoordenadasUsuario(cursor.getString(4));
            usuario.setEmailUsuario(cursor.getString(5));
            usuario.setFacebookUsuario(cursor.getString(6));
            usuario.setGoogleUsuario(cursor.getString(7));
            cursor.moveToLast();
        }
        return usuario;
    }


    public void updateUser(SQLiteDatabase db, Usuario usuario) {
        ContentValues cv = new ContentValues();
        cv.put("nombre_usuario", usuario.getNombreUsuario());
        cv.put("apellido_usuario", usuario.getApellidoUsuario());
        cv.put("direccion_usuario", usuario.getDireccionUsuario());
        cv.put("coordenadas_usuario", usuario.getCoordenadasUsuario());
        db.update("usuario", cv, "e_mail_usuario = '" + usuario.getEmailUsuario() + "'", null);
    }

    public ArrayList<Tienda> getTiendas(SQLiteDatabase db) {
        ArrayList<Tienda> tiendas = new ArrayList<Tienda>();
        Cursor cursor = db.rawQuery(QRY_SEARCH_STORE, null);
        while (cursor.moveToNext()) {
            Tienda tienda = new Tienda();
            Municipio municipio = new Municipio();
            Departamento departamento = new Departamento();
            tienda.setIdTienda(cursor.getInt(0));
            tienda.setDescTienda(cursor.getString(1));
            tienda.setDireccionTienda(cursor.getString(2));
            tienda.setCoordenadasTienda(cursor.getString(3));
            municipio.setIdMunicipio(cursor.getInt(4));
            municipio.setDescMunicipio(cursor.getString(5));
            departamento.setIdDepartamento(cursor.getInt(6));
            departamento.setDescDepartamento(cursor.getString(7));
            municipio.setDepartamento(departamento);
            tienda.setMunicipio(municipio);
            tiendas.add(tienda);
        }
        return tiendas;
    }
}
