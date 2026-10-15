package Dominio;

import java.util.ArrayList;
import java.util.List;

import Servidor.dao.ConsultaDAO;

public class gestionConsulta {
    private ConsultaDAO consultaDAO = new ConsultaDAO();

    public int guardar(consulta consulta) {
        try {
            return consultaDAO.guardar(consulta);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<consulta> listar() {
        try {
            return consultaDAO.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean actualizar(consulta consulta) {
        try {
            return consultaDAO.actualizar(consulta);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        try {
            return consultaDAO.eliminar(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
