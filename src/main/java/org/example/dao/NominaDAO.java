package org.example.dao;

import org.example.model.Nomina;
import org.example.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NominaDAO {

    public List<Nomina> listar() throws SQLException {
        List<Nomina> nominas = new ArrayList<>();

        String sql = "SELECT dni, sueldo FROM nominas";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                nominas.add(new Nomina(
                        rs.getString("dni"),
                        rs.getBigDecimal("sueldo")
                ));
            }
        }

        return nominas;
    }

    public Nomina buscarPorDni(String dni) throws SQLException {
        String sql = "SELECT dni, sueldo FROM nominas WHERE dni = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Nomina(
                            rs.getString("dni"),
                            rs.getBigDecimal("sueldo")
                    );
                }
            }
        }

        return null;
    }

    public boolean insertar(Nomina nomina) throws SQLException {
        String sql = "INSERT INTO nominas (dni, sueldo) VALUES (?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, nomina.getDni());
            ps.setBigDecimal(2, nomina.getSueldo());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Nomina nomina) throws SQLException {
        String sql = "UPDATE nominas SET sueldo = ? WHERE dni = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setBigDecimal(1, nomina.getSueldo());
            ps.setString(2, nomina.getDni());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(String dni) throws SQLException {
        String sql = "DELETE FROM nominas WHERE dni = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, dni);

            return ps.executeUpdate() > 0;
        }
    }
}
