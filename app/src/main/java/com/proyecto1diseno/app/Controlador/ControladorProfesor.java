package com.proyecto1diseno.app.Controlador;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.proyecto1diseno.app.Modelo.Profesor;
import com.proyecto1diseno.app.Servicio.ProfesorService;


@RestController
@Slf4j
@RequestMapping("/profesor")
public class ControladorProfesor {
    
    private final ProfesorService profesorService;
    
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
        List<Map<String, Object>> profesores = profesorService.obtenerProfesores(user);
        return ResponseEntity.ok().body(profesores);
    }

    @PostMapping("/modProf")
    public ResponseEntity<String> getProfesor(@RequestBody Map<String, Object> requestBody) throws SQLException {
        String codigoProf = (String) requestBody.get("codigo");
        Profesor profesorAMostrar = profesorService.getProfesor(codigoProf);
        Gson gson = new Gson();
        String jsonProfesor = gson.toJson(profesorAMostrar);
        return ResponseEntity.ok().body(jsonProfesor);
    }

    @PostMapping("/datosProfesRes")
    public ResponseEntity<String> modificarProfesor(@RequestBody Map<String, Object> profesorData) throws SQLException {
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(Integer.parseInt(profesorData.get("id").toString()));
        profesor.setNombre((String) profesorData.get("nombre"));
        profesor.setCorreo((String) profesorData.get("correo"));
        profesor.setContrasena((String) profesorData.get("pass"));
        profesor.setTelOficina(Integer.parseInt(profesorData.get("tel").toString()));
        profesor.setCelular(Integer.parseInt(profesorData.get("cel").toString()));
        profesorService.modificarProfesor(profesor);
        return ResponseEntity.ok().body("Profesor Modificado");
    }

    @PostMapping("/bajaProf")
    public ResponseEntity<String> darDeBajaProfesor(@RequestBody Map<String, Object> requestBody) throws SQLException {
        String codigoProfString = (String) requestBody.get("codigo");
        int codigoProf = Integer.parseInt(codigoProfString);
        profesorService.darDeBajaProfesor(codigoProf);
        return ResponseEntity.ok().body("Profesor dado de baja");
    }

    @PostMapping("/defGuia")
    public ResponseEntity<String> defGuiaProfesor(@RequestBody Map<String, Object> requestBody) throws SQLException {
        String codigoProfString = (String) requestBody.get("codigo");
        int codigoProf = Integer.parseInt(codigoProfString);
        profesorService.defGuiaProfesor(codigoProf);
        return ResponseEntity.ok().body("Profesor añadido como guia.");
    }
}