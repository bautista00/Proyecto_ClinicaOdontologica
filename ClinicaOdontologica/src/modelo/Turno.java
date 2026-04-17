package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {
    private static Long contadorId = 0L;

    private Long id;
    private Paciente paciente;
    private Odontologo odontologo;
    private Secretaria secretaria;
    private LocalDate fecha;
    private LocalTime hora;
    private EstadoTurno estado;

    public Turno(Paciente paciente, Odontologo odontologo, Secretaria secretaria, LocalDate fecha, LocalTime hora) {
        contadorId++;
        this.id = contadorId;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.secretaria = secretaria;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = EstadoTurno.PENDIENTE;
    }

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Secretaria getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(Secretaria secretaria) {
        this.secretaria = secretaria;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }

    public String generarMensajeRecordatorio() {
        return  "\n--- Turno ---" +
                "\nPaciente: " + paciente.getNombre() + " " + paciente.getApellido() +
                "\nOdontologo: " + odontologo.getNombre() + " " + odontologo.getApellido() +
                "\nFecha: " + fecha +
                "\nHora: " + hora +
                "\nEstado: " + estado +
                "\n----------------";
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", secretaria=" + secretaria +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", estado=" + estado +
                '}';
    }
}