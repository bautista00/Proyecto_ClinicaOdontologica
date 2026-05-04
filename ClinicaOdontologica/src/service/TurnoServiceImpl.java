package service;

import entity.EstadoTurno;
import entity.Odontologo;
import entity.Paciente;
import entity.Turno;
import repository.OdontologoRepository;
import repository.PacienteRepository;
import repository.TurnoRepository;

import java.util.List;

public class TurnoServiceImpl implements IService<Turno> {
    private TurnoRepository turnoRepository;
    private PacienteRepository pacienteRepository;
    private OdontologoRepository odontologoRepository;
    private Facturador facturador;

    public TurnoServiceImpl(PacienteRepository pacienteRepository,
                            OdontologoRepository odontologoRepository,
                            TurnoRepository turnoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
        this.turnoRepository = turnoRepository;
        this.facturador = new Facturador();
    }
    public Turno registrar(Turno turno) {

        Paciente paciente = pacienteRepository.buscarPorId(turno.getPaciente().getId());
        if (paciente == null) {
            System.out.println("Error: El paciente no existe");
            return null;
        }

        Odontologo odontologo = odontologoRepository.buscarPorId(turno.getOdontologo().getId());
        if (odontologo == null) {
            System.out.println("Error: El odontologo no existe");
            return null;
        }

        if (!odontologo.puedeAtender(turno.getMotivoConsulta())) {
            System.out.println("Error: El odontologo no atiende ese motivo");
            return null;
        }

        turnoRepository.guardar(turno);

        paciente.agregarTurno(turno);
        odontologo.agregarTurno(turno);
        turno.getSecretaria().agregarTurno(turno);

        return turno;
    }

    public void cambiarEstado(Long id, EstadoTurno estado) {
        Turno turno = turnoRepository.buscarPorId(id);

        if (turno == null) {
            System.out.println("Error: No existe un turno con ese ID");
            return;
        }

        turno.setEstado(estado);
        turnoRepository.actualizar(turno);
    }

    public double calcularMonto(Long idTurno) {
        Turno turno = turnoRepository.buscarPorId(idTurno);

        if (turno == null) {
            System.out.println("Turno no encontrado");
            return 0;
        }

        return facturador.calcularMonto(turno);
    }

    @Override
    public void guardar(Turno t) {
        turnoRepository.guardar(t);
    }

    @Override
    public Turno buscarPorId(Long id) {
        return turnoRepository.buscarPorId(id);
    }

    @Override
    public List<Turno> listar() {
        return turnoRepository.listar();
    }

    @Override
    public void actualizar(Turno t) {
        turnoRepository.actualizar(t);
    }

    @Override
    public void eliminar(Long id) {
        turnoRepository.eliminar(id);
    }
}
