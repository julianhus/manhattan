package com.traffico.manhattan.classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
    private static final String DEPARTAMENTO_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS 'departamento' (   'id_departamento' INT NOT NULL,   'desc_departamento' VARCHAR(128) NOT NULL,   PRIMARY KEY ('id_departamento'))";
    private static final String MUNICIPIO_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS 'municipio' (   'id_municipio' INT NOT NULL,   'desc_municipio' VARCHAR(128) NOT NULL,   'id_departamento' INT NOT NULL,   PRIMARY KEY ('id_municipio'),   CONSTRAINT 'fk_municipio_departamento'     FOREIGN KEY ('id_departamento')     REFERENCES 'departamento' ('id_departamento')     ON DELETE NO ACTION     ON UPDATE NO ACTION)";
    private static final String MUNICIPIO_INDEX_CREATE = "CREATE INDEX 'fk_municipio_departamento_idx' ON 'municipio' ('id_departamento' ASC)";
    private static final String TIENDA_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS 'tienda' (   'id_tienda' INT NOT NULL AUTO_INCREMENT,   'desc_tienda' VARCHAR(128) NOT NULL,   'direccion_tienda' VARCHAR(45) NULL,   'coordenadas_tienda' VARCHAR(45) NULL,   'id_municipio' INT NOT NULL,   PRIMARY KEY ('id_tienda'),   INDEX 'fk_tienda_municipio1_idx' ('id_municipio' ASC),   CONSTRAINT 'fk_tienda_municipio1'     FOREIGN KEY ('id_municipio')     REFERENCES 'municipio' ('id_municipio')     ON DELETE NO ACTION     ON UPDATE NO ACTION)";
    private static final String PRODUCTO_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS 'producto' (   'id_producto' INT NOT NULL AUTO_INCREMENT,   'barcode' BIGINT NOT NULL,   'Marca' VARCHAR(45) NOT NULL,   'descripcion' VARCHAR(45) NOT NULL,   'id_tienda' INT NOT NULL,   PRIMARY KEY ('id_producto'),   UNIQUE INDEX 'barcode_UNIQUE' ('barcode' ASC),   INDEX 'fk_producto_tienda1_idx' ('id_tienda' ASC),   CONSTRAINT 'fk_producto_tienda1'     FOREIGN KEY ('id_tienda')     REFERENCES 'tienda' ('id_tienda')     ON DELETE NO ACTION     ON UPDATE NO ACTION)";
    private static final String USUARIO_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS 'usuario' (   'id_usuario' INT NOT NULL AUTO_INCREMENT,   'nombre_usuario' VARCHAR(45) NOT NULL,   'apellido_usuario' VARCHAR(45) NOT NULL,   'direccion_usuario' VARCHAR(45) NULL,   'coordenadas_usuario' VARCHAR(45) NULL,   'e_mail_usuario' VARCHAR(45) NOT NULL,   'facebook' VARCHAR(45) NULL,   'google' VARCHAR(45) NULL,   PRIMARY KEY ('id_usuario'))";
    private static final String MERCADO_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS 'mercado' (   'idMercado' INT NOT NULL AUTO_INCREMENT,   'id_usuario' INT NOT NULL,   'id_producto' INT NOT NULL,   'fecha_registro' VARCHAR(45) NOT NULL,   'cantidad_producto' INT NOT NULL,   PRIMARY KEY ('idMercado'),   INDEX 'fk_usuario_has_producto_producto1_idx' ('id_producto' ASC),   INDEX 'fk_usuario_has_producto_usuario1_idx' ('id_usuario' ASC),   UNIQUE INDEX 'id_usuario_UNIQUE' ('id_usuario' ASC),   UNIQUE INDEX 'id_producto_UNIQUE' ('id_producto' ASC),   CONSTRAINT 'fk_usuario_has_producto_usuario1'     FOREIGN KEY ('id_usuario')     REFERENCES 'usuario' ('id_usuario')     ON DELETE NO ACTION     ON UPDATE NO ACTION,   CONSTRAINT 'fk_usuario_has_producto_producto1'     FOREIGN KEY ('id_producto')     REFERENCES 'producto' ('id_producto')     ON DELETE NO ACTION     ON UPDATE NO ACTION)";
    private static final String VALOR_PRODUCTO_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS 'valor_producto' (   'id_producto' INT NOT NULL,   'valor_producto' VARCHAR(45) NOT NULL,   'fecha_registro_valor' DATE NOT NULL,   'valor_producto_equivalente' VARCHAR(45) NULL,   'valor_medida' VARCHAR(45) NULL,   'medida' VARCHAR(45) NULL,   PRIMARY KEY ('id_producto', 'valor_producto', 'fecha_registro_valor'),   INDEX 'fk_valor_producto_producto1_idx' ('id_producto' ASC),   CONSTRAINT 'fk_valor_producto_producto1'     FOREIGN KEY ('id_producto')     REFERENCES 'producto' ('id_producto')     ON DELETE NO ACTION     ON UPDATE NO ACTION)";
    private static final String DB_NAME = "manhattan.sqlite";
    private static final int DB_VERSION = 1;
    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DEPARTAMENTO_TABLE_CREATE);
        db.execSQL(MUNICIPIO_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
