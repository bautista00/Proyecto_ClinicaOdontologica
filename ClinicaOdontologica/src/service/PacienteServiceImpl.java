package service;

import entity.Domicilio;
import entity.Paciente;
import repository.PacienteRepository;

import java.util.List;

public class PacienteServiceImpl implements IService<Paciente> {

    private PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente registrar(Paciente paciente) {
        if (!validarPaciente(paciente)) {
            return null;
        }

        if (pacienteRepository.existeDni(paciente.getDni())) {
            System.out.println("Error: ya existe un paciente con ese DNI.");
            return null;
        }

        pacienteRepository.guardar(paciente);
        return paciente;
    }

    @Override
    public Paciente buscarPorId(Long id) {
        if (id == null || id <= 0) {
            System.out.println("Error: el ID del paciente es invalido.");
            return null;
        }

        Paciente paciente = pacienteRepository.buscarPorId(id);
        if (paciente == null) {
            System.out.println("Error: no existe un paciente con ese ID.");
            return null;
        }

        return paciente;
    }

    public Paciente buscarPorDni(Integer dni) {
        if (dni == null || dni <= 0) {
            System.out.println("Error: el DNI es invalido.");
            return null;
        }

        Paciente paciente = pacienteRepository.buscarPorDni(dni);
        if (paciente == null) {
            System.out.println("Error: no existe un paciente con ese DNI.");
            return null;
        }

        return paciente;
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteRepository.listarTodos();
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        if (!validarPaciente(paciente)) {
            return null;
        }

        Paciente pacienteExistente = pacienteRepository.buscarPorId(paciente.getId());
        if (pacienteExistente == null) {
            System.out.println("Error: no existe un paciente con ese ID.");
            return null;
        }

        Paciente pacienteConMismoDni = pacienteRepository.buscarPorDni(paciente.getDni());
        if (pacienteConMismoDni != null && !pacienteConMismoDni.getId().equals(paciente.getId())) {
            System.out.println("Error: ya existe otro paciente con ese DNI.");
            return null;
        }

        pacienteRepository.actualizar(paciente);
        return paciente;
    }

    @Override
    public boolean eliminar(Long id) {
        if (id == null || id <= 0) {
            System.out.println("Error: el ID del paciente es invalido.");
            return false;
        }

        Paciente pacienteExistente = pacienteRepository.buscarPorId(id);
        if (pacienteExistente == null) {
            System.out.println("Error: no existe un paciente con ese ID.");
            return false;
        }

        pacienteRepository.eliminar(id);
        return true;
    }

    private boolean validarPaciente(Paciente paciente) {
        if (paciente == null) {
            System.out.println("Error: el paciente no puede ser nulo.");
            return false;
        }

        if (paciente.getNombre() == null || paciente.getNombre().isBlank()) {
            System.out.println("Error: el nombre del paciente no puede estar vacio.");
            return false;
        }

        if (paciente.getApellido() == null || paciente.getApellido().isBlank()) {
            System.out.println("Error: el apellido del paciente no puede estar vacio.");
            return false;
        }

        if (paciente.getDni() == null || paciente.getDni() <= 0) {
            System.out.println("Error: el DNI del paciente es invalido.");
            return false;
        }

        if (paciente.getEmail() == null || paciente.getEmail().isBlank()) {
            System.out.println("Error: el email del paciente no puede estar vacio.");
            return false;
        }

        if (paciente.getObraSocial() == null) {
            System.out.println("Error: debe indicarse si el paciente tiene obra social.");
            return false;
        }

        return validarDomicilio(paciente.getDomicilio());
    }

    private boolean validarDomicilio(Domicilio domicilio) {
        if (domicilio == null) {
            System.out.println("Error: el domicilio no puede ser nulo.");
            return false;
        }

        if (domicilio.getCalle() == null || domicilio.getCalle().isBlank()) {
            System.out.println("Error: la calle no puede estar vacia.");
            return false;
        }

        if (domicilio.getNumero() == null || domicilio.getNumero() <= 0) {
            System.out.println("Error: el numero del domicilio es invalido.");
            return false;
        }

        if (domicilio.getLocalidad() == null || domicilio.getLocalidad().isBlank()) {
            System.out.println("Error: la localidad no puede estar vacia.");
            return false;
        }

        if (domicilio.getProvincia() == null || domicilio.getProvincia().isBlank()) {
            System.out.println("Error: la provincia no puede estar vacia.");
            return false;
        }

        return true;
    }
}