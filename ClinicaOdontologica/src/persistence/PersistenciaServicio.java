package persistence;

import entity.Odontologo;
import entity.Paciente;
import entity.Secretaria;
import entity.Turno;
import repository.OdontologoRepository;
import repository.PacienteRepository;
import repository.SecretariaRepository;
import repository.TurnoRepository;

import java.io.*;

public class PersistenciaServicio {

    private static final String DIR = "datos/";
    private static final String PACIENTES_FILE    = DIR + "pacientes.dat";
    private static final String ODONTOLOGOS_FILE  = DIR + "odontologos.dat";
    private static final String SECRETARIAS_FILE  = DIR + "secretarias.dat";
    private static final String TURNOS_FILE       = DIR + "turnos.dat";

    public void guardar(PacienteRepository pacienteRepo,
                        OdontologoRepository odontologoRepo,
                        SecretariaRepository secretariaRepo,
                        TurnoRepository turnoRepo) {
        new File(DIR).mkdirs();
        serializar(pacienteRepo,   PACIENTES_FILE);
        serializar(odontologoRepo, ODONTOLOGOS_FILE);
        serializar(secretariaRepo, SECRETARIAS_FILE);
        serializar(turnoRepo,      TURNOS_FILE);
    }

    public PacienteRepository cargarPacientes() {
        PacienteRepository repo = (PacienteRepository) deserializar(PACIENTES_FILE);
        if (repo != null) {
            long maxId = repo.listarTodos().stream().mapToLong(Paciente::getId).max().orElse(0L);
            Paciente.setContadorId(maxId);
            return repo;
        }
        return new PacienteRepository();
    }

    public OdontologoRepository cargarOdontologos() {
        OdontologoRepository repo = (OdontologoRepository) deserializar(ODONTOLOGOS_FILE);
        if (repo != null) {
            long maxId = repo.listarTodos().stream().mapToLong(Odontologo::getId).max().orElse(0L);
            Odontologo.setContadorId(maxId);
            return repo;
        }
        return new OdontologoRepository();
    }

    public SecretariaRepository cargarSecretarias() {
        SecretariaRepository repo = (SecretariaRepository) deserializar(SECRETARIAS_FILE);
        if (repo != null) {
            long maxId = repo.listarTodos().stream().mapToLong(Secretaria::getId).max().orElse(0L);
            Secretaria.setContadorId(maxId);
            return repo;
        }
        return new SecretariaRepository();
    }

    public TurnoRepository cargarTurnos() {
        TurnoRepository repo = (TurnoRepository) deserializar(TURNOS_FILE);
        if (repo != null) {
            long maxId = repo.listarTodos().stream().mapToLong(Turno::getId).max().orElse(0L);
            Turno.setContadorId(maxId);
            return repo;
        }
        return new TurnoRepository();
    }

    private void serializar(Object objeto, String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(objeto);
        } catch (IOException e) {
            System.err.println("Error al guardar " + archivo + ": " + e.getMessage());
        }
    }

    private Object deserializar(String archivo) {
        File file = new File(archivo);
        if (!file.exists()) return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar " + archivo + ": " + e.getMessage());
            return null;
        }
    }
}
