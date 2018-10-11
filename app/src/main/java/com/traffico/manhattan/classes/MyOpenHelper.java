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
import com.traffico.manhattan.entities.Usuario;

public class MyOpenHelper extends SQLiteOpenHelper {
    private static final String DEPARTAMENTO_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS 'departamento' (  'id_departamento' INT NOT NULL,  'desc_departamento' VARCHAR(128) NOT NULL,  PRIMARY KEY ('id_departamento'))";
    private static final String MUNICIPIO_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS 'municipio' (  'id_municipio' INT NOT NULL,  'desc_municipio' VARCHAR(128) NOT NULL,  'id_departamento' INT NOT NULL,  PRIMARY KEY ('id_municipio'),  CONSTRAINT 'fk_municipio_departamento'    FOREIGN KEY ('id_departamento')    REFERENCES 'departamento' ('id_departamento')    ON DELETE NO ACTION    ON UPDATE NO ACTION)";
    private static final String MUNICIPIO_INDEX_CREATE = "CREATE INDEX 'fk_municipio_departamento_idx' ON 'municipio' ('id_departamento' ASC)";
    private static final String TIENDA_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS 'tienda' (  'id_tienda' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,  'desc_tienda' VARCHAR(128) NOT NULL,  'direccion_tienda' VARCHAR(45) NULL,  'coordenadas_tienda' VARCHAR(45) NULL,  'id_municipio' INT NOT NULL,    CONSTRAINT 'fk_tienda_municipio1'    FOREIGN KEY ('id_municipio')    REFERENCES 'municipio' ('id_municipio')    ON DELETE NO ACTION    ON UPDATE NO ACTION)";
    private static final String TIENDA_INDEX_CREATE = "CREATE INDEX 'fk_tienda_municipio1_idx' ON 'tienda' ('id_municipio' ASC)";
    private static final String PRODUCTO_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS 'producto' (  'id_producto' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,  'barcode' BIGINT NOT NULL,  'Marca' VARCHAR(45) NOT NULL,  'descripcion' VARCHAR(45) NOT NULL,  'id_tienda' INT NOT NULL,   CONSTRAINT 'fk_producto_tienda1'    FOREIGN KEY ('id_tienda')    REFERENCES 'tienda' ('id_tienda')    ON DELETE NO ACTION    ON UPDATE NO ACTION)";
    private static final String PRODUCTO_INDEX_UNIQUE_CREATE = "CREATE UNIQUE INDEX 'barcode_UNIQUE' ON 'producto' ('barcode' ASC)";
    private static final String PRODUCTO_INDEX_CREATE = "CREATE INDEX 'fk_producto_tienda1_idx' ON 'producto' ('id_tienda' ASC)";
    private static final String USUARIO_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS 'usuario' (  'id_usuario' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,  'nombre_usuario' VARCHAR(45) NOT NULL,  'apellido_usuario' VARCHAR(45) NOT NULL,  'direccion_usuario' VARCHAR(45) NULL,  'coordenadas_usuario' VARCHAR(45) NULL,  'e_mail_usuario' VARCHAR(45) NOT NULL,  'facebook' VARCHAR(45) NULL,  'google' VARCHAR(45) NULL)";
    private static final String MERCADO_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS 'mercado' (  'id_mercado' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,  'id_usuario' INT NOT NULL,  'id_producto' INT NOT NULL,  'fecha_registro' VARCHAR(45) NOT NULL,  'cantidad_producto' INT NOT NULL,  CONSTRAINT 'fk_usuario_has_producto_usuario1'    FOREIGN KEY ('id_usuario')    REFERENCES 'usuario' ('id_usuario')    ON DELETE NO ACTION    ON UPDATE NO ACTION,  CONSTRAINT 'fk_usuario_has_producto_producto1'    FOREIGN KEY ('id_producto')    REFERENCES 'producto' ('id_producto')    ON DELETE NO ACTION    ON UPDATE NO ACTION)";
    private static final String MERCADO_INDEX_CREATE_PRODUCTO = "CREATE INDEX 'fk_usuario_has_producto_producto1_idx' ON 'mercado' ('id_producto' ASC)";
    private static final String MERCADO_INDEX_CREATE_USUARIO = "CREATE INDEX 'fk_usuario_has_producto_usuario1_idx' ON 'mercado' ('id_usuario' ASC)";
    private static final String MERCADO_INDEX_UNIQUE_CREATE_USUARIO = "CREATE UNIQUE INDEX 'id_usuario_UNIQUE' ON 'mercado' ('id_usuario' ASC)";
    private static final String MERCADO_INDEX_UNIQUE_CREATE_PRODUCTO = "CREATE UNIQUE INDEX 'id_producto_UNIQUE' ON 'mercado' ('id_producto' ASC)";
    private static final String VALOR_PRODUCTO_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS 'valor_producto' (  'id_producto' INT NOT NULL,  'valor_producto' VARCHAR(45) NOT NULL,  'fecha_registro_valor' DATE NOT NULL,  'valor_producto_equivalente' VARCHAR(45) NULL,  'valor_medida' VARCHAR(45) NULL,  'medida' VARCHAR(45) NULL,  PRIMARY KEY ('id_producto', 'valor_producto', 'fecha_registro_valor'),  CONSTRAINT 'fk_valor_producto_producto1'    FOREIGN KEY ('id_producto')    REFERENCES 'producto' ('id_producto')    ON DELETE NO ACTION    ON UPDATE NO ACTION)";
    private static final String MERCADO_INDEX_CREATE_VALOR_PRODUCTO = "CREATE INDEX 'fk_valor_producto_producto1_idx' ON 'valor_producto' ('id_producto' ASC)";
    private static final String DB_NAME = "manhattan.sqlite";
    private static final int DB_VERSION = 1;
    //
    private static final String QRY_SEARCH_USER = "select * from usuario";
    private static final String QRY_INSERT_USER = "insert into usuario (?,?,?,?,?,?,?)";

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
        db.execSQL(MERCADO_TABLE_CREATE);
        db.execSQL(MERCADO_INDEX_CREATE_PRODUCTO);
        db.execSQL(MERCADO_INDEX_CREATE_USUARIO);
        db.execSQL(MERCADO_INDEX_UNIQUE_CREATE_USUARIO);
        db.execSQL(MERCADO_INDEX_UNIQUE_CREATE_PRODUCTO);
        db.execSQL(VALOR_PRODUCTO_TABLE_CREATE);
        db.execSQL(MERCADO_INDEX_CREATE_VALOR_PRODUCTO);
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
}
