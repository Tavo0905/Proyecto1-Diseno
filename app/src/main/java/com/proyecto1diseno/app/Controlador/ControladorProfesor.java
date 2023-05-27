package com.proyecto1diseno.app.Controlador;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
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


@RestController
@Slf4j
@RequestMapping("/profesor")
public class ControladorProfesor {
    
    private final ProfesorService profesorService;
    private final EstudianteService estudianteService;
    
    public ControladorProfesor(ProfesorService profesorService, EstudianteService estudianteService) {
        this.profesorService = profesorService;
        this.estudianteService = estudianteService;
    }

    @PostMapping("/agregarProf")
    public ResponseEntity<String> agregarProfesor(@RequestBody Map<String, Object> profesorData) throws SQLException {
        String user = (String) profesorData.get("user");
        Profesor profesor = new Profesor();
        profesor.setNombre((String) profesorData.get("nombre"));
        profesor.setCorreo((String) profesorData.get("correo"));
        profesor.setContrasena((String) profesorData.get("pass"));
        profesor.setTelOficina(Integer.parseInt(profesorData.get("tel").toString()));
        profesor.setCelular(Integer.parseInt(profesorData.get("cel").toString()));
        String respuestaAgregar = profesorService.agregarProfesor(profesor, user);
        if (respuestaAgregar.startsWith("Error: ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaAgregar);
        } else {
            return ResponseEntity.ok().body(respuestaAgregar);
        }
    }

    @PostMapping("/gestionarProf")
    public ResponseEntity<List<Map<String,Object>>> obtenerProfesores(@RequestBody Map<String, Object> requestBody) throws SQLException, JsonProcessingException {
        String user = (String) requestBody.get("user");
        List<Map<String, Object>> profesores = profesorService.obtenerProfesores(user);
        return ResponseEntity.ok().body(profesores);
    } 

    @PostMapping("/modProf")
    public ResponseEntity<String> obtenerProfesor(@RequestBody Map<String, Object> requestBody) throws SQLException {
        String codigoProf = (String) requestBody.get("codigo");
        Profesor profesorAMostrar = profesorService.obtenerProfesor(codigoProf);
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
        String respuestaModificar = profesorService.modificarProfesor(profesor);
        if (respuestaModificar.startsWith("Error: ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaModificar);
        } else {
            return ResponseEntity.ok().body(respuestaModificar);
        }
    }

    @PostMapping("/bajaProf")
    public ResponseEntity<String> darDeBajaProfesor(@RequestBody Map<String, Object> requestBody) throws SQLException {
        String codigoProfString = (String) requestBody.get("codigo");
        int codigoProf = Integer.parseInt(codigoProfString);
        String respuestaBaja = profesorService.darDeBajaProfesor(codigoProf);
        if (respuestaBaja.startsWith("Error: ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaBaja);
        } else {
            return ResponseEntity.ok().body(respuestaBaja);
        }
    }

    @PostMapping("/defGuia")
    public ResponseEntity<String> defGuiaProfesor(@RequestBody Map<String, Object> requestBody) throws SQLException {
        String codigoProfString = (String) requestBody.get("codigo");
        int codigoProf = Integer.parseInt(codigoProfString);
        String respuestaGuia = profesorService.defGuiaProfesor(codigoProf);
        if (respuestaGuia.startsWith("Error: ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaGuia);
        } else {
            return ResponseEntity.ok().body(respuestaGuia);
        }
    }

    @PostMapping("/gestionarProfGuia")
    public ResponseEntity<List<Map<String,Object>>> obtenerProfesoresGuia(@RequestBody Map<String, Object> requestBody) throws SQLException, JsonProcessingException {
        String user = (String) requestBody.get("user");
        List<Map<String, Object>> profesoresGuia = profesorService.obtenerProfesoresGuia(user);
        return ResponseEntity.ok().body(profesoresGuia);
    } 

}