package entity;

import entity.Domicilio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Paciente extends Persona{
    private String email;
    private LocalDate fechaAlta;
    private Domicilio domicilio;
    private Boolean obraSocial;
    private List<Turno> historialPaciente;

    public Paciente() {
    }

    public Paciente( String nombre, String apellido, Integer dni, String email, LocalDate fechaAlta, Domicilio domicilio, Boolean obraSocial) {
        super(nombre, apellido, dni);
        this.email = email;
        this.fechaAlta = fechaAlta;
        this.domicilio = domicilio;
        this.obraSocial = obraSocial;
        this.historialPaciente = new ArrayList<>();
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public LocalDate getFechaAlta() {return fechaAlta;}

    public void setFechaAlta(LocalDate fechaAlta) {this.fechaAlta = fechaAlta;}

    public Boolean getObraSocial() {return obraSocial;}

    public void setObraSocial(Boolean obraSocial) {this.obraSocial = obraSocial;}

    public Domicilio getDomicilio() {return domicilio;}

    public void setDomicilio(Domicilio domicilio) {this.domicilio = domicilio;}

    public List<Turno> getHistorialPaciente() {
        return historialPaciente;
    }

    public void agregarTurno(Turno turno) {
        historialPaciente.add(turno);
    }

    @Override
    public String toString() {
        return "\n=== Informacion del Paciente ===\n" +
                super.toString() +
                "\n Email: " + email +
                "\n Fecha Alta: " + fechaAlta +
                "\n Domicilio: " + domicilio +
                "\n Obra Social: " + (obraSocial ? "Si" : "No");
    }
}
