package com.proyecto1diseno.app.Servicio;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;

import com.proyecto1diseno.app.DAO.DBManager;
import com.proyecto1diseno.app.DAO.EstudianteDAO;
import com.proyecto1diseno.app.Modelo.Estudiante;

@Service
public class EstudianteService {

    public List<Estudiante> obtenerEstudiantes(int clave, List<Object> arreglo) throws SQLException {
        EstudianteDAO estudianteDAO = DBManager.getEstudianteDAO();
        List<Estudiante> estudiantes = estudianteDAO.obtenerEstudiantes(clave, arreglo);
        return estudiantes;
    }
    
}
