package com.traffico.manhattan.classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.traffico.manhattan.entities.Departamento;
import com.traffico.manhattan.entities.Municipio;
import com.traffico.manhattan.entities.Producto;
import com.traffico.manhattan.entities.Tienda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProductContent {

    public static List<Producto> PRODUCTS = new ArrayList<>();

    public static final Map<Integer, Producto> PRODUCTO_MAP = new HashMap<Integer, Producto>();

    public static void setContext (Context c){
        PRODUCTS = new ArrayList<>();
        List<Producto> productos;
        MyOpenHelper dbHelper = new MyOpenHelper(c);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            productos = dbHelper.getProductos(db);
            Iterator it = productos.iterator();
            while ((it.hasNext())){
                addItem((Producto) it.next());
            }
        }
    }

    static {
        /*addItem(new Producto(1, "7709691579125", "Tisanas Angel", "Limonaria"));
        addItem(new Producto(2, "7709691579521", "Tisanas Angel", "Manzanilla"));
        addItem(new Producto(3, "7709691579251", "Tisanas Angel", "Maracuya"));*/
    }

    private static void addItem(Producto producto) {
        PRODUCTS.add(producto);
        PRODUCTO_MAP.put(producto.getIdProducto(), producto);

    }
}
