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
        PreparedStatement statement1 = null;
        ResultSet resultSet1 = null;
        PreparedStatement statement2 = null;
        ResultSet resultSet2 = null;
    
        try {
            statement1 = connection.prepareStatement(query1);
            statement1.setString(1, user);
            resultSet1 = statement1.executeQuery();
    
            if (resultSet1.next()) {
                String profesorSede = resultSet1.getString("idSede");
    
                if (profesorSede != null && !profesorSede.isEmpty()) {
                    String query2 = "SELECT * FROM Profesores WHERE idSede = ? AND darDeBaja = 0";
                    statement2 = connection.prepareStatement(query2);
                    statement2.setString(1, profesorSede);
                    resultSet2 = statement2.executeQuery();
    
                    while (resultSet2.next()) {
                        Map<String, Object> profesor = new HashMap<>();
                        profesor.put("id", resultSet2.getInt("idProfesor"));
                        profesor.put("nombre", resultSet2.getString("nombre"));
                        profesor.put("correo", resultSet2.getString("correo"));
                        profesor.put("tel", resultSet2.getString("numeroOficina"));
                        profes.add(profesor);
                    }
                }
            }
    
            return profes;
        } finally {
            // Cerrar los recursos en el bloque finally
            if (resultSet2 != null) {
                resultSet2.close();
            }
    
            if (statement2 != null) {
                statement2.close();
            }
    
            if (resultSet1 != null) {
                resultSet1.close();
            }
    
            if (statement1 != null) {
                statement1.close();
            }
        }
    }

    public Profesor getProfesor(String codigo) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Profesor profesorEncontrado = null;
        
        int codigoNum = Integer.parseInt(codigo);
        
        try {

            String query = "SELECT * FROM Profesores WHERE idProfesor = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, codigoNum);
            resultSet = statement.executeQuery();
            
            // Verificar si se encontró un profesor con el código dado
            if (resultSet.next()) {
                // Crear un objeto Profesor con los datos obtenidos de la consulta
                profesorEncontrado = new Profesor();
                profesorEncontrado.setIdProfesor(resultSet.getInt("idProfesor"));
                profesorEncontrado.setNombre(resultSet.getString("nombre"));
                profesorEncontrado.setCorreo(resultSet.getString("correo"));
                profesorEncontrado.setContrasena(resultSet.getString("contraseña"));
                profesorEncontrado.setTelOficina(resultSet.getInt("numeroOficina"));
                profesorEncontrado.setCelular(resultSet.getInt("numeroCelular"));
                profesorEncontrado.setFotografia(resultSet.getString("foto"));   
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

    public String modificarProfesor(Profesor profesor) throws SQLException {
        String sqlCheckEmail = "SELECT idProfesor FROM Profesores WHERE correo = ?";
        String sqlUpdate = "UPDATE Profesores SET nombre = ?, correo = ?, contraseña = ?, numeroOficina = ?, numeroCelular = ? WHERE idProfesor = ?";
        
        try (PreparedStatement checkEmailStatement = connection.prepareStatement(sqlCheckEmail);
             PreparedStatement updateStatement = connection.prepareStatement(sqlUpdate)) {
            
            // Verificar si ya existe el mismo correo
            checkEmailStatement.setString(1, profesor.getCorreo());
            ResultSet resultSet = checkEmailStatement.executeQuery();
            
            if (resultSet.next()) {
                // El correo ya existe en la tabla Profesores
                return "El correo ya está en uso por otro profesor.";
            } else {
                // Realizar la actualización
                updateStatement.setString(1, profesor.getNombre());
                updateStatement.setString(2, profesor.getCorreo());
                updateStatement.setString(3, profesor.getContrasena());
                updateStatement.setInt(4, profesor.getTelOficina());
                updateStatement.setInt(5, profesor.getCelular());
                updateStatement.setInt(6, profesor.getIdProfesor());
                updateStatement.executeUpdate();
                return "Modificación exitosa.";
            }
        }
    }

    public void darDeBajaProfesor(int codigoProf) throws SQLException {
        String sql = "UPDATE Profesores SET darDeBaja = 1 WHERE idProfesor = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigoProf);
            statement.executeUpdate();
        }
    }

    public void defGuiaProfesor(int idProfesor) throws SQLException {
        String selectProfesorSql = "SELECT idSede FROM Profesores WHERE idProfesor = ?";
        String idSede;

        try (PreparedStatement selectProfesorStatement = connection.prepareStatement(selectProfesorSql)) {
            selectProfesorStatement.setInt(1, idProfesor);

            try (ResultSet resultSet = selectProfesorStatement.executeQuery()) {
                if (resultSet.next()) {
                    idSede = resultSet.getString("idSede");
                } else {
                    return;
                }
            }
        }

        String selectIdProfesorSql = "SELECT idProfesor FROM ProfesoresGuias WHERE idProfesor = ?";
        
        try (PreparedStatement selectIdProfesorStatement = connection.prepareStatement(selectIdProfesorSql)) {
            selectIdProfesorStatement.setInt(1, idProfesor);
            try (ResultSet resultSet = selectIdProfesorStatement.executeQuery()) {
                if (resultSet.next()) {
                    // idProfesor ya existe en ProfesoresGuias, no hacer nada
                    return;
                }
            }
        }

        String selectMaxCodigoSql = "SELECT MAX(CAST(RIGHT(codigo, 2) AS INT)) AS max_numero FROM ProfesoresGuias WHERE LEFT(codigo, CHARINDEX('-', codigo) - 1) = ?";
        String insertProfesorGuiaSql = "INSERT INTO ProfesoresGuias (idProfesor, codigo, coordinador) VALUES (?, ?, 0)";
        int maxNumero;

        try (PreparedStatement statement = connection.prepareStatement(selectMaxCodigoSql)) {
            statement.setString(1, idSede);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    maxNumero = resultSet.getInt("max_numero");
                } else {
                    maxNumero = 0;
                }
            }
        }
        
        int nuevoNumero = maxNumero + 1;
        String nuevoCodigo = idSede + "-" + String.format("%02d", nuevoNumero);
        
        try (PreparedStatement statement = connection.prepareStatement(insertProfesorGuiaSql)) {
            statement.setInt(1, idProfesor);
            statement.setString(2, nuevoCodigo);
            statement.executeUpdate();
        }
    }

    



}
