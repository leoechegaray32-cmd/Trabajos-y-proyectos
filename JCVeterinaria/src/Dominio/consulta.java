package Dominio;

public class consulta {
    private int id_consulta;
    private String procedimiento;
    private String fecha_consulta;
    private String hora;
    private int id_mascota;
    private int id_veterinario;

    public consulta() {
    }

    public consulta(int id_consulta, String procedimiento, String fecha_consulta, String hora) {
        this(id_consulta, procedimiento, fecha_consulta, hora, 0, 0);
    }

    public consulta(int id_consulta, String procedimiento, String fecha_consulta, String hora,
            int id_mascota, int id_veterinario) {
        this.id_consulta = id_consulta;
        this.procedimiento = procedimiento;
        this.fecha_consulta = fecha_consulta;
        this.hora = hora;
        this.id_mascota = id_mascota;
        this.id_veterinario = id_veterinario;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public String getFecha_consulta() {
        return fecha_consulta;
    }

    public void setFecha_consulta(String fecha_consulta) {
        this.fecha_consulta = fecha_consulta;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public int getId_veterinario() {
        return id_veterinario;
    }

    public void setId_veterinario(int id_veterinario) {
        this.id_veterinario = id_veterinario;
    }

    @Override
    public String toString() {
        return "consulta{" +
                "id_consulta=" + id_consulta +
                ", procedimiento='" + procedimiento + '\'' +
                ", fecha_consulta='" + fecha_consulta + '\'' +
                ", hora='" + hora + '\'' +
                ", id_mascota=" + id_mascota +
                ", id_veterinario=" + id_veterinario +
                '}';
    }
}
