package Dominio;

public class veterinario {
    private int id_veterinario;
    private String nombre_veterinario;
    private String contraseña;
    private String especialidad;

    public veterinario() {
    }

    public veterinario(int id_veterinario, String nombre_veterinario, String contraseña, String especialidad) {
        this.id_veterinario = id_veterinario;
        this.nombre_veterinario = nombre_veterinario;
        this.contraseña = contraseña;
        this.especialidad = especialidad;
    }

    public int getId_veterinario() {
        return id_veterinario;
    }

    public void setId_veterinario(int id_veterinario) {
        this.id_veterinario = id_veterinario;
    }

    public String getNombre_veterinario() {
        return nombre_veterinario;
    }

    public void setNombre_veterinario(String nombre_veterinario) {
        this.nombre_veterinario = nombre_veterinario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "veterinario{" +
                "id_veterinario=" + id_veterinario +
                ", nombre_veterinario='" + nombre_veterinario + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
