package com.proyecto1diseno.app.Modelo;

public class Profesor {
    private int codigo;
    private String nombre;
    private String idSede;
    private String correo;
    private String contraseña;
    private int telOficina;
    private int celular;
    private String fotografia;
    private boolean guia;
    private boolean coordinador;

    public Profesor(int codigo, String nombre, String idSede, String correo, String contraseña, int telOficina, int celular, String fotografia, boolean guia, boolean coordinador) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.idSede = idSede;
        this.correo = correo;
        this.contraseña = contraseña;
        this.telOficina = telOficina;
        this.celular = celular;
        this.fotografia = fotografia;
        this.guia = guia;
        this.coordinador = coordinador;
    }

    public Profesor(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }      

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getIdSede() {
        return idSede;
    }

    public void setIdSede(String idSede) {
        this.idSede = idSede;
    }



}