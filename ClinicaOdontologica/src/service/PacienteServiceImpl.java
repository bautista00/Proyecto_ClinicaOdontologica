package service;

import entity.Paciente;
import repository.PacienteRepository;

import java.util.List;

public class PacienteServiceImpl implements IService<Paciente> {
    private PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository ;
    }
    public Paciente registrar(Paciente paciente) {

        if (paciente.getNombre() == null || paciente.getNombre().isEmpty()) {
            System.out.println("Error: El nombre es obligatorio");
            return null;
        }

        if (paciente.getDni() == null || paciente.getDni() <= 0) {
            System.out.println("Error: DNI invalido");
            return null;
        }

        Paciente existente = pacienteRepository.buscarPorId(paciente.getId());

        if (existente != null) {
            System.out.println("Error: Ya existe un paciente con ese ID");
            return null;
        }

        pacienteRepository.guardar(paciente);
        return paciente;
    }

    @Override
    public void guardar(Paciente p) {
        pacienteRepository.guardar(p);
    }

    @Override
    public Paciente buscarPorId(Long id) {
        return pacienteRepository.buscarPorId(id);
    }

    @Override
    public List<Paciente> listar() {
        return pacienteRepository.listar();
    }

    @Override
    public void actualizar(Paciente p) {
        pacienteRepository.actualizar(p);
    }

    @Override
    public void eliminar(Long id) {
        pacienteRepository.eliminar(id);
    }



}
