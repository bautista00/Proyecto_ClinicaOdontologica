package view;

public abstract class DatoOdontologo extends DatoPersona {

    private String matricula;

    public DatoOdontologo() {
    }

    public DatoOdontologo(Long id, String nombre, String apellido, Integer dni, String matricula) {
        super(id, nombre, apellido, dni);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}