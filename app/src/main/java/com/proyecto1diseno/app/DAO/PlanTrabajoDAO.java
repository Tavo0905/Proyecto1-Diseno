package com.proyecto1diseno.app.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;


import com.proyecto1diseno.app.Modelo.Actividad;

@Slf4j
public class PlanTrabajoDAO {
    private final Connection connection;

    public PlanTrabajoDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Map<String, Object>> obtenerActividades() throws SQLException {
        List<Map<String, Object>> actividades = new ArrayList<>();

        String query = "SELECT semana, nombre, idTipoActividad, fechaHora FROM Actividades";
        PreparedStatement statement = connection.prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Map<String, Object> actividad = new HashMap<>();
            actividad.put("semana", resultSet.getString("semana"));
            actividad.put("nombre", resultSet.getString("nombre"));

            int idTipoActividad = resultSet.getInt("idTipoActividad");
            String tipoActividad = obtenerTipoActividad(idTipoActividad);

            actividad.put("tipoAct", tipoActividad);
            actividad.put("FechaHora", resultSet.getTimestamp("fechaHora"));
            actividades.add(actividad);
        }

        resultSet.close();
        statement.close();

        return actividades;
    }

    private String obtenerTipoActividad(int idTipoActividad) throws SQLException {
        String tipoActividad = null;

        String query = "SELECT tipo FROM TiposActividad WHERE idTipoActividad = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idTipoActividad);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            tipoActividad = resultSet.getString("tipo");
        }

        resultSet.close();
        statement.close();

        return tipoActividad;
    }
}

