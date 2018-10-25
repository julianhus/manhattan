package com.traffico.manhattan.entities;

import java.util.Date;
import java.util.List;

public class ValorProducto {

    private TiendaProducto idTiendaProducto;
    private float valorProducto;
    private float valorProductoEquivalente;
    private Date fechaRegistroValor;
    private List<MercadoProducto> mercadoProductos;

    public ValorProducto() {
    }

    public ValorProducto(TiendaProducto idTiendaProducto, float valorProducto, Date fechaRegistroValor) {
        this.idTiendaProducto = idTiendaProducto;
        this.valorProducto = valorProducto;
        this.fechaRegistroValor = fechaRegistroValor;
    }

    public TiendaProducto getIdTiendaProducto() {
        return idTiendaProducto;
    }

    public void setIdTiendaProducto(TiendaProducto idTiendaProducto) {
        this.idTiendaProducto = idTiendaProducto;
    }

    public float getValorProducto() {
        return valorProducto;
    }

    public void setValorProducto(float valorProducto) {
        this.valorProducto = valorProducto;
    }

    public float getValorProductoEquivalente() {
        return valorProductoEquivalente;
    }

    public void setValorProductoEquivalente(float valorProductoEquivalente) {
        this.valorProductoEquivalente = valorProductoEquivalente;
    }

    public Date getFechaRegistroValor() {
        return fechaRegistroValor;
    }

    public void setFechaRegistroValor(Date fechaRegistroValor) {
        this.fechaRegistroValor = fechaRegistroValor;
    }

    public List<MercadoProducto> getMercadoProductos() {
        return mercadoProductos;
    }

    public void setMercadoProductos(List<MercadoProducto> mercadoProductos) {
        this.mercadoProductos = mercadoProductos;
    }

    @Override
    public String toString() {
        return "Valor: " + valorProducto +"\n"+
                "Valor por Unidad=" + valorProductoEquivalente +"\n"+
                "Fecha Registro=" + fechaRegistroValor;
    }
}
