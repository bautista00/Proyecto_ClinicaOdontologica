package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {
    private static Long contadorId = 0L;

    private Long id;
    private Paciente paciente;
    private Secretaria secretaria;
    private Odontologo odontologo;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivoConsulta;
    private EstadoTurno estado;

    public Turno() {
    }

    public Turno(Paciente paciente, Odontologo odontologo, Secretaria secretaria,
                 LocalDate fecha, LocalTime hora, String motivoConsulta) {
        contadorId++;
        this.id = contadorId;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.secretaria = secretaria;
        this.fecha = fecha;
        this.hora = hora;
        this.motivoConsulta = motivoConsulta;
        this.estado = EstadoTurno.PENDIENTE;
    }

    public static Long getContadorId() {return contadorId;}

    public static void setContadorId(Long contadorId) {Turno.contadorId = contadorId;}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Paciente getPaciente() {return paciente;}

    public void setPaciente(Paciente paciente) {this.paciente = paciente;}

    public Secretaria getSecretaria() {return secretaria;}

    public void setSecretaria(Secretaria secretaria) {this.secretaria = secretaria;}

    public LocalDate getFecha() {return fecha;}

    public void setFecha(LocalDate fecha) {this.fecha = fecha;}

    public LocalTime getHora() {return hora;}

    public void setHora(LocalTime hora) {this.hora = hora;}

    public EstadoTurno getEstado() {return estado;}

    public void setEstado(EstadoTurno estado) {this.estado = estado;}

    public Odontologo getOdontologo() {return odontologo;}

    public String getMotivoConsulta() {return motivoConsulta;}

    public String generarMensajeRecordatorio() {
        return "\n--- Turno ---" +
                "\n Paciente: " + paciente.getNombre() + " " + paciente.getApellido() +
                "\n Odontologo: " + odontologo.getNombre() + " " + odontologo.getApellido() +
                "\n Especialidad: " + odontologo.getEspecialidad() +
                "\n Secretaria: " + secretaria.getNombre() + " " + secretaria.getApellido() +
                "\n Motivo: " + motivoConsulta +
                "\n Fecha: " + fecha +
                "\n Hora: " + hora +
                "\n Estado: " + estado;
    }

    @Override
    public String toString() {
        return generarMensajeRecordatorio();
    }
}
