package com.proyecto1diseno.app.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    public List<Profesor> obtenerProfesores(int clave, List<Object> arreglo, String user) throws SQLException {
        List<Profesor> profesores = new ArrayList<>();
        
        String query1 = "SELECT * FROM Profesores WHERE correo = ?";
        PreparedStatement statement1 = connection.prepareStatement(query1);
        statement1.setString(1, user);
        ResultSet resultSet1 = statement1.executeQuery();
        if (resultSet1.next()) {
            String profesorSede = resultSet1.getString("idSede");
    
            // Verificar si profesorSede existe y no es vacío
            if (profesorSede != null && !profesorSede.isEmpty()) {
                String query2 = "SELECT * FROM Profesores WHERE idSede = ?";
                PreparedStatement statement2 = connection.prepareStatement(query2);
                statement2.setString(1, profesorSede);
                ResultSet resultSet2 = statement2.executeQuery();
                
                while (resultSet2.next()) {
                    int codigo = resultSet2.getInt("codigo");
                    String nombre = resultSet2.getString("nombre");
                    String idSede = resultSet2.getString("idSede");
                    String correo = resultSet2.getString("correo");
                    String contraseña = resultSet2.getString("contraseña");
                    int telOficina = resultSet2.getInt("telOficina");
                    int celular = resultSet2.getInt("celular");
                    String fotografia = resultSet2.getString("fotografia");
                    boolean guia = resultSet2.getBoolean("guia");
                    boolean coordinador = resultSet2.getBoolean("coordinador");
                    
                    Profesor profesor = new Profesor(codigo, nombre, idSede, correo, contraseña, telOficina, celular, fotografia, guia, coordinador);
                    profesores.add(profesor);
                }
            }
        }
    
        return profesores;
    }




}
