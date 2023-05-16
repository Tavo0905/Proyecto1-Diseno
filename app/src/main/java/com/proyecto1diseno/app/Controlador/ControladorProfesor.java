package com.proyecto1diseno.app.Controlador;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/gestionarProf")
    public List<Profesor> gestionarProf(@RequestBody Map<String, Object> requestBody) throws SQLException {
         int clave = (int) requestBody.get("clave");
         List<Object> arreglo = (List<Object>) requestBody.get("arreglo");
         String user = (String) requestBody.get("user");
         
         List<Profesor> profesores = profesorService.obtenerProfesores(clave, arreglo, user);
        
        return profesores;
    }

    @PostMapping("/modProf")
    public Profesor getProfesor(@RequestBody Profesor profesor) throws SQLException {
        Profesor profesorModificado = profesorService.getProfesor(profesor);
        return profesorModificado;
    }
}