package com.traffico.manhattan.entities;

import java.util.List;

public class Municipio {

    private int idMunicipio;
    private String descMunicipio;
    private Departamento departamento;
    private List<Tienda> tiendas;

    public Municipio(int idMunicipio, String descMunicipio, Departamento departamento) {
        this.idMunicipio = idMunicipio;
        this.descMunicipio = descMunicipio;
        this.departamento = departamento;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getDescMunicipio() {
        return descMunicipio;
    }

    public void setDescMunicipio(String descMunicipio) {
        this.descMunicipio = descMunicipio;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Tienda> getTiendas() {
        return tiendas;
    }

    public void setTiendas(List<Tienda> tiendas) {
        this.tiendas = tiendas;
    }
}
