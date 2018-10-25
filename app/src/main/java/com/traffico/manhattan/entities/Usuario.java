package com.traffico.manhattan.entities;

import java.util.List;

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String direccionUsuario;
    private String coordenadasUsuario;
    private Municipio municipio;
    private String emailUsuario;
    private String facebookUsuario;
    private String googleUsuario;
    private List<Mercado> mercados;

    public Usuario(String nombreUsuario, String apellidoUsuario, String emailUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.emailUsuario = emailUsuario;
    }

    public Usuario(String nombreUsuario, String apellidoUsuario, String direccionUsuario, String coordenadasUsuario, String emailUsuario, String facebookUsuario, String googleUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.direccionUsuario = direccionUsuario;
        this.coordenadasUsuario = coordenadasUsuario;
        this.emailUsuario = emailUsuario;
        this.facebookUsuario = facebookUsuario;
        this.googleUsuario = googleUsuario;
    }

    public Usuario() {

    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getDireccionUsuario() {
        return direccionUsuario;
    }

    public void setDireccionUsuario(String direccionUsuario) {
        this.direccionUsuario = direccionUsuario;
    }

    public String getCoordenadasUsuario() {
        return coordenadasUsuario;
    }

    public void setCoordenadasUsuario(String coordenadasUsuario) {
        this.coordenadasUsuario = coordenadasUsuario;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getFacebookUsuario() {
        return facebookUsuario;
    }

    public void setFacebookUsuario(String facebookUsuario) {
        this.facebookUsuario = facebookUsuario;
    }

    public String getGoogleUsuario() {
        return googleUsuario;
    }

    public void setGoogleUsuario(String googleUsuario) {
        this.googleUsuario = googleUsuario;
    }

    public List<Mercado> getMercados() {
        return mercados;
    }

    public void setMercados(List<Mercado> mercados) {
        this.mercados = mercados;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", apellidoUsuario='" + apellidoUsuario + '\'' +
                ", direccionUsuario='" + direccionUsuario + '\'' +
                ", coordenadasUsuario='" + coordenadasUsuario + '\'' +
                ", emailUsuario='" + emailUsuario + '\'' +
                ", facebookUsuario='" + facebookUsuario + '\'' +
                ", googleUsuario='" + googleUsuario + '\'' +
                ", mercados=" + mercados +
                '}';
    }
}
