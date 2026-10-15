package Dominio;

public class vacuna {
    private int id_vacuna;
    private String tipo_vacuna;
    private String fecha_vacuna;
    private String hora_vacuna;
    private String vencimiento;
    private int id_mascota;
    private int id_consulta;

    public vacuna() {
    }

    public vacuna(int id_vacuna, String tipo_vacuna, String fecha_vacuna, String hora_vacuna, String vencimiento) {
        this(id_vacuna, tipo_vacuna, fecha_vacuna, hora_vacuna, vencimiento, 0, 0);
    }

    public vacuna(int id_vacuna, String tipo_vacuna, String fecha_vacuna, String hora_vacuna, String vencimiento,
            int id_mascota, int id_consulta) {
        this.id_vacuna = id_vacuna;
        this.tipo_vacuna = tipo_vacuna;
        this.fecha_vacuna = fecha_vacuna;
        this.hora_vacuna = hora_vacuna;
        this.vencimiento = vencimiento;
        this.id_mascota = id_mascota;
        this.id_consulta = id_consulta;
    }

    public int getId_vacuna() {
        return id_vacuna;
    }

    public void setId_vacuna(int id_vacuna) {
        this.id_vacuna = id_vacuna;
    }

    public String getTipo_vacuna() {
        return tipo_vacuna;
    }

    public void setTipo_vacuna(String tipo_vacuna) {
        this.tipo_vacuna = tipo_vacuna;
    }

    public String getFecha_vacuna() {
        return fecha_vacuna;
    }

    public void setFecha_vacuna(String fecha_vacuna) {
        this.fecha_vacuna = fecha_vacuna;
    }

    public String getHora_vacuna() {
        return hora_vacuna;
    }

    public void setHora_vacuna(String hora_vacuna) {
        this.hora_vacuna = hora_vacuna;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    @Override
    public String toString() {
        return "vacuna{" +
                "id_vacuna=" + id_vacuna +
                ", tipo_vacuna='" + tipo_vacuna + '\'' +
                ", fecha_vacuna='" + fecha_vacuna + '\'' +
                ", hora_vacuna='" + hora_vacuna + '\'' +
                ", vencimiento='" + vencimiento + '\'' +
                ", id_mascota=" + id_mascota +
                ", id_consulta=" + id_consulta +
                '}';
    }
}
