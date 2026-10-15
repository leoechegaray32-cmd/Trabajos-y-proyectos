package Servidor.dao;

import Dominio.consulta;
import Servidor.conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad consulta.
 */
public class ConsultaDAO {

    public int guardar(consulta consulta) throws SQLException {
        String sql = "INSERT INTO consulta (procedimiento, fecha_consulta, hora, id_mascota, id_veterinario) VALUES (?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, consulta.getProcedimiento());
            ps.setString(2, consulta.getFecha_consulta());
            ps.setString(3, consulta.getHora());
            ps.setInt(4, consulta.getId_mascota());
            ps.setInt(5, consulta.getId_veterinario());

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

    public List<consulta> listar() throws SQLException {
        String sql = "SELECT id_consulta, procedimiento, fecha_consulta, hora, id_mascota, id_veterinario FROM consulta ORDER BY fecha_consulta";
        List<consulta> lista = new ArrayList<>();

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new consulta(
                        rs.getInt("id_consulta"),
                        rs.getString("procedimiento"),
                        rs.getString("fecha_consulta"),
                        rs.getString("hora"),
                        rs.getInt("id_mascota"),
                        rs.getInt("id_veterinario")));
            }
        }
        return lista;
    }

    public boolean actualizar(consulta consulta) throws SQLException {
        String sql = "UPDATE consulta SET procedimiento = ?, fecha_consulta = ?, hora = ?, id_mascota = ?, id_veterinario = ? WHERE id_consulta = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, consulta.getProcedimiento());
            ps.setString(2, consulta.getFecha_consulta());
            ps.setString(3, consulta.getHora());
            ps.setInt(4, consulta.getId_mascota());
            ps.setInt(5, consulta.getId_veterinario());
            ps.setInt(6, consulta.getId_consulta());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM consulta WHERE id_consulta = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
    
    public boolean eliminarD(int id) throws SQLException {
        String sql = "DELETE c FROM `consulta` c JOIN mascota ON c.id_mascota = mascota.id_mascota WHERE mascota.id_dueño = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminarM(int id) throws SQLException {
        String sql = "DELETE c FROM `consulta` c JOIN mascota ON c.id_mascota = mascota.id_mascota WHERE mascota.id_mascota = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}