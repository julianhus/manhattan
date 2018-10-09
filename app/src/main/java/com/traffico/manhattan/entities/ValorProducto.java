package com.traffico.manhattan.entities;

import java.util.Date;

public class ValorProducto {

    private int idProducto;
    private float valorProducto;
    private Date fechaRegistroValor;
    private float valorProductoEquivalente;
    private String medida;
    private float valorMedida;
    private Producto producto;

    public ValorProducto(int idProducto, float valorProducto, Date fechaRegistroValor) {
        this.idProducto = idProducto;
        this.valorProducto = valorProducto;
        this.fechaRegistroValor = fechaRegistroValor;
    }

    public ValorProducto(int idProducto, float valorProducto, Date fechaRegistroValor, float valorProductoEquivalente, String medida, float valorMedida, Producto producto) {
        this.idProducto = idProducto;
        this.valorProducto = valorProducto;
        this.fechaRegistroValor = fechaRegistroValor;
        this.valorProductoEquivalente = valorProductoEquivalente;
        this.medida = medida;
        this.valorMedida = valorMedida;
        this.producto = producto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public float getValorProducto() {
        return valorProducto;
    }

    public void setValorProducto(float valorProducto) {
        this.valorProducto = valorProducto;
    }

    public Date getFechaRegistroValor() {
        return fechaRegistroValor;
    }

    public void setFechaRegistroValor(Date fechaRegistroValor) {
        this.fechaRegistroValor = fechaRegistroValor;
    }

    public float getValorProductoEquivalente() {
        return valorProductoEquivalente;
    }

    public void setValorProductoEquivalente(float valorProductoEquivalente) {
        this.valorProductoEquivalente = valorProductoEquivalente;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public float getValorMedida() {
        return valorMedida;
    }

    public void setValorMedida(float valorMedida) {
        this.valorMedida = valorMedida;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
