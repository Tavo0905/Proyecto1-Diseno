package com.proyecto1diseno.app.Servicio;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.proyecto1diseno.app.DAO.DBManager;
import com.proyecto1diseno.app.DAO.EstudianteDAO;
import com.proyecto1diseno.app.Modelo.Estudiante;

@Service
public class EstudianteService {

    public List<Map<String, Object>> obtenerEstudiantes(String user) throws SQLException {
        EstudianteDAO estudianteDAO = DBManager.getEstudianteDAO();
        List<Map<String, Object>> estudiantes = estudianteDAO.obtenerEstudiantes(user);
        return estudiantes;
    }
    
}
