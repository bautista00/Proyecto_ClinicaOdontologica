package view;

import entity.EstadoTurno;

import java.time.LocalDate;
import java.time.LocalTime;

public class DatoTurno {

    private Long id;
    private Long idPaciente;
    private Long idOdontologo;
    private Long idSecretaria;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivoConsulta;
    private EstadoTurno estado;

    public DatoTurno() {
    }

    public DatoTurno(Long id, Long idPaciente, Long idOdontologo, Long idSecretaria,
                     LocalDate fecha, LocalTime hora, String motivoConsulta, EstadoTurno estado) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idOdontologo = idOdontologo;
        this.idSecretaria = idSecretaria;
        this.fecha = fecha;
        this.hora = hora;
        this.motivoConsulta = motivoConsulta;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getIdOdontologo() {
        return idOdontologo;
    }

    public void setIdOdontologo(Long idOdontologo) {
        this.idOdontologo = idOdontologo;
    }

    public Long getIdSecretaria() {
        return idSecretaria;
    }

    public void setIdSecretaria(Long idSecretaria) {
        this.idSecretaria = idSecretaria;
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

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }
}