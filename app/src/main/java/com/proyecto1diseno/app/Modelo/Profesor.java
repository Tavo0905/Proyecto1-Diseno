package com.proyecto1diseno.app.Modelo;

public class Profesor {
    private String codigo;
    private String nombre;
    private int telOficina;
    private int celular;
    private String fotografia;
    private boolean guia;
    private boolean coordinador;

    public Profesor(String codigo, String nombre, int telOficina, int celular, String fotografia, boolean guia, boolean coordinador) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.telOficina = telOficina;
        this.celular = celular;
        this.fotografia = fotografia;
        this.guia = guia;
        this.coordinador = coordinador;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelOficina() {
        return telOficina;
    }

    public void setTelOficina(int telOficina) {
        this.telOficina = telOficina;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public boolean isGuia() {
        return guia;
    }

    public void setGuia(boolean guia) {
        this.guia = guia;
    }

    public boolean isCoordinador() {
        return coordinador;
    }

    public void setCoordinador(boolean coordinador) {
        this.coordinador = coordinador;
    }
}