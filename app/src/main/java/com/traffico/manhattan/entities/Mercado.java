package com.traffico.manhattan.entities;

import java.util.Date;
import java.util.List;

public class Mercado {

    private int idMercado;
    private Tienda tienda;
    private Usuario usuario;
    private boolean estadoMercado;
    private Date fechaRegistro;
    private List<MercadoProducto> mercadoProductos;

    public Mercado(Tienda tienda, Usuario usuario, boolean estadoMercado, Date fechaRegistro) {
        this.tienda = tienda;
        this.usuario = usuario;
        this.estadoMercado = estadoMercado;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdMercado() {
        return idMercado;
    }

    public void setIdMercado(int idMercado) {
        this.idMercado = idMercado;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isEstadoMercado() {
        return estadoMercado;
    }

    public void setEstadoMercado(boolean estadoMercado) {
        this.estadoMercado = estadoMercado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<MercadoProducto> getMercadoProductos() {
        return mercadoProductos;
    }

    public void setMercadoProductos(List<MercadoProducto> mercadoProductos) {
        this.mercadoProductos = mercadoProductos;
    }

    @Override
    public String toString() {
        return "Mercado{" +
                "idMercado=" + idMercado +
                ", tienda=" + tienda +
                ", usuario=" + usuario +
                ", estadoMercado=" + estadoMercado +
                ", fechaRegistro=" + fechaRegistro +
                ", mercadoProductos=" + mercadoProductos +
                '}';
    }
}
