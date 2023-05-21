package com.proyecto1diseno.app.Servicio;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.proyecto1diseno.app.DAO.DBManager;
import com.proyecto1diseno.app.DAO.ProfesorDAO;
import com.proyecto1diseno.app.Modelo.Profesor;

@Service
public class ProfesorService {
    Collection<Profesor> profesores;

    public boolean editarInfo(Profesor profesor){
        if(profesor == null){
            return false;
        }
        for (Profesor prof : profesores){
            if (prof.getCodigo() == profesor.getCodigo()){
                prof.setCelular(profesor.getCelular());
                prof.setFotografia(profesor.getFotografia());
                prof.setNombre(profesor.getNombre());
                prof.setTelOficina(profesor.getTelOficina());
                return true;
            }
            
        }
        return false;
    }

    public Optional<Profesor> validarCredenciales(String correo, String contrasena) throws SQLException {
        ProfesorDAO profesorDAO = DBManager.getProfesorDAO();
        return profesorDAO.validarCredenciales(correo, contrasena);
    }

    public List<Map<String, Object>> obtenerProfesores(String user) throws SQLException {
        ProfesorDAO profesorDAO = DBManager.getProfesorDAO();
        List<Map<String, Object>> profesores = profesorDAO.obtenerProfesores(user);
        return profesores;
    }

    public Profesor getProfesor(String codigoProf) throws SQLException {
        ProfesorDAO profesorDAO = DBManager.getProfesorDAO();
        Profesor profesorEncontrado = profesorDAO.getProfesor(codigoProf);

        if (profesorEncontrado == null) {
            throw new NoSuchElementException("Profesor no encontrado");
        }

        return profesorEncontrado;
    }




    
}
