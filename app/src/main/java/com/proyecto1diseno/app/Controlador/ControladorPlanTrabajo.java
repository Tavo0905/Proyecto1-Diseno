package com.proyecto1diseno.app.controlador;

import java.util.Collection;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.proyecto1diseno.app.Modelo.Comentario;
import com.proyecto1diseno.app.Modelo.Estados;
import com.proyecto1diseno.app.Servicio.PlanTrabajoService;

@Controller
@RequestMapping("/plantrabajo")
public class ControladorPlanTrabajo {
    
    @Autowired
    private PlanTrabajoService planTrabajoService;
    
    public ControladorPlanTrabajo() {
    }
    
    @RequestMapping("/editaractividad")
    public boolean editarActividad(long idActividad, String nombre, String descripcion, int duracion) {
        return planTrabajoService.editarActividad(idActividad, nombre, descripcion, duracion);
    }
    
    @RequestMapping("/borraractividad")
    public boolean borrarActividad(long idActividad) {
        return planTrabajoService.borrarActividad(idActividad);
    }
    
    @RequestMapping("/getactividades")
    public Collection<Actividad> getActividades() {
        return planTrabajoService.getActividades();
    }
    
    @RequestMapping("/getactividad")
    public Actividad getActividad(long idActividad) {
        return planTrabajoService.getActividad(idActividad);
    }
    
    @RequestMapping("/addfecharecordatorio")
    public boolean addFechaRecordatorio(long idActividad, Date fecha) {
        return planTrabajoService.addFechaRecordatorio(idActividad, fecha);
    }
    
    @RequestMapping("/addcomentario")
    public boolean addComentario(long idActividad, Comentario comentario) {
        // lógica para agregar un nuevo comentario a una actividad del plan de trabajo
        return planTrabajoService.addComentario(idActividad, comentario);
    }
    
    @RequestMapping("/addreplica")
    public boolean addReplica(long idActividad, Comentario comentario) {
        // lógica para agregar una nueva réplica a un comentario de una actividad del plan de trabajo
        return planTrabajoService.addReplica(idActividad, comentario);
    }
    
    @RequestMapping("/editarestado")
    public boolean editarEstado(long idActividad, Estados estado) {
        // lógica para editar el estado de una actividad del plan de trabajo
        return planTrabajoService.editarEstado(idActividad, estado);