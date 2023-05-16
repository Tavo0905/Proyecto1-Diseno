package com.proyecto1diseno.app.Controlador;

import java.util.Collection;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto1diseno.app.Modelo.Actividad;
import com.proyecto1diseno.app.Modelo.Comentario;
import com.proyecto1diseno.app.Modelo.Estados;
import com.proyecto1diseno.app.Servicio.PlanTrabajoService;

@RestController
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
        return planTrabajoService.addComentario(idActividad, comentario);
    }
    
    @RequestMapping("/addreplica")
    public boolean addReplica(long idActividad, Comentario comentario) {
        return planTrabajoService.addReplica(idActividad, comentario);
    }
    
    @RequestMapping("/editarestado")
    public boolean editarEstado(long idActividad, Estados estado) {
        return planTrabajoService.editarEstado(idActividad, estado);

    }
}