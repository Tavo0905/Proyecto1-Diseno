package com.proyecto1diseno.app.Controlador;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.proyecto1diseno.app.Modelo.Estudiante;
import com.proyecto1diseno.app.Modelo.Profesor;
import com.proyecto1diseno.app.Servicio.EstudianteService;
import com.proyecto1diseno.app.Servicio.ProfesorService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/estudiante")
public class ControladorEstudiante {

    private final EstudianteService estudianteService;

    @Autowired
    public ControladorEstudiante(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping("/gestionarEst")
    public ResponseEntity<List<Map<String,Object>>> gestionarEst(@RequestBody Map<String, Object> requestBody) throws SQLException, JsonProcessingException {
        String user = (String) requestBody.get("user");
        log.info("USER: " + user);
         
        List<Map<String, Object>> estudiantes = estudianteService.obtenerEstudiantes(user);
        log.info("ESTUDIANTES: " + estudiantes);
        return ResponseEntity.ok().body(estudiantes);
    }

    /*@PostMapping("/modificarEstudiante")
    public ResponseEntity<String> getEstudiante(@RequestBody Map<String, Object> requestBody) throws SQLException {
        String codigoEst = (String) requestBody.get("carnet");
        log.info("AQUI");
        log.info(codigoEst);
        Estudiante estudianteAMostrar = estudianteService.getEstudiante(codigoEst);
        Gson gson = new Gson();
        String jsonEstudiante = gson.toJson(estudianteAMostrar);
        return ResponseEntity.ok().body(jsonEstudiante);
        }*/

        @PostMapping("/modEst")
        public ResponseEntity<String> getEstudiante(@RequestBody Map<String, Object> requestBody) throws SQLException {
            String codigoEst = (String) requestBody.get("codigo");
            log.info("AQUI");
            log.info(codigoEst);
            Estudiante estudianteAMostrar = estudianteService.getEstudiante(codigoEst);
            Gson gson = new Gson();
            String jsonEstudiante = gson.toJson(estudianteAMostrar);
            return ResponseEntity.ok().body(jsonEstudiante);
            }

}