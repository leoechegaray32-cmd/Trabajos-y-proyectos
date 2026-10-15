package Servidor.dao;

import Dominio.veterinario;
import Servidor.conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad veterinario.
 */
public class VeterinarioDAO {

    public int guardar(veterinario veterinario) throws SQLException {
        String sql = "INSERT INTO veterinario (nombre_veterinario, contraseña, especialidad) VALUES (?, ?, ?)";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, veterinario.getNombre_veterinario());
            ps.setString(2, veterinario.getContraseña());
            ps.setString(3, veterinario.getEspecialidad());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                return -1;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            return -1;
        }
    }

    public List<veterinario> listar() throws SQLException {
        String sql = "SELECT id_veterinario, nombre_veterinario, contraseña, especialidad FROM veterinario ORDER BY nombre_veterinario";
        List<veterinario> lista = new ArrayList<>();

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new veterinario(
                        rs.getInt("id_veterinario"),
                        rs.getString("nombre_veterinario"),
                        rs.getString("contraseña"),
                        rs.getString("especialidad")));
            }
        }
        return lista;
    }

    public boolean actualizar(veterinario veterinario) throws SQLException {
        String sql = "UPDATE veterinario SET nombre_veterinario = ?, contraseña = ?, especialidad = ? WHERE id_veterinario = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, veterinario.getNombre_veterinario());
            ps.setString(2, veterinario.getContraseña());
            ps.setString(3, veterinario.getEspecialidad());
            ps.setInt(4, veterinario.getId_veterinario());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM veterinario WHERE id_veterinario = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}