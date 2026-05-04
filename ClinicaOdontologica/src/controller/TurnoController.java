package controller;


import entity.Odontologo;
import entity.Paciente;
import entity.Secretaria;
import entity.Turno;
import service.OdontologoServiceImpl;
import service.PacienteServiceImpl;
import service.TurnoServiceImpl;
import view.DatoTurno;

import java.time.LocalDate;
import java.time.LocalTime;

public class TurnoController {
    private TurnoServiceImpl turnoService;
    private PacienteServiceImpl pacienteService;
    private OdontologoServiceImpl odontologoService;

    public TurnoController(TurnoServiceImpl turnoService,
                           PacienteServiceImpl pacienteService,
                           OdontologoServiceImpl odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    public Turno registrar(DatoTurno dato) {

        Paciente paciente = pacienteService.buscarPorId(dato.getIdPaciente());

        if (paciente == null) {
            System.out.println("Error: No existe un paciente con ese ID");
            return null;
        }

        Odontologo odontologo = odontologoService.buscarPorId(dato.getIdOdontologo());

        if (odontologo == null) {
            System.out.println("Error: No existe un odontologo con ese ID");
            return null;
        }

        Secretaria secretaria = new Secretaria(
                dato.getNombreSecretaria(),
                "",
                dato.getDniSecretaria()
        );

        LocalDate fecha = LocalDate.of(dato.getAnio(), dato.getMes(), dato.getDia());
        LocalTime hora = LocalTime.of(dato.getHora(), dato.getMinuto());

        Turno turno = new Turno(
                paciente,
                odontologo,
                secretaria,
                fecha,
                hora,
                dato.getMotivo()
        );

        return turnoService.registrar(turno);
    }

    public Turno buscarPorId(Long id) {
        return turnoService.buscarPorId(id);
    }

    public void listar() {
        for (Turno turno : turnoService.listar()) {
            System.out.println(turno);
        }
    }

    public void cambiarEstado(Long id, entity.EstadoTurno estado) {
        turnoService.cambiarEstado(id, estado);
    }

    public void eliminar(Long id) {
        turnoService.eliminar(id);
    }

    public double calcularMonto(Long idTurno) {
        return turnoService.calcularMonto(idTurno);
    }

}
