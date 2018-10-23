package com.traffico.manhattan.entities;

import java.io.Serializable;
import java.util.List;

public class Departamento implements Serializable {

    private int idDepartamento;
    private String descDepartamento;
    private List<Municipio> municipios;

    public Departamento(int idDepartamento, String descDepartamento) {

        this.idDepartamento = idDepartamento;
        this.descDepartamento = descDepartamento;
    }

    public Departamento() {

    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getDescDepartamento() {
        return descDepartamento;
    }

    public void setDescDepartamento(String descDepartamento) {
        this.descDepartamento = descDepartamento;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    @Override
    public String toString() {
        return descDepartamento;
    }
}
