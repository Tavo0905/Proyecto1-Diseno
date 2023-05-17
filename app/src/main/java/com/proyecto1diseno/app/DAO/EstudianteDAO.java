package com.proyecto1diseno.app.DAO;
import com.proyecto1diseno.app.Modelo.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    private final Connection connection;

    public EstudianteDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Estudiante> obtenerEstudiantes(int clave, List<Object> arreglo, String user) throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            
            String sql = "SELECT * FROM Estudiantes";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, clave);

            // Configura los valores de la lista 'arreglo' en el PreparedStatement
            for (int i = 0; i < arreglo.size(); i++) {
                statement.setObject(i + 2, arreglo.get(i));
            }

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Aquí se extraen los datos del ResultSet y se crea un objeto Estudiante
                int carnet = resultSet.getInt("carnet");
                String nombre = resultSet.getString("nombre");
                String correo = resultSet.getString("correo");
                int celular = resultSet.getInt("celular");
                String contrasena = resultSet.getString("contrasena");

                Estudiante estudiante = new Estudiante(carnet, nombre, correo, celular, contrasena);
                estudiantes.add(estudiante);
            }
        } finally {
            // Cierra los recursos en caso de excepción o al finalizar la consulta
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return estudiantes;
    }
    
}
