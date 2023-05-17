package com.proyecto1diseno.app.Controlador;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto1diseno.app.Modelo.Profesor;
import com.proyecto1diseno.app.Servicio.ProfesorService;


@RestController
@Slf4j
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
    public ResponseEntity<List<Map<String,Object>>> gestionarProf(@RequestBody Map<String, Object> requestBody) throws SQLException, JsonProcessingException {
        String user = (String) requestBody.get("user");
        log.info("USER: " + user);
         
        List<Map<String, Object>> profesores = profesorService.obtenerProfesores(user);
        log.info("PROFESORES: " + profesores);
        return ResponseEntity.ok().body(profesores);
    }

    @PostMapping("/modProf")
    public Profesor getProfesor(@RequestBody Profesor profesor) throws SQLException {
        Profesor profesorModificado = profesorService.getProfesor(profesor);
        return profesorModificado;
    }
}