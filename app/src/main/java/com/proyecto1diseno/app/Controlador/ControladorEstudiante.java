package com.proyecto1diseno.app.Controlador;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto1diseno.app.Modelo.Estudiante;
import com.proyecto1diseno.app.Modelo.Profesor;
import com.proyecto1diseno.app.Servicio.EstudianteService;
import com.proyecto1diseno.app.Servicio.ProfesorService;

@RestController
@RequestMapping("/estudiante")
public class ControladorEstudiante {

    private final EstudianteService estudianteService;

    @Autowired
    public ControladorEstudiante(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    public List<Estudiante> gestionarEst(@RequestBody Map<String, Object> requestBody) throws SQLException {
        int clave = (int) requestBody.get("clave");
        List<Object> arreglo = (List<Object>) requestBody.get("arreglo");
        String user = (String) requestBody.get("user");
        
        List<Estudiante> estudiantes = estudianteService.obtenerEstudiantes(clave, arreglo, user);

        return estudiantes;
    }

}