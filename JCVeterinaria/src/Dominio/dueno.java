package Dominio;

public class dueno {
    private int id_dueño;
    private String nombre;
    private String C_I;
    private String telefono;
    private String email;

    public dueno() {
    }

    public dueno(int id_dueño, String nombre, String C_I, String telefono, String email) {
        this.id_dueño = id_dueño;
        this.nombre = nombre;
        this.C_I = C_I;
        this.telefono = telefono;
        this.email = email;
    }

    public int getId_dueño() {
        return id_dueño;
    }

    public void setId_dueño(int id_dueño) {
        this.id_dueño = id_dueño;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getC_I() {
        return C_I;
    }

    public void setC_I(String C_I) {
        this.C_I = C_I;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "dueño{" +
                "id_dueño=" + id_dueño +
                ", nombre='" + nombre + '\'' +
                ", C_I='" + C_I + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
