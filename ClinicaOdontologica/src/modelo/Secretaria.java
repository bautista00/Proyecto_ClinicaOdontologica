package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Secretaria {
    private static final Double TARIFA_PARTICULAR = 50000.0;
    private static final Double COPAGO_OBRA_SOCIAL = 10000.0;
    private static Long contadorId = 0L;

    private Long id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private List<Turno> historialSecretaria;



    public Secretaria(String nombre, String apellido, Integer dni) {
        contadorId++;
        this.id = contadorId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.historialSecretaria = new ArrayList<>();
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

    public List<Turno> getHistorialSecretaria() {
        return historialSecretaria;
    }

    public void setHistorialSecretaria(List<Turno> historialSecretaria) {
        this.historialSecretaria = historialSecretaria;
    }

    public Turno registrarTurno(Paciente paciente, Odontologo odontologo, LocalDate fecha, LocalTime hora) {
        Turno turno = new Turno(paciente, odontologo,this,fecha,hora);
        paciente.getHistorialPaciente().add(turno);
        this.getHistorialSecretaria().add(turno);
        odontologo.getHistorialOdontologo().add(turno);

        return turno;
    }

    public Double calcularMontoAPagar(Paciente paciente) {
        if (paciente != null && Boolean.TRUE.equals(paciente.getObraSocial())) {
            return COPAGO_OBRA_SOCIAL;
        }
        return TARIFA_PARTICULAR;
    }

    @Override
    public String toString() {
        return "\n === Informacion de la Secretaria ===" +
                "\n ID: " + id +
                "\n Nombre:" + nombre + " " + apellido +
                "\n DNI: " + dni;

    }

}