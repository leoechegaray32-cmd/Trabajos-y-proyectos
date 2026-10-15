package Dominio;

import java.util.ArrayList;
import java.util.List;

import Servidor.dao.ConsultaDAO;
import Servidor.dao.DuenoDAO;
import Servidor.dao.VacunaDAO;
import Servidor.dao.MascotaDAO;

public class gestionDueño {
private DuenoDAO duenoDAO = new DuenoDAO();
private ConsultaDAO consultaDAO = new ConsultaDAO();
private VacunaDAO vacunaDAO = new VacunaDAO();
private MascotaDAO mascotaDAO = new MascotaDAO();

    public int guardar(dueno dueno) {
        try {
            return duenoDAO.guardar(dueno);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<dueno> listar() {
        try {
            return duenoDAO.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean actualizar(dueno dueno) {
        try {
            return duenoDAO.actualizar(dueno);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        try {
            boolean resultado = consultaDAO.eliminarD(id);
            resultado = vacunaDAO.eliminarD(id);
            resultado = mascotaDAO.eliminarD(id);
            resultado = duenoDAO.eliminar(id);
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}