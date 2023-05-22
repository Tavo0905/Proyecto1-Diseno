package com.proyecto1diseno.app.Modelo;

public class Estudiante {
    private int carnet;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String correo;
    private int celular;
    private String contrasena;

    public Estudiante(int carnet, String nombre, String apellido1, String apellido2, String correo, int celular, String contrasena) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.correo = correo;
        this.celular = celular;
        this.contrasena = contrasena;
    }

    public Estudiante (){
        
    }
    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getContrasena(){
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}