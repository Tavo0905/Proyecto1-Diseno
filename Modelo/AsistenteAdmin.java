package Modelo;

public class AsistenteAdmin {
    private String codigo;
    private String nombre;
    private String correo;
    private int celular;

    public AsistenteAdmin(String codigo, String nombre, String correo, int celular) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.correo = correo;
        this.celular = celular;
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