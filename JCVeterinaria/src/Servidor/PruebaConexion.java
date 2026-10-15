package Servidor;

import Servidor.conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Prueba de conexion: abre la base de datos, ejecuta un SELECT
 * sobre la tabla duenos y muestra los registros encontrados.
 *
 * Compilar (desde la raiz del proyecto):
 *   javac -encoding UTF-8 -cp "src/Servidor/lib/mysql-connector-j-8.0.33.jar" -d bin Servidor/conexion/Conexion.java Servidor/conexion/PruebaConexion.java
 *
 * Ejecutar:
 *   java -cp "bin;src/Servidor/lib/mysql-connector-j-8.0.33.jar" Servidor.conexion.PruebaConexion
 *
 * Requisitos: MySQL (XAMPP) iniciado y la base veterinaria creada/importada.
 */
public class PruebaConexion {

    public static void main(String[] args) {
        String sql = "SELECT * FROM dueño";

        // try-with-resources: cierra solo la conexion, el Statement y el ResultSet
        try (Connection cn = Conexion.getConexion();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("Conexion OK a veterinaria\n");
            System.out.println("Registros en la tabla duenos:\n");

            int contador = 0;
            while (rs.next()) {
                int id = rs.getInt("id_dueño");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                String ci = rs.getString("C.I");

                System.out.println("ID: " + id
                        + " | Nombre: " + nombre
                        + " | Email: " + email
                        + " | Telefono: " + telefono
                        + " | C.I: " + ci);
                contador++;
            }

            if (contador == 0) {
                System.out.println("(La tabla duenos esta vacia)");
            } else {
                System.out.println("\nTotal: " + contador + " dueno(s)");
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("Revisa que MySQL (XAMPP) este iniciado y que la base veterinaria exista/importada");
            e.printStackTrace();
        }
    }
}
