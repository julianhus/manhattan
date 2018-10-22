package com.traffico.manhattan.entities;

public class TiendaProducto {
    Producto producto;
    Tienda tienda;

    public TiendaProducto(Producto producto, Tienda tienda) {
        this.producto = producto;
        this.tienda = tienda;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }
}
