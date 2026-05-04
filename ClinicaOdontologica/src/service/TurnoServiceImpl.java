package service;

import entity.EstadoTurno;
import entity.Odontologo;
import entity.Paciente;
import entity.Secretaria;
import entity.Turno;
import repository.OdontologoRepository;
import repository.PacienteRepository;
import repository.SecretariaRepository;
import repository.TurnoRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TurnoServiceImpl implements IService<Turno> {

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
        if (!validarTurnoNuevo(turno)) {
            return null;
        }

        if (turnoRepository.existeConflictoHorario(
                turno.getOdontologo().getId(),
                turno.getFecha(),
                turno.getHora())) {
            System.out.println("Error: el odontologo ya tiene un turno asignado en esa fecha y hora.");
            return null;
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
        if (paciente == null) {
            return null;
        }

        Odontologo odontologo = obtenerOdontologoExistente(idOdontologo);
        if (odontologo == null) {
            return null;
        }

        Secretaria secretaria = obtenerSecretariaExistente(idSecretaria);
        if (secretaria == null) {
            return null;
        }

        if (!validarFechaHora(fecha, hora) || !validarMotivo(motivoConsulta)) {
            return null;
        }

        if (!odontologo.puedeAtender(motivoConsulta)) {
            System.out.println("Error: el odontologo seleccionado no puede atender ese motivo de consulta.");
            return null;
        }

        if (turnoRepository.existeConflictoHorario(odontologo.getId(), fecha, hora)) {
            System.out.println("Error: el odontologo ya tiene un turno asignado en esa fecha y hora.");
            return null;
        }

        Turno turno = new Turno(paciente, odontologo, secretaria, fecha, hora, motivoConsulta);
        turnoRepository.guardar(turno);
        sincronizarAlta(turno);
        return turno;
    }

    @Override
    public Turno buscarPorId(Long id) {
        if (id == null || id <= 0) {
            System.out.println("Error: el ID del turno es invalido.");
            return null;
        }

        Turno turno = turnoRepository.buscarPorId(id);
        if (turno == null) {
            System.out.println("Error: no existe un turno con ese ID.");
            return null;
        }

        return turno;
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoRepository.listarTodos();
    }

    public List<Turno> listarPorPaciente(Long idPaciente) {
        Paciente paciente = obtenerPacienteExistente(idPaciente);
        if (paciente == null) {
            return null;
        }

        return turnoRepository.buscarPorPaciente(idPaciente);
    }

    public List<Turno> listarPorOdontologo(Long idOdontologo) {
        Odontologo odontologo = obtenerOdontologoExistente(idOdontologo);
        if (odontologo == null) {
            return null;
        }

        return turnoRepository.buscarPorOdontologo(idOdontologo);
    }

    public List<Turno> listarPorSecretaria(Long idSecretaria) {
        Secretaria secretaria = obtenerSecretariaExistente(idSecretaria);
        if (secretaria == null) {
            return null;
        }

        return turnoRepository.buscarPorSecretaria(idSecretaria);
    }

    @Override
    public Turno actualizar(Turno turno) {
        if (turno == null) {
            System.out.println("Error: el turno no puede ser nulo.");
            return null;
        }

        Turno turnoExistente = turnoRepository.buscarPorId(turno.getId());
        if (turnoExistente == null) {
            System.out.println("Error: no existe un turno con ese ID.");
            return null;
        }

        if (!validarTurnoActualizacion(turno)) {
            return null;
        }

        if (turnoRepository.existeConflictoHorarioExcluyendoTurno(
                turno.getId(),
                turno.getOdontologo().getId(),
                turno.getFecha(),
                turno.getHora())) {
            System.out.println("Error: el odontologo ya tiene otro turno asignado en esa fecha y hora.");
            return null;
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
        if (turno == null) {
            return null;
        }

        Odontologo odontologoAnterior = turno.getOdontologo();
        Secretaria secretariaAnterior = turno.getSecretaria();

        Odontologo nuevoOdontologo = obtenerOdontologoExistente(idOdontologo);
        if (nuevoOdontologo == null) {
            return null;
        }

        Secretaria nuevaSecretaria = obtenerSecretariaExistente(idSecretaria);
        if (nuevaSecretaria == null) {
            return null;
        }

        if (!validarFechaHora(fecha, hora) || !validarMotivo(motivoConsulta)) {
            return null;
        }

        if (estado == null) {
            System.out.println("Error: el estado del turno no puede ser nulo.");
            return null;
        }

        if (!nuevoOdontologo.puedeAtender(motivoConsulta)) {
            System.out.println("Error: el odontologo seleccionado no puede atender ese motivo de consulta.");
            return null;
        }

        if (turnoRepository.existeConflictoHorarioExcluyendoTurno(
                turno.getId(),
                nuevoOdontologo.getId(),
                fecha,
                hora)) {
            System.out.println("Error: el odontologo ya tiene otro turno asignado en esa fecha y hora.");
            return null;
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
            System.out.println("Error: el estado del turno no puede ser nulo.");
            return null;
        }

        Turno turno = obtenerTurnoExistente(idTurno);
        if (turno == null) {
            return null;
        }

        turno.setEstado(nuevoEstado);
        turnoRepository.actualizar(turno);
        return turno;
    }

    public Double calcularMonto(Long idTurno) {
        Turno turno = obtenerTurnoExistente(idTurno);
        if (turno == null) {
            return null;
        }

        return facturador.calcularMonto(turno.getPaciente(), turno.getOdontologo());
    }

    @Override
    public boolean eliminar(Long id) {
        Turno turno = obtenerTurnoExistente(id);
        if (turno == null) {
            return false;
        }

        sincronizarBaja(turno);
        turnoRepository.eliminar(id);
        return true;
    }

    private boolean validarTurnoNuevo(Turno turno) {
        if (turno == null) {
            System.out.println("Error: el turno no puede ser nulo.");
            return false;
        }

        if (turno.getPaciente() == null || pacienteRepository.buscarPorId(turno.getPaciente().getId()) == null) {
            System.out.println("Error: el paciente del turno no existe.");
            return false;
        }

        if (turno.getOdontologo() == null || odontologoRepository.buscarPorId(turno.getOdontologo().getId()) == null) {
            System.out.println("Error: el odontologo del turno no existe.");
            return false;
        }

        if (turno.getSecretaria() == null || secretariaRepository.buscarPorId(turno.getSecretaria().getId()) == null) {
            System.out.println("Error: la secretaria del turno no existe.");
            return false;
        }

        if (!validarFechaHora(turno.getFecha(), turno.getHora()) || !validarMotivo(turno.getMotivoConsulta())) {
            return false;
        }

        if (!turno.getOdontologo().puedeAtender(turno.getMotivoConsulta())) {
            System.out.println("Error: el odontologo seleccionado no puede atender ese motivo de consulta.");
            return false;
        }

        return true;
    }

    private boolean validarTurnoActualizacion(Turno turno) {
        if (turno.getPaciente() == null || pacienteRepository.buscarPorId(turno.getPaciente().getId()) == null) {
            System.out.println("Error: el paciente del turno no existe.");
            return false;
        }

        if (turno.getOdontologo() == null || odontologoRepository.buscarPorId(turno.getOdontologo().getId()) == null) {
            System.out.println("Error: el odontologo del turno no existe.");
            return false;
        }

        if (turno.getSecretaria() == null || secretariaRepository.buscarPorId(turno.getSecretaria().getId()) == null) {
            System.out.println("Error: la secretaria del turno no existe.");
            return false;
        }

        if (!validarFechaHora(turno.getFecha(), turno.getHora()) || !validarMotivo(turno.getMotivoConsulta())) {
            return false;
        }

        if (turno.getEstado() == null) {
            System.out.println("Error: el estado del turno no puede ser nulo.");
            return false;
        }

        if (!turno.getOdontologo().puedeAtender(turno.getMotivoConsulta())) {
            System.out.println("Error: el odontologo seleccionado no puede atender ese motivo de consulta.");
            return false;
        }

        return true;
    }

    private boolean validarFechaHora(LocalDate fecha, LocalTime hora) {
        if (fecha == null) {
            System.out.println("Error: la fecha del turno no puede ser nula.");
            return false;
        }

        if (hora == null) {
            System.out.println("Error: la hora del turno no puede ser nula.");
            return false;
        }

        return true;
    }

    private boolean validarMotivo(String motivoConsulta) {
        if (motivoConsulta == null || motivoConsulta.isBlank()) {
            System.out.println("Error: el motivo de consulta no puede estar vacio.");
            return false;
        }

        return true;
    }

    private Paciente obtenerPacienteExistente(Long idPaciente) {
        if (idPaciente == null || idPaciente <= 0) {
            System.out.println("Error: el ID del paciente es invalido.");
            return null;
        }

        Paciente paciente = pacienteRepository.buscarPorId(idPaciente);
        if (paciente == null) {
            System.out.println("Error: no existe un paciente con ese ID.");
            return null;
        }

        return paciente;
    }

    private Odontologo obtenerOdontologoExistente(Long idOdontologo) {
        if (idOdontologo == null || idOdontologo <= 0) {
            System.out.println("Error: el ID del odontologo es invalido.");
            return null;
        }

        Odontologo odontologo = odontologoRepository.buscarPorId(idOdontologo);
        if (odontologo == null) {
            System.out.println("Error: no existe un odontologo con ese ID.");
            return null;
        }

        return odontologo;
    }

    private Secretaria obtenerSecretariaExistente(Long idSecretaria) {
        if (idSecretaria == null || idSecretaria <= 0) {
            System.out.println("Error: el ID de la secretaria es invalido.");
            return null;
        }

        Secretaria secretaria = secretariaRepository.buscarPorId(idSecretaria);
        if (secretaria == null) {
            System.out.println("Error: no existe una secretaria con ese ID.");
            return null;
        }

        return secretaria;
    }

    private Turno obtenerTurnoExistente(Long idTurno) {
        if (idTurno == null || idTurno <= 0) {
            System.out.println("Error: el ID del turno es invalido.");
            return null;
        }

        Turno turno = turnoRepository.buscarPorId(idTurno);
        if (turno == null) {
            System.out.println("Error: no existe un turno con ese ID.");
            return null;
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