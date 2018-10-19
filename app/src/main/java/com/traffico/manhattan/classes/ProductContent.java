package com.traffico.manhattan.classes;

import com.traffico.manhattan.entities.Departamento;
import com.traffico.manhattan.entities.Municipio;
import com.traffico.manhattan.entities.Producto;
import com.traffico.manhattan.entities.Tienda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductContent {

    public static final List<Producto> PRODUCTS = new ArrayList<>();

    public static final Map<Integer, Producto> PRODUCTO_MAP = new HashMap<Integer, Producto>();

    static {
        addItem(new Producto(1, "7709691579125", "Tisanas Angel", "Limonaria"));
        addItem(new Producto(2, "7709691579521", "Tisanas Angel", "Manzanilla"));
        addItem(new Producto(3, "7709691579251", "Tisanas Angel", "Maracuya"));
    }

    private static void addItem(Producto producto) {
        PRODUCTS.add(producto);
        PRODUCTO_MAP.put(producto.getIdProducto(), producto);

    }
}
