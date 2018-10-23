package com.traffico.manhattan.entities;

import java.io.Serializable;
import java.util.List;

public class Tienda implements Serializable {

    private int idTienda;
    private String descTienda;
    private String direccionTienda;
    private String coordenadasTienda;
    private Municipio municipio;
    private List<Producto> productos;


    public Tienda(int idTienda, String descTienda, String direccionTienda, Municipio municipio) {
        this.idTienda = idTienda;
        this.descTienda = descTienda;
        this.direccionTienda = direccionTienda;
        this.municipio = municipio;
    }

    public Tienda(int idTienda, String descTienda, String direccionTienda, String coordenadasTienda, Municipio municipio) {
        this.idTienda = idTienda;
        this.descTienda = descTienda;
        this.direccionTienda = direccionTienda;
        this.coordenadasTienda = coordenadasTienda;
        this.municipio = municipio;
    }

    public Tienda() {

    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public String getDescTienda() {
        return descTienda;
    }

    public void setDescTienda(String descTienda) {
        this.descTienda = descTienda;
    }

    public String getDireccionTienda() {
        return direccionTienda;
    }

    public void setDireccionTienda(String direccionTienda) {
        this.direccionTienda = direccionTienda;
    }

    public String getCoordenadasTienda() {
        return coordenadasTienda;
    }

    public void setCoordenadasTienda(String coordenadasTienda) {
        this.coordenadasTienda = coordenadasTienda;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return descTienda + ", " + direccionTienda;
    }
}
