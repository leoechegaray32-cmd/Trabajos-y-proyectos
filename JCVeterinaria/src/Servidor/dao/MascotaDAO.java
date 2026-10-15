package Servidor.dao;

import Dominio.mascota;
import Servidor.conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad mascota.
 */
public class MascotaDAO {

    public int guardar(mascota mascota) throws SQLException {
        String sql = "INSERT INTO mascotas (nombre, edad, especie, raza, sexo, peso) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, mascota.getNombre());
            ps.setInt(2, mascota.getEdad());
            ps.setString(3, mascota.getEspecie());
            ps.setString(4, mascota.getRaza());
            ps.setString(5, mascota.getSexo());
            ps.setDouble(6, mascota.getPeso());

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

    public List<mascota> listar() throws SQLException {
        String sql = "SELECT id_mascota, nombre, edad, especie, raza, sexo, peso FROM mascota ORDER BY nombre";
        List<mascota> lista = new ArrayList<>();

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new mascota(
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("especie"),
                        rs.getString("raza"),
                        rs.getString("sexo"),
                        rs.getDouble("peso"),
                        rs.getInt("id_mascota")));
            }
        }
        return lista;
    }

    public boolean actualizar(mascota mascota) throws SQLException {
        String sql = "UPDATE mascota SET nombre = ?, edad = ?, especie = ?, raza = ?, sexo = ?, peso = ? WHERE id_mascota = ?";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, mascota.getNombre());
            ps.setInt(2, mascota.getEdad());
            ps.setString(3, mascota.getEspecie());
            ps.setString(4, mascota.getRaza());
            ps.setString(5, mascota.getSexo());
            ps.setDouble(6, mascota.getPeso());
            ps.setInt(7, mascota.getId_mascota());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM `mascota` WHERE id_mascota = ?;";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminarD(int id) throws SQLException {
        String sql = "DELETE m FROM `mascota` m JOIN dueño ON m.id_dueño = dueño.id_dueño WHERE dueño.id_dueño = ?;";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}