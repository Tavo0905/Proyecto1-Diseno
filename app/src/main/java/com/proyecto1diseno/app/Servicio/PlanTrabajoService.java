package com.proyecto1diseno.app.Servicio;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto1diseno.app.DAO.DBManager;
import com.proyecto1diseno.app.DAO.PlanTrabajoDAO;
import com.proyecto1diseno.app.Modelo.Actividad;
import com.proyecto1diseno.app.Modelo.Comentario;
import com.proyecto1diseno.app.Modelo.Estados;
import com.proyecto1diseno.app.Modelo.PlanTrabajo;

@Service
public class PlanTrabajoService {
    private PlanTrabajoDAO planTrabajoDAO;

    @Autowired
    public PlanTrabajoService() throws SQLException {
        planTrabajoDAO = DBManager.getPlanTrabajoDAO();
    }

    public List<Map<String, Object>> obtenerActividades() throws SQLException {
        return planTrabajoDAO.obtenerActividades();
    }
}
