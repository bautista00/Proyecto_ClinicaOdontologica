package view;

public class DatoOdontologo extends DatoPersona {

    private String matricula;
    private String tipoEspecialidad;

    public DatoOdontologo() {
    }

    public DatoOdontologo(Long id, String nombre, String apellido, Integer dni,
                          String matricula, String tipoEspecialidad) {
        super(id, nombre, apellido, dni);
        this.matricula = matricula;
        this.tipoEspecialidad = tipoEspecialidad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipoEspecialidad() {
        return tipoEspecialidad;
    }

    public void setTipoEspecialidad(String tipoEspecialidad) {
        this.tipoEspecialidad = tipoEspecialidad;
    }
}