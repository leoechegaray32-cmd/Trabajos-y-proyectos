package Dominio;

import java.util.ArrayList;
import java.util.List;

import Servidor.dao.VacunaDAO;

public class gestionVacuna {
    private VacunaDAO vacunaDAO = new VacunaDAO();

    public int guardar(vacuna vacuna) {
        try {
            return vacunaDAO.guardar(vacuna);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<vacuna> listar() {
        try {
            return vacunaDAO.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean actualizar(vacuna vacuna) {
        try {
            return vacunaDAO.actualizar(vacuna);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        try {
            return vacunaDAO.eliminar(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
