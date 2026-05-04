package entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Odontologo extends Persona{
    private String matricula;
    private List<Turno> historialOdontologo;

    public Odontologo() {
    }

    public Odontologo( String nombre, String apellido, Integer dni, String matricula) {
        super(nombre, apellido, dni);
        this.matricula = matricula;
        this.historialOdontologo = new ArrayList<>();
    }
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Turno> getHistorialOdontologo() {
        return historialOdontologo;
    }

    public void agregarTurno(Turno turno) {
        historialOdontologo.add(turno);
    }

    //Metodos Abstractos
    public abstract double getTarifaBase();
    public abstract String getEspecialidad();
    public abstract boolean puedeAtender(String motivoConsulta);

    @Override
    public String toString() {
        return "\n=== Informacion del Odontologo ===\n" +
                super.toString()+
                "\n Matricula: " + matricula +
                "\n Especialidad: " + getEspecialidad();
    }
}
