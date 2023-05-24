package com.proyecto1diseno.app.Controlador;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proyecto1diseno.app.Servicio.PlanTrabajoService;

@RestController
@Slf4j
@RequestMapping("/plantrabajo")
public class ControladorPlanTrabajo {
    
    @Autowired
    private PlanTrabajoService planTrabajoService;
    
    public ControladorPlanTrabajo() {
    }
    
    @PostMapping("/obtenerActividades")
    public ResponseEntity<List<Map<String, Object>>> obtenerActividades() throws SQLException, JsonProcessingException {
        List<Map<String, Object>> actividades = planTrabajoService.obtenerActividades();
        return ResponseEntity.ok().body(actividades);
    }
}