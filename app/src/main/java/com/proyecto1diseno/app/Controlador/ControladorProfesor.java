package com.proyecto1diseno.app.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto1diseno.app.Modelo.Profesor;
import com.proyecto1diseno.app.Servicio.ProfesorService;

@RestController
@RequestMapping("/profesor")
public class ControladorProfesor {
    
    private final ProfesorService profesorService;
    
    @Autowired
    public ControladorProfesor(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @RequestMapping("/editarInfo")
    public boolean editarInfo(Profesor profesor) {
        return profesorService.editarInfo(profesor);
    }

    @RequestMapping("/accesarListaEstudiantes")
    public void accesarListaEstudiantes(String campus) {
        // TBD
    }

    @RequestMapping("/modificarEstudiante")
    public boolean modificarEstudiante(com.proyecto1diseno.app.Modelo.Estudiante estudiante) {
        return false;
    }

    @RequestMapping("/generarExcel")
    public void generarExcel(String campus) {
        // TBD
    }

    @RequestMapping("/consultarEquipoGuia")
    public com.proyecto1diseno.app.Modelo.EquipoGuia consultarEquipoGuia() {
        // TBD
        return null;
    }

    @RequestMapping("/consultarPlanDeTrabajo")
    public void consultarPlanDeTrabajo() {
        // TBD
    }

    @RequestMapping("/definirPlanTrabajo")
    public com.proyecto1diseno.app.Modelo.PlanTrabajo definirPlanTrabajo(com.proyecto1diseno.app.Modelo.PlanTrabajo plan) {
        // TBD
        return null;
    }
}