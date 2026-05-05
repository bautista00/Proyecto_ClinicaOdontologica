package view;

import entity.EstadoTurno;

import java.time.LocalDate;
import java.time.LocalTime;

public class DatoTurno {

    private Long id;
    private Integer dniPaciente;
    private String matriculaOdontologo;
    private Integer dniSecretaria;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivoConsulta;
    private EstadoTurno estado;

    public DatoTurno() {
    }

    public DatoTurno(Long id, Integer dniPaciente, String matriculaOdontologo, Integer dniSecretaria,
                     LocalDate fecha, LocalTime hora, String motivoConsulta, EstadoTurno estado) {
        this.id = id;
        this.dniPaciente = dniPaciente;
        this.matriculaOdontologo = matriculaOdontologo;
        this.dniSecretaria = dniSecretaria;
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

    public Integer getDniPaciente() {
        return dniPaciente;
    }

    public void setDniPaciente(Integer dniPaciente) {
        this.dniPaciente = dniPaciente;
    }

    public String getMatriculaOdontologo() {
        return matriculaOdontologo;
    }

    public void setMatriculaOdontologo(String matriculaOdontologo) {
        this.matriculaOdontologo = matriculaOdontologo;
    }

    public Integer getDniSecretaria() {
        return dniSecretaria;
    }

    public void setDniSecretaria(Integer dniSecretaria) {
        this.dniSecretaria = dniSecretaria;
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