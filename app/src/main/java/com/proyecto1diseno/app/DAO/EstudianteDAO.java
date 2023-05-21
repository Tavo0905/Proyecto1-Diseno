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
    
}
