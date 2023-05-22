package com.proyecto1diseno.app.DAO;
import com.proyecto1diseno.app.Modelo.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EstudianteDAO {

    private final Connection connection;

    public EstudianteDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Map<String, Object>> obtenerEstudiantes(String user) throws SQLException {
        List<Map<String, Object>> estudiantes = new ArrayList<>();

        String query1 = "SELECT * FROM Profesores WHERE correo = ?"; 
        PreparedStatement statement1 = connection.prepareStatement(query1);
        statement1.setString(1, user);
        ResultSet resultSet1 = statement1.executeQuery();
        if (resultSet1.next()) {
            String estudianteSede = resultSet1.getString("idSede");
            
            if (estudianteSede != null && !estudianteSede.isEmpty()) {
                String query2 = "SELECT * FROM Estudiantes WHERE idSede = ?";
                PreparedStatement statement2 = connection.prepareStatement(query2);
                statement2.setString(1, estudianteSede);
                ResultSet resultSet2 = statement2.executeQuery();
                
                while (resultSet2.next()) {
                    Map<String, Object> estudiante = new HashMap<>();
                    estudiante.put("id", resultSet2.getInt("carne"));
                    estudiante.put("nombre", resultSet2.getString("nombre")+" "+resultSet2.getString("apellido1")+" "+resultSet2.getString("apellido2"));
                    estudiante.put("correo", resultSet2.getString("correo"));
                    estudiante.put("tel", resultSet2.getString("numeroCelular"));
                    estudiantes.add(estudiante);
                }

            }
        }

        return estudiantes;
    }

    public Estudiante getEstudiante(String carnet) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Estudiante estudianteEncontrado = null;
        
        int carnetNum = Integer.parseInt(carnet);
        
        //int carnetNum = 2020087412;
        
        try {

            String query = "SELECT * FROM Estudiantes WHERE carne = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, carnetNum);
            resultSet = statement.executeQuery();
            
            // Verificar si se encontró un estudiante con el código dado
            if (resultSet.next()) {
                // Crear un objeto Estudiante con los datos obtenidos de la consulta
                estudianteEncontrado = new Estudiante();
                estudianteEncontrado.setCarnet(resultSet.getInt("carne"));
                estudianteEncontrado.setNombre(resultSet.getString("nombre"));
                estudianteEncontrado.setApellido1(resultSet.getString("apellido1"));
                estudianteEncontrado.setApellido2(resultSet.getString("apellido2"));
                estudianteEncontrado.setCorreo(resultSet.getString("correo"));
                estudianteEncontrado.setContrasena(resultSet.getString("contraseña"));
                estudianteEncontrado.setCelular(resultSet.getInt("numeroCelular"));  
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
        
        return estudianteEncontrado;
    }
    
}
