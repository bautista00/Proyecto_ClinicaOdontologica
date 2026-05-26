package service;

import entity.Domicilio;
import entity.Paciente;
import exception.DatoInvalidoException;
import exception.PacienteNoEncontradoException;
import repository.PacienteRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PacienteServiceImpl implements IService<Paciente> {

    private PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente registrar(Paciente paciente) {
        validarPaciente(paciente);

        if (pacienteRepository.existeDni(paciente.getDni())) {
            throw new DatoInvalidoException("Ya existe un paciente con el DNI " + paciente.getDni() + ".");
        }

        pacienteRepository.guardar(paciente);
        return paciente;
    }

    @Override
    public Paciente buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new DatoInvalidoException("El ID del paciente debe ser un numero positivo.");
        }

        Paciente paciente = pacienteRepository.buscarPorId(id);
        if (paciente == null) {
            throw new PacienteNoEncontradoException("No existe un paciente con ID " + id + ".");
        }

        return paciente;
    }

    public Paciente buscarPorDni(Integer dni) {
        if (dni == null || dni <= 0) {
            throw new DatoInvalidoException("El DNI debe ser un numero positivo.");
        }

        Paciente paciente = pacienteRepository.buscarPorDni(dni);
        if (paciente == null) {
            throw new PacienteNoEncontradoException("No existe un paciente con DNI " + dni + ".");
        }

        return paciente;
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteRepository.listarTodos();
    }

    public List<Paciente> listarOrdenadosPorApellido() {
        return pacienteRepository.listarTodos().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        validarPaciente(paciente);

        Paciente pacienteExistente = pacienteRepository.buscarPorId(paciente.getId());
        if (pacienteExistente == null) {
            throw new PacienteNoEncontradoException("No existe un paciente con ID " + paciente.getId() + ".");
        }

        Paciente pacienteConMismoDni = pacienteRepository.buscarPorDni(paciente.getDni());
        if (pacienteConMismoDni != null && !pacienteConMismoDni.getId().equals(paciente.getId())) {
            throw new DatoInvalidoException("Ya existe otro paciente con el DNI " + paciente.getDni() + ".");
        }

        pacienteRepository.actualizar(paciente);
        return paciente;
    }

    @Override
    public boolean eliminar(Long id) {
        if (id == null || id <= 0) {
            throw new DatoInvalidoException("El ID del paciente debe ser un numero positivo.");
        }

        if (pacienteRepository.buscarPorId(id) == null) {
            throw new PacienteNoEncontradoException("No existe un paciente con ID " + id + ".");
        }

        pacienteRepository.eliminar(id);
        return true;
    }

    private void validarPaciente(Paciente paciente) {
        if (paciente == null) {
            throw new DatoInvalidoException("El paciente no puede ser nulo.");
        }
        if (paciente.getNombre() == null || paciente.getNombre().isEmpty()) {
            throw new DatoInvalidoException("El nombre no puede estar vacio.");
        }
        if (!paciente.getNombre().matches("[A-Za-záéíóúÁÉÍÓÚñÑ ]+")) {
            throw new DatoInvalidoException("El nombre no puede contener numeros.");
        }
        if (paciente.getApellido() == null || paciente.getApellido().isEmpty()) {
            throw new DatoInvalidoException("El apellido no puede estar vacio.");
        }
        if (!paciente.getApellido().matches("[A-Za-záéíóúÁÉÍÓÚñÑ ]+")) {
            throw new DatoInvalidoException("El apellido no puede contener numeros.");
        }
        if (paciente.getDni() == null || paciente.getDni() <= 0) {
            throw new DatoInvalidoException("El DNI debe ser un numero positivo.");
        }
        if (paciente.getEmail() == null || paciente.getEmail().isEmpty()) {
            throw new DatoInvalidoException("El email no puede estar vacio.");
        }
        if (paciente.getObraSocial() == null) {
            throw new DatoInvalidoException("Debe indicarse si el paciente tiene obra social.");
        }
        validarDomicilio(paciente.getDomicilio());
    }

    private void validarDomicilio(Domicilio domicilio) {
        if (domicilio == null) {
            throw new DatoInvalidoException("El domicilio no puede ser nulo.");
        }
        if (domicilio.getCalle() == null || domicilio.getCalle().isEmpty()) {
            throw new DatoInvalidoException("La calle no puede estar vacia.");
        }
        if (domicilio.getNumero() == null || domicilio.getNumero() <= 0) {
            throw new DatoInvalidoException("El numero del domicilio debe ser positivo.");
        }
        if (domicilio.getLocalidad() == null || domicilio.getLocalidad().isEmpty()) {
            throw new DatoInvalidoException("La localidad no puede estar vacia.");
        }
        if (domicilio.getProvincia() == null || domicilio.getProvincia().isEmpty()) {
            throw new DatoInvalidoException("La provincia no puede estar vacia.");
        }
    }
}