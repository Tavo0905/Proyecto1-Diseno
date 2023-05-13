package com.proyecto1diseno.app.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.proyecto1diseno.app.Modelo.AsistenteAdmin;

public class AsistenteAdminDAO {
    private final Connection connection;

    public AsistenteAdminDAO(Connection connection) {
        this.connection = connection;
    }

    public Optional<AsistenteAdmin> validarCredenciales(String correo, String contrasena) throws SQLException {
        String sql = "SELECT * FROM asistentes_admin WHERE correo = ? AND contrasena = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, correo);
            statement.setString(2, contrasena);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    AsistenteAdmin asistenteAdmin = new AsistenteAdmin(
                        result.getString("correo"),
                        result.getString("contrasena")
                    );
                    return Optional.of(asistenteAdmin);
                } else {
                    return Optional.empty();
                }
            }
        }
    }
}
