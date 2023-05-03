package Modelo;

public class Estudiante {
    private int carnet;
    private String nombre;
    private String correo;
    private int celular;

    public Estudiante(int carnet, String nombre, String correo, int celular) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.correo = correo;
        this.celular = celular;
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
}