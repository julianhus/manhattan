package com.traffico.manhattan.entities;

import java.util.List;

public class Producto {

    private int idProducto;
    private String barCode;
    private String marca;
    private String descProducto;
    private String medida;
    private float valorMedida;

    private List<ValorProducto> valorProductos;
    private List<Mercado> mercados;

    public Producto(int idProducto, String barCode, String marca, String descProducto, List<ValorProducto> valorProductos) {
        this.idProducto = idProducto;
        this.barCode = barCode;
        this.marca = marca;
        this.descProducto = descProducto;

        this.valorProductos = valorProductos;
    }

    public Producto(int idProducto, String barCode, String marca, String descProducto) {
        this.idProducto = idProducto;
        this.barCode = barCode;
        this.marca = marca;
        this.descProducto = descProducto;
    }

    public Producto() {

    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescProducto() {
        return descProducto;
    }

    public void setDescProducto(String descProducto) {
        this.descProducto = descProducto;
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

    public List<ValorProducto> getValorProductos() {
        return valorProductos;
    }

    public void setValorProducto(List<ValorProducto> valorProductos) {
        this.valorProductos = valorProductos;
    }

    public List<Mercado> getMercados() {
        return mercados;
    }

    public void setMercados(List<Mercado> mercados) {
        this.mercados = mercados;
    }

    @Override
    public String toString() {
        return marca + "/" + descProducto;
    }
}
