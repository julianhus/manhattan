package com.traffico.manhattan.entities;

import java.util.Date;

public class Mercado {

    private int idMercado;
    private Usuario usuario;
    private Producto producto;
    private Date fechaRegistro;
    private int cantidadProducto;

    public Mercado(int idMercado, Usuario usuario, Producto producto, Date fechaRegistro, int cantidadProducto) {
        this.idMercado = idMercado;
        this.usuario = usuario;
        this.producto = producto;
        this.fechaRegistro = fechaRegistro;
        this.cantidadProducto = cantidadProducto;
    }

    public int getIdMercado() {
        return idMercado;
    }

    public void setIdMercado(int idMercado) {
        this.idMercado = idMercado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
}
