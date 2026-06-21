package service;

import entity.EstadoTurno;
import entity.Odontologo;
import entity.Turno;
import exception.DatoInvalidoException;
import exception.OdontologoNoEncontradoException;
import repository.OdontologoRepository;

import java.time.LocalDate;
import java.util.List;

public class OdontologoServiceImpl implements IService<Odontologo> {

    private OdontologoRepository odontologoRepository;

    public OdontologoServiceImpl(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        validarOdontologo(odontologo);

        if (odontologoRepository.existeMatricula(odontologo.getMatricula())) {
            throw new DatoInvalidoException("Ya existe un odontologo con la matricula " + odontologo.getMatricula() + ".");
        }

        odontologoRepository.guardar(odontologo);
        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new DatoInvalidoException("El ID del odontologo debe ser un numero positivo.");
        }

        Odontologo odontologo = odontologoRepository.buscarPorId(id);
        if (odontologo == null) {
            throw new OdontologoNoEncontradoException("No existe un odontologo con ID " + id + ".");
        }

        return odontologo;
    }

    public Odontologo buscarPorMatricula(String matricula) {
        if (matricula == null || matricula.isEmpty()) {
            throw new DatoInvalidoException("La matricula no puede estar vacia.");
        }

        Odontologo odontologo = odontologoRepository.buscarPorMatricula(matricula);
        if (odontologo == null) {
            throw new OdontologoNoEncontradoException("No existe un odontologo con matricula " + matricula + ".");
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoRepository.listarTodos();
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        validarOdontologo(odontologo);

        Odontologo odontologoExistente = odontologoRepository.buscarPorId(odontologo.getId());
        if (odontologoExistente == null) {
            throw new OdontologoNoEncontradoException("No existe un odontologo con ID " + odontologo.getId() + ".");
        }

        Odontologo odontologoConMismaMatricula = odontologoRepository.buscarPorMatricula(odontologo.getMatricula());
        if (odontologoConMismaMatricula != null && !odontologoConMismaMatricula.getId().equals(odontologo.getId())) {
            throw new DatoInvalidoException("Ya existe otro odontologo con la matricula " + odontologo.getMatricula() + ".");
        }

        odontologoRepository.actualizar(odontologo);
        return odontologo;
    }

    @Override
    public boolean eliminar(Long id) {
        if (id == null || id <= 0) {
            throw new DatoInvalidoException("El ID del odontologo debe ser un numero positivo.");
        }

        Odontologo odontologo = odontologoRepository.buscarPorId(id);
        if (odontologo == null) {
            throw new OdontologoNoEncontradoException("No existe un odontologo con ID " + id + ".");
        }

        if (tieneTurnosFuturos(odontologo.getHistorialOdontologo())) {
            throw new DatoInvalidoException(
                    "No se puede eliminar el odontologo porque tiene turnos a futuro. " +
                    "Cancele o complete esos turnos antes de eliminarlo.");
        }

        odontologoRepository.eliminar(id);
        return true;
    }

    // Hay turno a futuro si su fecha es hoy o posterior y sigue activo (PENDIENTE o CONFIRMADO)
    private boolean tieneTurnosFuturos(List<Turno> turnos) {
        if (turnos == null) return false;
        LocalDate hoy = LocalDate.now();
        return turnos.stream().anyMatch(t ->
                !t.getFecha().isBefore(hoy)
                && (t.getEstado() == EstadoTurno.PENDIENTE || t.getEstado() == EstadoTurno.CONFIRMADO));
    }

    private void validarOdontologo(Odontologo odontologo) {
        if (odontologo == null) {
            throw new DatoInvalidoException("El odontologo no puede ser nulo.");
        }
        if (odontologo.getNombre() == null || odontologo.getNombre().isEmpty()) {
            throw new DatoInvalidoException("El nombre no puede estar vacio.");
        }
        if (!odontologo.getNombre().matches("[A-Za-záéíóúÁÉÍÓÚñÑ ]+")) {
            throw new DatoInvalidoException("El nombre no puede contener numeros.");
        }
        if (odontologo.getApellido() == null || odontologo.getApellido().isEmpty()) {
            throw new DatoInvalidoException("El apellido no puede estar vacio.");
        }
        if (!odontologo.getApellido().matches("[A-Za-záéíóúÁÉÍÓÚñÑ ]+")) {
            throw new DatoInvalidoException("El apellido no puede contener numeros.");
        }
        if (odontologo.getDni() == null || odontologo.getDni() <= 0) {
            throw new DatoInvalidoException("El DNI debe ser un numero positivo.");
        }
        if (odontologo.getMatricula() == null || odontologo.getMatricula().isEmpty()) {
            throw new DatoInvalidoException("La matricula no puede estar vacia.");
        }
    }
}