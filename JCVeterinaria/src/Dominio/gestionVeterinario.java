package Dominio;

import java.util.ArrayList;
import java.util.List;

import Servidor.dao.VeterinarioDAO;

public class gestionVeterinario {
    private VeterinarioDAO veterinarioDAO = new VeterinarioDAO();

    public int guardar(veterinario veterinario) {
        try {
            return veterinarioDAO.guardar(veterinario);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<veterinario> listar() {
        try {
            return veterinarioDAO.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean actualizar(veterinario veterinario) {
        try {
            return veterinarioDAO.actualizar(veterinario);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        try {
            return veterinarioDAO.eliminar(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
