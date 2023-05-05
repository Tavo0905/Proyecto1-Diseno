package com.proyecto1diseno.app.Modelo;

import java.util.Collection;
import java.util.Date;
import java.time.LocalDateTime;

public class Actividad {

    private int semanaRealizacion;
    private Tipos tipo;
    private String nombre;
    private LocalDateTime fechaHora;
    private Collection<Profesor> organizadores;
    private Date fechaPublicacion;
    private Collection<Date> fechasRecordatorios;
    private Modalidades modalidad;
    private String enlace;
    private String afiche;
    private Estados estado;
    private Collection<Comentario> comentarios;

    public Actividad(int semanaRealizacion, Tipos tipo, String nombre, LocalDateTime fechaHora, Collection<Profesor> organizadores,
                     Date fechaPublicacion, Collection<Date> fechasRecordatorios, Modalidades modalidad, String enlace,
                     String afiche, Estados estado, Collection<Comentario> comentarios) {
        this.semanaRealizacion = semanaRealizacion;
        this.tipo = tipo;
        this.nombre = nombre;
        this.fechaHora = fechaHora;
        this.organizadores = organizadores;
        this.fechaPublicacion = fechaPublicacion;
        this.fechasRecordatorios = fechasRecordatorios;
        this.modalidad = modalidad;
        this.enlace = enlace;
        this.afiche = afiche;
        this.estado = estado;
        this.comentarios = comentarios;
    }

    public int getSemanaRealizacion() {
        return semanaRealizacion;
    }

    public void setSemanaRealizacion(int semanaRealizacion) {
        this.semanaRealizacion = semanaRealizacion;
    }

    public Tipos getTipo() {
        return tipo;
    }

    public void setTipo(Tipos tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Collection<Profesor> getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(Collection<Profesor> organizadores) {
        this.organizadores = organizadores;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Collection<Date> getFechasRecordatorios() {
        return fechasRecordatorios;
    }

    public void setFechasRecordatorios(Collection<Date> fechasRecordatorios) {
        this.fechasRecordatorios = fechasRecordatorios;
    }

    public Modalidades getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidades modalidad) {
        this.modalidad = modalidad;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getAfiche() {
        return afiche;
    }

    public void setAfiche(String afiche) {
        this.afiche = afiche;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public Collection<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Collection<Comentario> comentarios) {
        this.comentarios = comentarios;
        
    }
}