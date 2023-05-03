package Controlador;

import java.util.Date;

public class controladorPlanTrabajo {
    
    public boolean addActividad(Modelo.Actividad actividad) {
        //lógica para agregar una nueva actividad al plan de trabajo
        return true;
    }
    
    public boolean addOrganizador(Modelo.Profesor organizador) {
        //lógica para agregar un nuevo organizador a una actividad del plan de trabajo
        return true;
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