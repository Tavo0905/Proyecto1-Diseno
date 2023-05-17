package com.proyecto1diseno.app.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.proyecto1diseno.app.Modelo.Profesor;

public class ProfesorDAO {
    private final Connection connection;

    public ProfesorDAO(Connection connection) {
        this.connection = connection;
    }

    public Optional<Profesor> validarCredenciales(String correo, String contraseña) throws SQLException {
        String sql = "SELECT * FROM Profesores WHERE correo = ? AND contraseña = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, correo);
            statement.setString(2, contraseña);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Profesor profesor = new Profesor(
                        result.getString("correo"),
                        result.getString("contraseña")
                    );
                    return Optional.of(profesor);
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public List<Map<String, Object>> obtenerProfesores(String user) throws SQLException { 
        List<Map<String, Object>> profes = new ArrayList<>();
        
        String query1 = "SELECT * FROM Profesores WHERE correo = ?";
        PreparedStatement statement1 = connection.prepareStatement(query1);
        statement1.setString(1, user);
        ResultSet resultSet1 = statement1.executeQuery();
        if (resultSet1.next()) {
            String profesorSede = resultSet1.getString("idSede");
            
            if (profesorSede != null && !profesorSede.isEmpty()) {
                String query2 = "SELECT * FROM Profesores WHERE idSede = ?";
                PreparedStatement statement2 = connection.prepareStatement(query2);
                statement2.setString(1, profesorSede);
                ResultSet resultSet2 = statement2.executeQuery();
                
                while (resultSet2.next()) {
                    Map<String, Object> profesor = new HashMap<>();
                    profesor.put("id", profesorSede + "-" + resultSet2.getInt("idProfesor"));
                    profesor.put("nombre", resultSet2.getString("nombre"));
                    profesor.put("correo", resultSet2.getString("correo"));
                    profesor.put("tel", resultSet2.getString("numeroOficina"));
                    profes.add(profesor);
                }

            }
        }

        return profes;
    }

    public Profesor getProfesor(int codigo) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Profesor profesorEncontrado = null;
        
        try {

            String query = "SELECT * FROM Profesores WHERE codigo = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, codigo);
            resultSet = statement.executeQuery();
            
            // Verificar si se encontró un profesor con el código dado
            if (resultSet.next()) {
                // Crear un objeto Profesor con los datos obtenidos de la consulta
                profesorEncontrado = new Profesor();
                profesorEncontrado.setCodigo(resultSet.getInt("codigo"));
                profesorEncontrado.setNombre(resultSet.getString("nombre"));
                profesorEncontrado.setIdSede(resultSet.getString("idSede"));
                profesorEncontrado.setCorreo(resultSet.getString("correo"));
                profesorEncontrado.setContraseña(resultSet.getString("contraseña"));
                profesorEncontrado.setTelOficina(resultSet.getInt("numeroOficina"));
                profesorEncontrado.setCelular(resultSet.getInt("numeroCelular"));
                profesorEncontrado.setFotografia(resultSet.getString("fotografia"));
                profesorEncontrado.setGuia(resultSet.getBoolean("guia"));
                profesorEncontrado.setCoordinador(resultSet.getBoolean("coordinador"));
                
            }
            
        } finally {
            // Cerrar los recursos utilizados
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        
        return profesorEncontrado;
    }

    



}
