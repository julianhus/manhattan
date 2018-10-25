package com.traffico.manhattan.entities;

public class TiendaProducto {

    private int idTiendaProducto;
    Tienda tienda;
    Producto producto;

    public TiendaProducto() {
    }

    public int getIdTiendaProducto() {
        return idTiendaProducto;
    }

    public void setIdTiendaProducto(int idTiendaProducto) {
        this.idTiendaProducto = idTiendaProducto;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "TiendaProducto{" +
                "idTiendaProducto=" + idTiendaProducto +
                ", tienda=" + tienda +
                ", producto=" + producto +
                '}';
    }
}
