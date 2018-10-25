package com.traffico.manhattan.entities;

public class MercadoProducto {

    private int idMercadoProducto;
    private int cantidadProductos;
    private int totalProductos;
    private Mercado mercado;
    private ValorProducto valorProducto;

    public MercadoProducto(int cantidadProductos, int totalProductos, Mercado mercado, ValorProducto valorProducto) {
        this.cantidadProductos = cantidadProductos;
        this.totalProductos = totalProductos;
        this.mercado = mercado;
        this.valorProducto = valorProducto;
    }

    public int getIdMercadoProducto() {
        return idMercadoProducto;
    }

    public void setIdMercadoProducto(int idMercadoProducto) {
        this.idMercadoProducto = idMercadoProducto;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public int getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(int totalProductos) {
        this.totalProductos = totalProductos;
    }

    public Mercado getMercado() {
        return mercado;
    }

    public void setMercado(Mercado mercado) {
        this.mercado = mercado;
    }

    public ValorProducto getValorProducto() {
        return valorProducto;
    }

    public void setValorProducto(ValorProducto valorProducto) {
        this.valorProducto = valorProducto;
    }

    @Override
    public String toString() {
        return "MercadoProducto{" +
                "idMercadoProducto=" + idMercadoProducto +
                ", cantidadProductos=" + cantidadProductos +
                ", totalProductos=" + totalProductos +
                ", mercado=" + mercado +
                ", valorProducto=" + valorProducto +
                '}';
    }
}
