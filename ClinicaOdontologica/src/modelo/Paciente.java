package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Paciente {

    private static Long contadorId = 0L;

    private Long id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private String email;
    private LocalDate fechaAlta;
    private Domicilio domicilio;
    private Boolean obraSocial;
    private List<Turno> historialPaciente;

    public Paciente(String nombre, String apellido, Integer dni, String email, Domicilio domicilio, Boolean obraSocial) {
        contadorId++;
        this.id = contadorId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.fechaAlta = LocalDate.now();
        this.domicilio = domicilio;
        this.obraSocial = obraSocial;
        this.historialPaciente = new ArrayList<>();
    }



    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Boolean getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(Boolean obraSocial) {
        this.obraSocial = obraSocial;
    }

    public List<Turno> getHistorialPaciente() {
        return historialPaciente;
    }

    public void setHistorialPaciente(List<Turno> historialPaciente) {
        this.historialPaciente = historialPaciente;
    }

    @Override
    public String toString() {
        return "\n=== Informacion del Paciente ===" +
                "\n  ID:         " + id +
                "\n  Nombre:     " + nombre + " " + apellido +
                "\n  DNI:        " + dni +
                "\n  Email:      " + email +
                "\n  Fecha Alta: " + fechaAlta +
                "\n  Domicilio:  " + domicilio.getCalle() + " " + domicilio.getNumero() +
                ", " + domicilio.getLocalidad() + ", " + domicilio.getProvincia() +
                "\n  Obra Social: " + (Boolean.TRUE.equals(obraSocial) ? "Si" : "No");
    }
}