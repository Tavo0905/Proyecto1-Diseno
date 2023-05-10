package com.proyecto1diseno.app.Servicio;
import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Service;
import com.proyecto1diseno.app.Modelo.Actividad;
import com.proyecto1diseno.app.Modelo.Comentario;
import com.proyecto1diseno.app.Modelo.Estados;
import com.proyecto1diseno.app.Modelo.PlanTrabajo;

@Service
public class PlanTrabajoService {

    Collection<Actividad> actividades;
    Actividad actividad;
    Collection<Date> fechasRecordatorios;
    Collection<Comentario> comentarios;
    PlanTrabajo planTrabajo;

    public boolean editarActividad(long idActividad, String nombre, String descripcion, int duracion){
       /*if(idActividad == null || nombre == null || descripcion == null || duracion == null){ //ni long ni int pueden ser nulos por ser tipos primitivos de Java
            return false;
        }*/
       /* for (Actividad a : actividades){
            if (a.getID() == idActividad){ //necesitamos id de actividad 
                a.setNombre(nombre);
                a.setDescripcion(descripcion);
                a.setDuracion(duracion);
                return true
            }
        } */
        return false;
    }

    public boolean borrarActividad(long idActividad){ 
        /*if (idActividad == null){ // long no puede ser nulo
            return false;
        } */
        /* for (Actividad a : actividades){
            if (a.getID() == idActividad){ //necesitamos id de actividad 
                actividades.remove(a);
                return true
            }
         } */
        return false;
    }
    
    public Collection<Actividad> getActividades(){
        return actividades;
    }

    public Actividad getActividad(long idActividad){
        /*if (idActividad == null){ // long no puede ser nulo
            return null;
        } */
        /* for (Actividad a : actividades){
            if (a.getID() == idActividad){ //necesitamos id de actividad 
                return actividad
            }
         } */
        return null;
    }

    public boolean addFechaRecordatorio(long idActividad, Date fecha){
        /*if (idActividad == null || fecha == null){ // long no puede ser nulo
            return false;
        } */
          /* for (Actividad a : actividades){
            if (a.getID() == idActividad){ //necesitamos id de actividad 
                fechasRecordatorios.add(fecha);
                return true
            }
         } */
        return false;
    }

    public boolean addComentario(long idActividad, Comentario comentario){
        /*if (idActividad == null || comentario == null){ // long no puede ser nulo
            return false;
        } */
          /* for (Actividad a : actividades){
            if (a.getID() == idActividad){ //necesitamos id de actividad 
                comentarios.add(comentario);
                return true
            }
         } */
        return false;
    }

    public boolean addReplica(long idActividad, Comentario comentario){
        /*if (idActividad == null || comentario == null){ // long no puede ser nulo
            return false;
        } */
          /* for (Actividad a : actividades){
            if (a.getID() == idActividad){ //necesitamos id de actividad 
                comentarios.add(comentario);
                return true
            }
         } */
        return false;
        
    }

    public boolean editarEstado(long idActividad, Estados estado){
        /*if (idActividad == null || estado == null){ // long no puede ser nulo
            return false;
        } */
          /* for (Actividad a : actividades){
            if (a.getID() == idActividad){ //necesitamos id de actividad 
                a.setEstado(estado);
                return true
            }
         } */
        return false;
    }
}
