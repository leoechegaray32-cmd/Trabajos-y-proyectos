package Servidor.dao;

import Dominio.dueno;
import Servidor.conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad dueno.
 */
public class DuenoDAO {

    public int guardar(dueno dueno) throws SQLException {
        String sql = "INSERT INTO `dueño` (nombre, `C.I`, telefono, email) VALUES (?, ?, ?, ?)";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, dueno.getNombre());
            ps.setString(2, dueno.getC_I());
            ps.setString(3, dueno.getTelefono());
            ps.setString(4, dueno.getEmail());

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

    public List<dueno> listar() throws SQLException {
        String sql = "SELECT id_dueño, nombre, `C.I`, telefono, email FROM `dueño` ORDER BY nombre";
        List<dueno> lista = new ArrayList<>();

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new dueno(
                        rs.getInt("id_dueño"),
                        rs.getString("nombre"),
                        rs.getString("C.I"),
                        rs.getString("telefono"),
                        rs.getString("email")));
            }
        }
        return lista;
    }

    public boolean actualizar(dueno dueno) throws SQLException {
        String sql = "UPDATE `dueño` SET nombre = ?, `C.I` = ?, telefono = ?, email = ? WHERE id_dueño = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, dueno.getNombre());
            ps.setString(2, dueno.getC_I());
            ps.setString(3, dueno.getTelefono());
            ps.setString(4, dueno.getEmail());
            ps.setInt(5, dueno.getId_dueño());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM `dueño` WHERE dueño.id_dueño = ?;";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }
}
