package Dominio;

import java.util.ArrayList;
import java.util.List;

import Servidor.dao.MascotaDAO;
import Servidor.dao.VacunaDAO;
import Servidor.dao.ConsultaDAO;

public class gestionMascota {
    private MascotaDAO mascotaDAO = new MascotaDAO();
    private VacunaDAO vacunaDAO = new VacunaDAO();
    private ConsultaDAO consultaDAO = new ConsultaDAO();

    public int guardar(mascota mascota) {
        try {
            return mascotaDAO.guardar(mascota);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<mascota> listar() {
        try {
            return mascotaDAO.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean actualizar(mascota mascota) {
        try {
            return mascotaDAO.actualizar(mascota);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        try {
            boolean resultado = vacunaDAO.eliminarM(id);
            resultado = consultaDAO.eliminarM(id);
            resultado = mascotaDAO.eliminar(id);
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
