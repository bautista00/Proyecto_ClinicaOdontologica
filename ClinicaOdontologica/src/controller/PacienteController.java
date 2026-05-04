package controller;

import entity.Domicilio;
import entity.Paciente;
import service.PacienteServiceImpl;
import view.DatoPaciente;

import java.time.LocalDate;
import java.util.List;

public class PacienteController {
    private PacienteServiceImpl pacienteService;

    public PacienteController(PacienteServiceImpl pacienteService) {
        this.pacienteService = pacienteService;
    }

    public Paciente registrar(DatoPaciente dato) {

        Domicilio domicilio = new Domicilio(
                dato.getCalle(),
                dato.getNumero(),
                dato.getLocalidad(),
                dato.getProvincia()
        );

        Paciente paciente = new Paciente(
                dato.getNombre(),
                dato.getApellido(),
                dato.getDni(),
                dato.getEmail(),
                LocalDate.now(),
                domicilio,
                dato.getObraSocial()
        );

        return pacienteService.registrar(paciente);
    }

    public Paciente buscarPorId(Long id) {
        return pacienteService.buscarPorId(id);
    }

    public List<Paciente> listar() {
        return pacienteService.listar();
    }

    public void eliminar(Long id) {
        pacienteService.eliminar(id);
    }

    public void actualizar(Paciente paciente) {
        pacienteService.actualizar(paciente);
    }
}
