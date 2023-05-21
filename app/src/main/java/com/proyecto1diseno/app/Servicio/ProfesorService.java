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

    public boolean editarInfo(Profesor profesor){
        return false;
    }

    public Optional<Profesor> validarCredenciales(String correo, String contrasena) throws SQLException {
        return profesorDAO.validarCredenciales(correo, contrasena);
    }

    public List<Map<String, Object>> obtenerProfesores(String user) throws SQLException {
        return profesorDAO.obtenerProfesores(user);
    }

    public Profesor getProfesor(String codigoProf) throws SQLException {
        Profesor profesorEncontrado = profesorDAO.getProfesor(codigoProf);

        if (profesorEncontrado == null) {
            throw new NoSuchElementException("Profesor no encontrado");
        }

        return profesorEncontrado;
    }

    public void modificarProfesor(Profesor profesor) throws SQLException {
        profesorDAO.modificarProfesor(profesor);
    }

    public void darDeBajaProfesor(int codigoProf) throws SQLException {
        profesorDAO.darDeBajaProfesor(codigoProf);
    }

    public void defGuiaProfesor(int codigoProf) throws SQLException {
        profesorDAO.defGuiaProfesor(codigoProf);
    }




    
}
