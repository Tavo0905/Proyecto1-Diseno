package Controlador;

import java.util.Collection;
import java.util.Date;

public class controladorPlanTrabajo {
    Modelo.PlanTrabajo planDeTrabajo;
    Collection<Modelo.Actividad> plan;
    Collection<Modelo.Profesor> organizadores;
    Modelo.Actividad actividad;


    public boolean addActividad(Modelo.Actividad actividad) {
    
        if (actividad == null) {
            return false; // no se permite agregar actividades nulas
        }
        if (plan.contains(actividad)) {  
            return false; // la actividad ya existe, no se permite duplicados
        }
        plan.add(actividad);
        planDeTrabajo.setPlan(plan);

        return true;
    }
    
    public boolean addOrganizador(Modelo.Profesor organizador) {
        for (Modelo.Actividad a : plan){
            if (a.equals(actividad)){
                organizadores.add(organizador);
                a.setOrganizadores(organizadores);
                return true;
            }
        }
        
        return false;
    }
    
    public boolean addFechaRecordatorio(Date fecha) {
        //lógica para agregar una nueva fecha de recordatorio a una actividad del plan de trabajo
        return true;
    }
    
    public boolean addComentarios(Modelo.Comentario comentario) {
        //lógica para agregar un nuevo comentario a una actividad del plan de trabajo
        return true;
    }
    
    public boolean addReplica(Modelo.Comentario comentario) {
        //lógica para agregar una nueva réplica a un comentario de una actividad del plan de trabajo
        return true;
    }
    
    public boolean editarEstado(Modelo.Estados estado) {
        //lógica para editar el estado de una actividad del plan de trabajo
        return true;
    }

    
}