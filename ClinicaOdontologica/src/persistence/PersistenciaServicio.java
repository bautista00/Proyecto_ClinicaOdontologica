package persistence;

import entity.*;
import repository.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class PersistenciaServicio {

    private static final String DIR             = "datos/";
    private static final String SEP             = ";";
    private static final String PACIENTES_FILE   = DIR + "pacientes.txt";
    private static final String ODONTOLOGOS_FILE = DIR + "odontologos.txt";
    private static final String SECRETARIAS_FILE = DIR + "secretarias.txt";
    private static final String TURNOS_FILE      = DIR + "turnos.txt";

    // ── GUARDAR ────────────────────────────────────────────────────────────────

    public void guardar(PacienteRepository pacienteRepo,
                        OdontologoRepository odontologoRepo,
                        SecretariaRepository secretariaRepo,
                        TurnoRepository turnoRepo) {
        new File(DIR).mkdirs();
        guardarPacientes(pacienteRepo);
        guardarOdontologos(odontologoRepo);
        guardarSecretarias(secretariaRepo);
        guardarTurnos(turnoRepo);
    }

    private void guardarPacientes(PacienteRepository repo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PACIENTES_FILE))) {
            for (Paciente p : repo.listarTodos()) {
                Domicilio d = p.getDomicilio();
                pw.println(p.getId()       + SEP + p.getNombre()     + SEP + p.getApellido() + SEP +
                           p.getDni()      + SEP + p.getEmail()       + SEP + p.getFechaAlta() + SEP +
                           d.getCalle()    + SEP + d.getNumero()      + SEP +
                           d.getLocalidad()+ SEP + d.getProvincia()   + SEP + p.getObraSocial());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar pacientes: " + e.getMessage());
        }
    }

    private void guardarOdontologos(OdontologoRepository repo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ODONTOLOGOS_FILE))) {
            for (Odontologo o : repo.listarTodos()) {
                pw.println(o.getId()       + SEP + o.getNombre()   + SEP + o.getApellido() + SEP +
                           o.getDni()      + SEP + o.getMatricula() + SEP + o.getEspecialidad());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar odontologos: " + e.getMessage());
        }
    }

    private void guardarSecretarias(SecretariaRepository repo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(SECRETARIAS_FILE))) {
            for (Secretaria s : repo.listarTodos()) {
                pw.println(s.getId() + SEP + s.getNombre() + SEP + s.getApellido() + SEP + s.getDni());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar secretarias: " + e.getMessage());
        }
    }

    private void guardarTurnos(TurnoRepository repo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(TURNOS_FILE))) {
            for (Turno t : repo.listarTodos()) {
                pw.println(t.getId()                  + SEP + t.getPaciente().getId()    + SEP +
                           t.getOdontologo().getId()  + SEP + t.getSecretaria().getId()  + SEP +
                           t.getFecha()               + SEP + t.getHora()                + SEP +
                           t.getMotivoConsulta()      + SEP + t.getEstado());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar turnos: " + e.getMessage());
        }
    }

    // ── CARGAR ─────────────────────────────────────────────────────────────────

    public PacienteRepository cargarPacientes() {
        PacienteRepository repo = new PacienteRepository();
        File archivo = new File(PACIENTES_FILE);
        if (!archivo.exists()) return repo;

        long maxId = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] p = linea.split(SEP, -1);
                long id = Long.parseLong(p[0]);
                Paciente.setContadorId(id - 1);
                Domicilio domicilio = new Domicilio(p[6], Integer.parseInt(p[7]), p[8], p[9]);
                Paciente paciente = new Paciente(p[1], p[2], Integer.parseInt(p[3]), p[4], domicilio, Boolean.parseBoolean(p[10]));
                paciente.setFechaAlta(LocalDate.parse(p[5]));
                repo.guardar(paciente);
                if (id > maxId) maxId = id;
            }
        } catch (IOException e) {
            System.err.println("Error al cargar pacientes: " + e.getMessage());
        }
        Paciente.setContadorId(maxId);
        return repo;
    }

    public OdontologoRepository cargarOdontologos() {
        OdontologoRepository repo = new OdontologoRepository();
        File archivo = new File(ODONTOLOGOS_FILE);
        if (!archivo.exists()) return repo;

        long maxId = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] p = linea.split(SEP, -1);
                long id = Long.parseLong(p[0]);
                Odontologo.setContadorId(id - 1);
                Odontologo o;
                switch (p[5]) {
                    case "Ortodoncia": o = new Ortodoncista(p[1], p[2], Integer.parseInt(p[3]), p[4]); break;
                    case "Endodoncia": o = new Endodoncista(p[1], p[2], Integer.parseInt(p[3]), p[4]); break;
                    default:           o = new OdontologoGeneral(p[1], p[2], Integer.parseInt(p[3]), p[4]); break;
                }
                repo.guardar(o);
                if (id > maxId) maxId = id;
            }
        } catch (IOException e) {
            System.err.println("Error al cargar odontologos: " + e.getMessage());
        }
        Odontologo.setContadorId(maxId);
        return repo;
    }

    public SecretariaRepository cargarSecretarias() {
        SecretariaRepository repo = new SecretariaRepository();
        File archivo = new File(SECRETARIAS_FILE);
        if (!archivo.exists()) return repo;

        long maxId = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] p = linea.split(SEP, -1);
                long id = Long.parseLong(p[0]);
                Secretaria.setContadorId(id - 1);
                Secretaria s = new Secretaria(p[1], p[2], Integer.parseInt(p[3]));
                repo.guardar(s);
                if (id > maxId) maxId = id;
            }
        } catch (IOException e) {
            System.err.println("Error al cargar secretarias: " + e.getMessage());
        }
        Secretaria.setContadorId(maxId);
        return repo;
    }

    // Recibe los repos ya cargados para resolver las referencias de Paciente, Odontologo y Secretaria
    public TurnoRepository cargarTurnos(PacienteRepository pacienteRepo,
                                        OdontologoRepository odontologoRepo,
                                        SecretariaRepository secretariaRepo) {
        TurnoRepository repo = new TurnoRepository();
        File archivo = new File(TURNOS_FILE);
        if (!archivo.exists()) return repo;

        long maxId = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                // Limite 8: el campo motivo puede contener espacios pero no ";"
                String[] p = linea.split(SEP, 8);
                long id = Long.parseLong(p[0]);
                Paciente   paciente   = pacienteRepo.buscarPorId(Long.parseLong(p[1]));
                Odontologo odontologo = odontologoRepo.buscarPorId(Long.parseLong(p[2]));
                Secretaria secretaria = secretariaRepo.buscarPorId(Long.parseLong(p[3]));
                if (paciente == null || odontologo == null || secretaria == null) continue;
                Turno.setContadorId(id - 1);
                Turno turno = new Turno(paciente, odontologo, secretaria,
                        LocalDate.parse(p[4]), LocalTime.parse(p[5]), p[6]);
                turno.setEstado(EstadoTurno.valueOf(p[7]));
                // Reconstruir listas historial en memoria
                paciente.agregarTurno(turno);
                odontologo.agregarTurno(turno);
                secretaria.agregarTurno(turno);
                repo.guardar(turno);
                if (id > maxId) maxId = id;
            }
        } catch (IOException e) {
            System.err.println("Error al cargar turnos: " + e.getMessage());
        }
        Turno.setContadorId(maxId);
        return repo;
    }
}
