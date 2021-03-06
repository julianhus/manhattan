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
import com.traffico.manhattan.entities.TiendaProducto;
import com.traffico.manhattan.entities.Usuario;
import com.traffico.manhattan.entities.ValorProducto;
import com.traffico.manhattan.interfaces.StringsCreacion;

import java.util.ArrayList;
import java.util.List;

public class MyOpenHelper extends SQLiteOpenHelper implements StringsCreacion {

    private static final String DB_NAME = "manhattan.sqlite";
    private static final int DB_VERSION = 1;
    //
    private static final String QRY_SEARCH_STATE = "select d.id_departamento, d.desc_departamento, m.id_municipio, m.desc_municipio from departamento d left outer join municipio m on m.id_departamento = d.id_departamento";
    private static final String QRY_SEARCH_COUTRY = "";
    private static final String QRY_SEARCH_USER = "select u.id_usuario, u.nombre_usuario, u.apellido_usuario, u.direccion_usuario, u.coordenadas_usuario, u.e_mail_usuario, u.facebook, u.google,  m.id_municipio,m.desc_municipio, d.id_departamento, d.desc_departamento from usuario u left outer join municipio m on m.id_municipio = u.id_municipio left outer join departamento d on d.id_departamento = m.id_departamento";
    private static final String QRY_SEARCH_STORE = "select t.id_tienda, t.desc_tienda, t.direccion_tienda, t.coordenadas_tienda, m.id_municipio, m.desc_municipio, d.id_departamento, d.desc_departamento from tienda t left outer join municipio m on m.id_municipio = t.id_municipio left outer join departamento d on d.id_departamento = m.id_departamento";

    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DEPARTAMENTO_TABLE);
            db.execSQL(MUNICIPIO_TABLE);
            db.execSQL(MUNICIPIO_INDEX);
            db.execSQL(TIENDA_TABLE);
            db.execSQL(TIENDA_INDEX);
            db.execSQL(TIENDA_UNIQUE);
            db.execSQL(PRODUCTO_TABLE);
            db.execSQL(PRODUCTO_UNIQUE);
            db.execSQL(USUARIO_TABLE);
            db.execSQL(USUARIO_INDEX);
            db.execSQL(USUARIO_UNIQUE);
            db.execSQL(MERCADO_TABLE);
            db.execSQL(MERCADO_INDEX_USUARIO);
            db.execSQL(MERCADO_INDEX_TIENDA);
            db.execSQL(TIENDA_PRODUCTO_TABLE);
            db.execSQL(TIENDA_PRODUCTO_INDEX_TIENDA);
            db.execSQL(TIENDA_PRODUCTO_PRODUCTO);
            db.execSQL(TIENDA_PRODUCTO_UNIQUE);
            db.execSQL(VALOR_PRODUCTO_TABLE);
            db.execSQL(VALOR_PRODUCTO_INDEX);
            db.execSQL(MERCADO_PRODUCTO_TABLE);
            db.execSQL(MERCADO_PRODUCTO_INDEX_MERCADO);
            db.execSQL(MERCADO_PRODUCTO_INDEX_VALOR_PRODUCTO);
            db.execSQL(CARGA_DEPARTAMENTOS);
            db.execSQL(CARGA_MUNICIPIOS_AMAZONAS);
            db.execSQL(CARGA_MUNICIPIOS_ANTIOQUIA);
            db.execSQL(CARGA_MUNICIPIOS_ARAUCA);
            db.execSQL(CARGA_MUNICIPIOS_ATLANTICO);
            db.execSQL(CARGA_MUNICIPIOS_BOGOTA);
            db.execSQL(CARGA_MUNICIPIOS_BOLIVAR);
            db.execSQL(CARGA_MUNICIPIOS_BOYACÁ);
            db.execSQL(CARGA_MUNICIPIOS_CALDAS);
            db.execSQL(CARGA_MUNICIPIOS_CAQUETÁ);
            db.execSQL(CARGA_MUNICIPIOS_CASANARE);
            db.execSQL(CARGA_MUNICIPIOS_CAUCA);
            db.execSQL(CARGA_MUNICIPIOS_CESAR);
            db.execSQL(CARGA_MUNICIPIOS_CHOCÓ);
            db.execSQL(CARGA_MUNICIPIOS_CUNDINAMARCA);
            db.execSQL(CARGA_MUNICIPIOS_CÓRDOBA);
            db.execSQL(CARGA_MUNICIPIOS_GUAINÍA);
            db.execSQL(CARGA_MUNICIPIOS_GUAJIRA);
            db.execSQL(CARGA_MUNICIPIOS_GUAVIARE);
            db.execSQL(CARGA_MUNICIPIOS_HUILA);
            db.execSQL(CARGA_MUNICIPIOS_MAGDALENA);
            db.execSQL(CARGA_MUNICIPIOS_META);
            db.execSQL(CARGA_MUNICIPIOS_NARIÑO);
            db.execSQL(CARGA_MUNICIPIOS_NDESANTANDER);
            db.execSQL(CARGA_MUNICIPIOS_PUTUMAYO);
            db.execSQL(CARGA_MUNICIPIOS_QUINDIO);
            db.execSQL(CARGA_MUNICIPIOS_RISARALDA);
            db.execSQL(CARGA_MUNICIPIOS_SANANDRÉS);
            db.execSQL(CARGA_MUNICIPIOS_SANTANDER);
            db.execSQL(CARGA_MUNICIPIOS_SUCRE);
            db.execSQL(CARGA_MUNICIPIOS_TOLIMA);
            db.execSQL(CARGA_MUNICIPIOS_VALLEDELCAUCA);
            db.execSQL(CARGA_MUNICIPIOS_VAUPÉS);
            db.execSQL(CARGA_MUNICIPIOS_VICHADA);
        } catch (Exception e) {
            Log.e("Error", "onCreate: " + e.getMessage(), null);
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
        try {
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
        } catch (Exception e) {
            Log.e("Error", "insertUser: " + e.getMessage(), null);
            return -1;
        }
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
            //
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
                municipio.setIdMunicipio(cursor.getInt(2));
                municipio.setDescMunicipio(cursor.getString(3));
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
                municipio.setIdMunicipio(cursor.getInt(2));
                municipio.setDescMunicipio(cursor.getString(3));
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
            producto.setMedida(cursor.getString(4));
            producto.setValorMedida(cursor.getFloat(5));
        }
        return producto;
    }

    public List<Producto> getProductos(SQLiteDatabase db) {
        ArrayList<Producto> productos = new ArrayList<Producto>();
        Cursor cProductos = db.rawQuery("select * from producto", null);
        while (cProductos.moveToNext()) {
            Producto producto = new Producto();
            producto.setIdProducto(cProductos.getInt(0));
            producto.setBarCode(cProductos.getString(1));
            producto.setMarca(cProductos.getString(2));
            producto.setDescProducto(cProductos.getString(3));
            producto.setMedida(cProductos.getString(4));
            producto.setValorMedida(cProductos.getFloat(5));
            //
            ArrayList<TiendaProducto> tiendaProductos = new ArrayList<>();
            String[] arguments = new String[]{"" + producto.getIdProducto()};
            Cursor cTiendaProducto = db.rawQuery("select * from tienda_producto where id_producto = ?", arguments);
            while (cTiendaProducto.moveToNext()) {
                TiendaProducto tiendaProducto = new TiendaProducto();
                tiendaProducto.setIdTiendaProducto(cTiendaProducto.getInt(0));
                //
                Tienda tienda = new Tienda();
                tienda.setIdTienda(cTiendaProducto.getInt(1));
                tienda = getTienda(db, tienda);
                tiendaProducto.setTienda(tienda);
                //
                tiendaProducto.setProducto(producto);
                //
                ArrayList<ValorProducto> valorProductos = new ArrayList<>();
                Cursor cValorProducto = db.rawQuery("select * from valor_produto where id_tienda_prodcuto = ? ", new String[]{"" + tiendaProducto.getIdTiendaProducto()});
                while (cValorProducto.moveToNext()) {
                    ValorProducto valorProducto = new ValorProducto();
                    valorProducto.setIdTiendaProducto(tiendaProducto);
                    valorProducto.setValorProducto(cValorProducto.getFloat(1));
                    valorProducto.setValorProductoEquivalente(cValorProducto.getFloat(2));
                    //valorProducto.setFechaRegistroValor(); pendiente revisar fechas
                    valorProductos.add(valorProducto);
                }
                tiendaProductos.add(tiendaProducto);
            }
            productos.add(producto);
        }
        return productos;
    }

    private Tienda getTienda(SQLiteDatabase db, Tienda tienda) {
        Cursor cTienda = db.rawQuery("select * from tienda where id_tienda = ?", new String[]{"" + tienda.getIdTienda()});
        while (cTienda.moveToNext()) {
            tienda.setDescTienda(cTienda.getString(1));
            tienda.setDireccionTienda(cTienda.getString(2));
            tienda.setCoordenadasTienda(cTienda.getString(3));
            Municipio municipio = new Municipio();
            municipio.setIdMunicipio(cTienda.getInt(4));
            tienda.setMunicipio(municipio);
        }
        return tienda;
    }

    public ArrayList<ValorProducto> getValorProducto(SQLiteDatabase db, Producto producto) {
        ArrayList<ValorProducto> valorProductos = new ArrayList<>();
        /*Cursor cursor = db.rawQuery("select p.id_producto, vp.valor_producto, vp.valor_producto_equivalente, vp.fecha_registro_valor " +
                " from producto p left outer join valor_producto vp on vp.id_producto = p.id_producto " +
                " where p.id_producto = " + producto.getIdProducto() +
                " order by vp.fecha_registro_valor", null);
        while (cursor.moveToNext()) {
            ValorProducto valorProducto = new ValorProducto();
            valorProducto.setIdProducto(cursor.getInt(0));
            valorProducto.setValorProducto(cursor.getFloat(1));
            valorProducto.setValorProductoEquivalente(cursor.getFloat(3));
            //valorProducto.setFechaRegistroValor(cursor.getString(5));
            valorProductos.add(valorProducto);
        }*/
        return valorProductos;
    }

    public long insertProduct(SQLiteDatabase db, Producto producto) {
        ContentValues cv = new ContentValues();
        cv.put("barcode", producto.getBarCode());
        cv.put("marca", producto.getMarca());
        cv.put("descripcion", producto.getDescProducto());
        cv.put("medida", producto.getMedida());
        cv.put("valor_medida", producto.getValorMedida());
        return db.insert("producto", null, cv);
    }
}