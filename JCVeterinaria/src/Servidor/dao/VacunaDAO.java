package Servidor.dao;

import Dominio.vacuna;
import Servidor.conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * DAO para la entidad vacuna.
 */
public class VacunaDAO {

    public int guardar(vacuna vacuna) throws SQLException {
        String sql = "INSERT INTO vacuna (tipo_vacuna, fecha_vacuna, hora_vacuna, vencimiento, id_mascota, id_consulta) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, vacuna.getTipo_vacuna());
            ps.setString(2, vacuna.getFecha_vacuna());
            ps.setString(3, vacuna.getHora_vacuna());
            ps.setString(4, vacuna.getVencimiento());
            ps.setInt(5, vacuna.getId_mascota());
            ps.setInt(6, vacuna.getId_consulta());

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

    public List<vacuna> listar() throws SQLException {
        String sql = "SELECT id_vacuna, tipo_vacuna, fecha_vacuna, hora_vacuna, vencimiento, id_mascota, id_consulta FROM vacuna ORDER BY fecha_vacuna";
        List<vacuna> lista = new ArrayList<>();

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new vacuna(
                        rs.getInt("id_vacuna"),
                        rs.getString("tipo_vacuna"),
                        rs.getString("fecha_vacuna"),
                        rs.getString("hora_vacuna"),
                        rs.getString("vencimiento"),
                        rs.getInt("id_mascota"),
                        rs.getInt("id_consulta")));
            }
        }
        return lista;
    }

    public boolean actualizar(vacuna vacuna) throws SQLException {
        String sql = "UPDATE vacuna SET tipo_vacuna = ?, fecha_vacuna = ?, hora_vacuna = ?, vencimiento = ?, id_mascota = ?, id_consulta = ? WHERE id_vacuna = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, vacuna.getTipo_vacuna());
            ps.setString(2, vacuna.getFecha_vacuna());
            ps.setString(3, vacuna.getHora_vacuna());
            ps.setString(4, vacuna.getVencimiento());
            ps.setInt(5, vacuna.getId_mascota());
            ps.setInt(6, vacuna.getId_consulta());
            ps.setInt(7, vacuna.getId_vacuna());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM vacuna WHERE id_vacuna = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminarD(int id) throws SQLException {
        String sql = "DELETE v FROM `vacuna` v JOIN mascota ON v.id_mascota = mascota.id_mascota WHERE mascota.id_dueño = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminarM(int id) throws SQLException {
        String sql = "DELETE v FROM `vacuna` v JOIN mascota ON v.id_mascota = mascota.id_mascota WHERE mascota.id_mascota = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}