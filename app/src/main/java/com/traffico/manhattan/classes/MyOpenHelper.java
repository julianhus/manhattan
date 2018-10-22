package com.traffico.manhattan.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import com.traffico.manhattan.entities.Departamento;
import com.traffico.manhattan.entities.Municipio;
import com.traffico.manhattan.entities.Producto;
import com.traffico.manhattan.entities.Tienda;
import com.traffico.manhattan.entities.Usuario;
import com.traffico.manhattan.interfaces.StringsCreacion;

import java.util.ArrayList;

public class MyOpenHelper extends SQLiteOpenHelper implements StringsCreacion {

    private static final String DB_NAME = "manhattan.sqlite";
    private static final int DB_VERSION = 1;
    //
    private static final String QRY_SEARCH_USER = "select * from usuario";
    private static final String QRY_SEARCH_STORE = "select t.id_tienda, t.desc_tienda, t.direccion_tienda, t.coordenadas_tienda, m.id_municipio, m.desc_municipio, d.id_departamento, d.desc_departamento from tienda t left outer join municipio m on m.id_municipio = t.id_municipio left outer join departamento d on d.id_departamento = m.id_departamento";
    private static final String QRY_SEARCH_STATE = "select d.id_departamento, d.desc_departamento, m.id_departamento, m.id_municipio, m.desc_municipio from departamento d left outer join municipio m on m.id_departamento = d.id_departamento";

    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DEPARTAMENTO_TABLE_CREATE);
            db.execSQL(MUNICIPIO_TABLE_CREATE);
            db.execSQL(MUNICIPIO_INDEX_CREATE);
            db.execSQL(TIENDA_TABLE_CREATE);
            db.execSQL(TIENDA_INDEX_CREATE);
            db.execSQL(TIENDA_UNIQUE_CREATE_DIRECCION);
            db.execSQL(PRODUCTO_TABLE_CREATE);
            db.execSQL(PRODUCTO_INDEX_UNIQUE_CREATE);
            db.execSQL(USUARIO_TABLE_CREATE);
            db.execSQL(USUARIO_UNIQUE_EMAIL);
            db.execSQL(MERCADO_TABLE_CREATE);
            db.execSQL(MERCADO_INDEX_CREATE_PRODUCTO);
            db.execSQL(MERCADO_INDEX_CREATE_USUARIO);
            db.execSQL(MERCADO_INDEX_UNIQUE_CREATE_USUARIO);
            db.execSQL(MERCADO_INDEX_UNIQUE_CREATE_PRODUCTO);
            db.execSQL(VALOR_PRODUCTO_TABLE_CREATE);
            db.execSQL(MERCADO_INDEX_CREATE_VALOR_PRODUCTO);
            db.execSQL(TIENDA_PRODUCTO_CREATE_TABLE);
            db.execSQL(TIENDA_PRODUCTO_INDEX_TIENDA);
            db.execSQL(TIENDA_PRODUCTO_INDEX_PRODUCTO);
            db.execSQL(CARGA_DEPARTAMENTOS);
            db.execSQL(CARGA_MUNICIPIOS);
        } catch (Exception e) {
            Log.e("Error", "onCreate: ",null );
        }
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

    public long insertUser(SQLiteDatabase db, EditText eTName, EditText eTLastName, EditText eTAddress, EditText eTLocation, EditText eTEMail, String userIdFacebook) {
        Usuario usuario = new Usuario(eTName.getText().toString(), eTLastName.getText().toString(), eTAddress.getText().toString(), eTLocation.getText().toString(), eTEMail.getText().toString(), userIdFacebook, null);
        ContentValues cv = new ContentValues();
        cv.put("nombre_usuario", usuario.getNombreUsuario());
        cv.put("apellido_usuario", usuario.getApellidoUsuario());
        cv.put("direccion_usuario", usuario.getDireccionUsuario());
        cv.put("coordenadas_usuario", usuario.getCoordenadasUsuario());
        cv.put("e_mail_usuario", usuario.getEmailUsuario());
        cv.put("facebook", usuario.getFacebookUsuario());
        cv.put("google", usuario.getGoogleUsuario());
        return db.insert("usuario", null, cv);
    }

    public Usuario getUsuario(SQLiteDatabase db) {
        Usuario usuario = new Usuario();
        Cursor cursor = db.rawQuery(QRY_SEARCH_USER, null);
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

    public ArrayList<Departamento> getDepartamentos(SQLiteDatabase db) {
        ArrayList<Departamento> departamentos = new ArrayList<>();
        ArrayList<Municipio> municipios = new ArrayList<>();
        Cursor cursor = db.rawQuery(QRY_SEARCH_STATE, null);
        int flagStateChange = 0;
        Departamento departamento = new Departamento();
        Municipio municipio = new Municipio();
        while (cursor.moveToNext()) {
            if (cursor.getInt(0) == flagStateChange || flagStateChange == 0) {
                departamento = new Departamento();
                departamento.setIdDepartamento(cursor.getInt(0));
                departamento.setDescDepartamento(cursor.getString(1));
                municipio = new Municipio();
                municipio.setDepartamento(departamento);
                municipio.setIdMunicipio(cursor.getInt(3));
                municipio.setDescMunicipio(cursor.getString(4));
                municipios.add(municipio);
                flagStateChange = departamento.getIdDepartamento();
            } else {
                departamento.setMunicipios(municipios);
                departamentos.add(departamento);
                departamento = new Departamento();
                departamento.setIdDepartamento(cursor.getInt(0));
                departamento.setDescDepartamento(cursor.getString(1));
                municipios = new ArrayList<>();
                municipio = new Municipio();
                municipio.setDepartamento(departamento);
                municipio.setIdMunicipio(cursor.getInt(3));
                municipio.setDescMunicipio(cursor.getString(4));
                municipios.add(municipio);
                flagStateChange = departamento.getIdDepartamento();
            }
            if (cursor.isLast()) {
                departamento.setMunicipios(municipios);
                departamentos.add(departamento);
            }
        }
        return departamentos;
    }


    public long insertTienda(SQLiteDatabase db, Tienda tienda) {
        ContentValues cv = new ContentValues();
        cv.put("desc_tienda", tienda.getDescTienda());
        cv.put("direccion_tienda", tienda.getDireccionTienda());
        cv.put("coordenadas_tienda", tienda.getCoordenadasTienda());
        cv.put("id_municipio", tienda.getMunicipio().getIdMunicipio());
        return db.insert("tienda", null, cv);
    }

    public int updateTienda(SQLiteDatabase db, Tienda tienda) {
        ContentValues cv = new ContentValues();
        cv.put("desc_tienda", tienda.getDescTienda());
        cv.put("direccion_tienda", tienda.getDireccionTienda());
        cv.put("coordenadas_tienda", tienda.getCoordenadasTienda());
        cv.put("id_municipio", tienda.getMunicipio().getIdMunicipio());
        return db.update("tienda", cv, " id_tienda = ?", new String[]{String.valueOf(tienda.getIdTienda())});
    }

    public Producto getProducto(SQLiteDatabase db, String scanContent) {

        Producto producto = new Producto();
        String[] args = new String[]{scanContent};
        Cursor cursor = db.rawQuery(" SELECT * FROM producto WHERE barcode = ? ", args);
        while (cursor.moveToNext()) {
            producto.setIdProducto(cursor.getInt(0));
            producto.setBarCode(cursor.getString(1));
            producto.setMarca(cursor.getString(2));
            producto.setDescProducto(cursor.getString(3));
        }
        //
        return producto;
        //
    }
}