package com.proyecto1diseno.app.Servicio;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto1diseno.app.DAO.DBManager;
import com.proyecto1diseno.app.DAO.ProfesorDAO;
import com.proyecto1diseno.app.Modelo.Profesor;

@Service
public class ProfesorService {
    private ProfesorDAO profesorDAO;

    @Autowired
    public ProfesorService() throws SQLException {
        profesorDAO = DBManager.getProfesorDAO();
    }

    public Optional<Profesor> validarCredenciales(String correo, String contrasena) throws SQLException {
        return profesorDAO.validarCredenciales(correo, contrasena);
    }

    public List<Map<String, Object>> obtenerProfesores(String user) throws SQLException {
        return profesorDAO.obtenerProfesores(user);
    }

    public Profesor obtenerProfesor(String codigoProf) throws SQLException {
        Profesor profesorEncontrado = profesorDAO.obtenerProfesor(codigoProf);

        if (profesorEncontrado == null) {
            throw new NoSuchElementException("Profesor no encontrado");
        }

        return profesorEncontrado;
    }

    public String agregarProfesor(Profesor profesor, String user) throws SQLException {
        return profesorDAO.agregarProfesor(profesor, user);
    }

    public String modificarProfesor(Profesor profesor) throws SQLException {
        return profesorDAO.modificarProfesor(profesor);
    }

    public String darDeBajaProfesor(int codigoProf) throws SQLException {
        return profesorDAO.darDeBajaProfesor(codigoProf);
    }

    public String defGuiaProfesor(int codigoProf) throws SQLException {
        return profesorDAO.defGuiaProfesor(codigoProf);
    }

    public List<Map<String, Object>> obtenerProfesoresGuia(String user) throws SQLException {
        return profesorDAO.obtenerProfesoresGuia(user);
    }



    
}
