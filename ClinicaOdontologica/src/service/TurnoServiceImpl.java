package service;

import entity.EstadoTurno;
import entity.Odontologo;
import entity.Paciente;
import entity.Secretaria;
import entity.Turno;
import exception.DatoInvalidoException;
import exception.OdontologoNoEncontradoException;
import exception.PacienteNoEncontradoException;
import exception.TurnoYaReservadoException;
import repository.OdontologoRepository;
import repository.PacienteRepository;
import repository.SecretariaRepository;
import repository.TurnoRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TurnoServiceImpl implements IService<Turno> {

    public static final Comparator<Turno> POR_FECHA_HORA =
            Comparator.comparing(Turno::getFecha).thenComparing(Turno::getHora);

    private TurnoRepository turnoRepository;
    private PacienteRepository pacienteRepository;
    private OdontologoRepository odontologoRepository;
    private SecretariaRepository secretariaRepository;
    private Facturador facturador;

    public TurnoServiceImpl(TurnoRepository turnoRepository,
                            PacienteRepository pacienteRepository,
                            OdontologoRepository odontologoRepository,
                            SecretariaRepository secretariaRepository,
                            Facturador facturador) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
        this.secretariaRepository = secretariaRepository;
        this.facturador = facturador;
    }

    @Override
    public Turno registrar(Turno turno) {
        validarTurnoNuevo(turno);

        if (turnoRepository.existeConflictoHorario(
                turno.getOdontologo().getId(),
                turno.getFecha(),
                turno.getHora())) {
            throw new TurnoYaReservadoException("El odontologo ya tiene un turno reservado el "
                    + turno.getFecha() + " a las " + turno.getHora() + ".");
        }

        turnoRepository.guardar(turno);
        sincronizarAlta(turno);
        return turno;
    }

    public Turno registrarTurno(Long idPaciente,
                                Long idOdontologo,
                                Long idSecretaria,
                                LocalDate fecha,
                                LocalTime hora,
                                String motivoConsulta) {

        Paciente paciente = obtenerPacienteExistente(idPaciente);
        Odontologo odontologo = obtenerOdontologoExistente(idOdontologo);
        Secretaria secretaria = obtenerSecretariaExistente(idSecretaria);

        validarFechaHora(fecha, hora);
        validarMotivo(motivoConsulta);

        if (!odontologo.puedeAtender(motivoConsulta)) {
            throw new DatoInvalidoException("El odontologo seleccionado no puede atender ese motivo de consulta.");
        }

        if (turnoRepository.existeConflictoHorario(odontologo.getId(), fecha, hora)) {
            throw new TurnoYaReservadoException("El odontologo ya tiene un turno reservado el "
                    + fecha + " a las " + hora + ".");
        }

        Turno turno = new Turno(paciente, odontologo, secretaria, fecha, hora, motivoConsulta);
        turnoRepository.guardar(turno);
        sincronizarAlta(turno);
        return turno;
    }

    @Override
    public Turno buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new DatoInvalidoException("El ID del turno debe ser un numero positivo.");
        }

        Turno turno = turnoRepository.buscarPorId(id);
        if (turno == null) {
            throw new DatoInvalidoException("No existe un turno con ID " + id + ".");
        }

        return turno;
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoRepository.listarTodos();
    }

    public List<Turno> listarPorPaciente(Long idPaciente) {
        obtenerPacienteExistente(idPaciente);
        return turnoRepository.listarTodos().stream()
                .filter(t -> t.getPaciente().getId().equals(idPaciente))
                .sorted(POR_FECHA_HORA)
                .collect(Collectors.toList());
    }

    public List<Turno> listarPorOdontologo(Long idOdontologo) {
        obtenerOdontologoExistente(idOdontologo);
        return turnoRepository.listarTodos().stream()
                .filter(t -> t.getOdontologo().getId().equals(idOdontologo))
                .sorted(POR_FECHA_HORA)
                .collect(Collectors.toList());
    }

    public List<Turno> listarPorSecretaria(Long idSecretaria) {
        obtenerSecretariaExistente(idSecretaria);
        return turnoRepository.listarTodos().stream()
                .filter(t -> t.getSecretaria().getId().equals(idSecretaria))
                .sorted(POR_FECHA_HORA)
                .collect(Collectors.toList());
    }

    public List<Turno> listarPorEstado(EstadoTurno estado) {
        if (estado == null) {
            throw new DatoInvalidoException("El estado a filtrar no puede ser nulo.");
        }
        return turnoRepository.listarTodos().stream()
                .filter(t -> t.getEstado() == estado)
                .sorted(POR_FECHA_HORA)
                .collect(Collectors.toList());
    }

    public List<Turno> buscarPorRangoFechas(LocalDate desde, LocalDate hasta) {
        if (desde == null || hasta == null) {
            throw new DatoInvalidoException("Las fechas 'desde' y 'hasta' no pueden ser nulas.");
        }
        if (desde.isAfter(hasta)) {
            throw new DatoInvalidoException("La fecha 'desde' no puede ser posterior a 'hasta'.");
        }

        return turnoRepository.listarTodos().stream()
                .filter(t -> !t.getFecha().isBefore(desde) && !t.getFecha().isAfter(hasta))
                .sorted(POR_FECHA_HORA)
                .collect(Collectors.toList());
    }

    public List<Turno> buscarPorOdontologoYPaciente(Long idOdontologo, Long idPaciente) {
        obtenerOdontologoExistente(idOdontologo);
        obtenerPacienteExistente(idPaciente);
        return turnoRepository.listarTodos().stream()
                .filter(t -> t.getOdontologo().getId().equals(idOdontologo))
                .filter(t -> t.getPaciente().getId().equals(idPaciente))
                .sorted(POR_FECHA_HORA)
                .collect(Collectors.toList());
    }

    @Override
    public Turno actualizar(Turno turno) {
        if (turno == null) {
            throw new DatoInvalidoException("El turno no puede ser nulo.");
        }

        if (turnoRepository.buscarPorId(turno.getId()) == null) {
            throw new DatoInvalidoException("No existe un turno con ID " + turno.getId() + ".");
        }

        validarTurnoActualizacion(turno);

        if (turnoRepository.existeConflictoHorarioExcluyendoTurno(
                turno.getId(),
                turno.getOdontologo().getId(),
                turno.getFecha(),
                turno.getHora())) {
            throw new TurnoYaReservadoException("El odontologo ya tiene otro turno reservado el "
                    + turno.getFecha() + " a las " + turno.getHora() + ".");
        }

        turnoRepository.actualizar(turno);
        return turno;
    }

    public Turno modificarTurno(Long idTurno,
                                Long idOdontologo,
                                Long idSecretaria,
                                LocalDate fecha,
                                LocalTime hora,
                                String motivoConsulta,
                                EstadoTurno estado) {

        Turno turno = obtenerTurnoExistente(idTurno);

        Odontologo odontologoAnterior = turno.getOdontologo();
        Secretaria secretariaAnterior = turno.getSecretaria();

        Odontologo nuevoOdontologo = obtenerOdontologoExistente(idOdontologo);
        Secretaria nuevaSecretaria = obtenerSecretariaExistente(idSecretaria);

        validarFechaHora(fecha, hora);
        validarMotivo(motivoConsulta);

        if (estado == null) {
            throw new DatoInvalidoException("El estado del turno no puede ser nulo.");
        }

        if (!nuevoOdontologo.puedeAtender(motivoConsulta)) {
            throw new DatoInvalidoException("El odontologo seleccionado no puede atender ese motivo de consulta.");
        }

        if (turnoRepository.existeConflictoHorarioExcluyendoTurno(
                turno.getId(),
                nuevoOdontologo.getId(),
                fecha,
                hora)) {
            throw new TurnoYaReservadoException("El odontologo ya tiene otro turno reservado el "
                    + fecha + " a las " + hora + ".");
        }

        turno.setOdontologo(nuevoOdontologo);
        turno.setSecretaria(nuevaSecretaria);
        turno.setFecha(fecha);
        turno.setHora(hora);
        turno.setMotivoConsulta(motivoConsulta);
        turno.setEstado(estado);

        if (!odontologoAnterior.getId().equals(nuevoOdontologo.getId())) {
            odontologoAnterior.removerTurno(turno);
            nuevoOdontologo.agregarTurno(turno);
        }

        if (!secretariaAnterior.getId().equals(nuevaSecretaria.getId())) {
            secretariaAnterior.removerTurno(turno);
            nuevaSecretaria.agregarTurno(turno);
        }

        turnoRepository.actualizar(turno);
        return turno;
    }

    public Turno cambiarEstado(Long idTurno, EstadoTurno nuevoEstado) {
        if (nuevoEstado == null) {
            throw new DatoInvalidoException("El estado del turno no puede ser nulo.");
        }

        Turno turno = obtenerTurnoExistente(idTurno);
        turno.setEstado(nuevoEstado);
        turnoRepository.actualizar(turno);
        return turno;
    }

    public Double calcularMonto(Long idTurno) {
        Turno turno = obtenerTurnoExistente(idTurno);
        return facturador.calcularMonto(turno.getPaciente(), turno.getOdontologo());
    }

    @Override
    public boolean eliminar(Long id) {
        Turno turno = obtenerTurnoExistente(id);
        sincronizarBaja(turno);
        turnoRepository.eliminar(id);
        return true;
    }

    private void validarTurnoNuevo(Turno turno) {
        if (turno == null) {
            throw new DatoInvalidoException("El turno no puede ser nulo.");
        }
        if (turno.getPaciente() == null || pacienteRepository.buscarPorId(turno.getPaciente().getId()) == null) {
            throw new PacienteNoEncontradoException("El paciente del turno no existe.");
        }
        if (turno.getOdontologo() == null || odontologoRepository.buscarPorId(turno.getOdontologo().getId()) == null) {
            throw new OdontologoNoEncontradoException("El odontologo del turno no existe.");
        }
        if (turno.getSecretaria() == null || secretariaRepository.buscarPorId(turno.getSecretaria().getId()) == null) {
            throw new DatoInvalidoException("La secretaria del turno no existe.");
        }
        validarFechaHora(turno.getFecha(), turno.getHora());
        validarMotivo(turno.getMotivoConsulta());

        if (!turno.getOdontologo().puedeAtender(turno.getMotivoConsulta())) {
            throw new DatoInvalidoException("El odontologo seleccionado no puede atender ese motivo de consulta.");
        }
    }

    private void validarTurnoActualizacion(Turno turno) {
        if (turno.getPaciente() == null || pacienteRepository.buscarPorId(turno.getPaciente().getId()) == null) {
            throw new PacienteNoEncontradoException("El paciente del turno no existe.");
        }
        if (turno.getOdontologo() == null || odontologoRepository.buscarPorId(turno.getOdontologo().getId()) == null) {
            throw new OdontologoNoEncontradoException("El odontologo del turno no existe.");
        }
        if (turno.getSecretaria() == null || secretariaRepository.buscarPorId(turno.getSecretaria().getId()) == null) {
            throw new DatoInvalidoException("La secretaria del turno no existe.");
        }
        validarFechaHora(turno.getFecha(), turno.getHora());
        validarMotivo(turno.getMotivoConsulta());

        if (turno.getEstado() == null) {
            throw new DatoInvalidoException("El estado del turno no puede ser nulo.");
        }
        if (!turno.getOdontologo().puedeAtender(turno.getMotivoConsulta())) {
            throw new DatoInvalidoException("El odontologo seleccionado no puede atender ese motivo de consulta.");
        }
    }

    private void validarFechaHora(LocalDate fecha, LocalTime hora) {
        if (fecha == null) {
            throw new DatoInvalidoException("La fecha no puede ser nula.");
        }
        if (hora == null) {
            throw new DatoInvalidoException("La hora no puede ser nula.");
        }
    }

    private void validarMotivo(String motivoConsulta) {
        if (motivoConsulta == null || motivoConsulta.isEmpty()) {
            throw new DatoInvalidoException("El motivo de consulta no puede estar vacio.");
        }
    }

    private Paciente obtenerPacienteExistente(Long idPaciente) {
        if (idPaciente == null || idPaciente <= 0) {
            throw new DatoInvalidoException("El ID del paciente debe ser un numero positivo.");
        }
        Paciente paciente = pacienteRepository.buscarPorId(idPaciente);
        if (paciente == null) {
            throw new PacienteNoEncontradoException("No existe un paciente con ID " + idPaciente + ".");
        }
        return paciente;
    }

    private Odontologo obtenerOdontologoExistente(Long idOdontologo) {
        if (idOdontologo == null || idOdontologo <= 0) {
            throw new DatoInvalidoException("El ID del odontologo debe ser un numero positivo.");
        }
        Odontologo odontologo = odontologoRepository.buscarPorId(idOdontologo);
        if (odontologo == null) {
            throw new OdontologoNoEncontradoException("No existe un odontologo con ID " + idOdontologo + ".");
        }
        return odontologo;
    }

    private Secretaria obtenerSecretariaExistente(Long idSecretaria) {
        if (idSecretaria == null || idSecretaria <= 0) {
            throw new DatoInvalidoException("El ID de la secretaria debe ser un numero positivo.");
        }
        Secretaria secretaria = secretariaRepository.buscarPorId(idSecretaria);
        if (secretaria == null) {
            throw new DatoInvalidoException("No existe una secretaria con ID " + idSecretaria + ".");
        }
        return secretaria;
    }

    private Turno obtenerTurnoExistente(Long idTurno) {
        if (idTurno == null || idTurno <= 0) {
            throw new DatoInvalidoException("El ID del turno debe ser un numero positivo.");
        }
        Turno turno = turnoRepository.buscarPorId(idTurno);
        if (turno == null) {
            throw new DatoInvalidoException("No existe un turno con ID " + idTurno + ".");
        }
        return turno;
    }

    private void sincronizarAlta(Turno turno) {
        turno.getPaciente().agregarTurno(turno);
        turno.getOdontologo().agregarTurno(turno);
        turno.getSecretaria().agregarTurno(turno);
    }

    private void sincronizarBaja(Turno turno) {
        turno.getPaciente().removerTurno(turno);
        turno.getOdontologo().removerTurno(turno);
        turno.getSecretaria().removerTurno(turno);
    }
}
