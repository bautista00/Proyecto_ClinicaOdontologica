package controller;

import entity.Endodoncista;
import entity.EstadoTurno;
import entity.Odontologo;
import entity.OdontologoGeneral;
import entity.Ortodoncista;
import entity.Paciente;
import entity.Secretaria;
import entity.Turno;
import exception.ClinicaException;
import view.DatoEndodoncista;
import view.DatoOdontologo;
import view.DatoOdontologoGeneral;
import view.DatoOrtodoncista;
import view.DatoPaciente;
import view.DatoSecretaria;
import view.DatoTurno;
import view.VistaMenu;
import view.VistaOdontologo;
import view.VistaPaciente;
import view.VistaSecretaria;
import view.VistaTurno;

import java.time.LocalDate;

public class MenuController {

    private VistaMenu vistaMenu;
    private VistaPaciente vistaPaciente;
    private VistaOdontologo vistaOdontologo;
    private VistaSecretaria vistaSecretaria;
    private VistaTurno vistaTurno;

    private PacienteController pacienteController;
    private OdontologoController odontologoController;
    private SecretariaController secretariaController;
    private TurnoController turnoController;

    public MenuController(VistaMenu vistaMenu,
                          VistaPaciente vistaPaciente,
                          VistaOdontologo vistaOdontologo,
                          VistaSecretaria vistaSecretaria,
                          VistaTurno vistaTurno,
                          PacienteController pacienteController,
                          OdontologoController odontologoController,
                          SecretariaController secretariaController,
                          TurnoController turnoController) {
        this.vistaMenu = vistaMenu;
        this.vistaPaciente = vistaPaciente;
        this.vistaOdontologo = vistaOdontologo;
        this.vistaSecretaria = vistaSecretaria;
        this.vistaTurno = vistaTurno;
        this.pacienteController = pacienteController;
        this.odontologoController = odontologoController;
        this.secretariaController = secretariaController;
        this.turnoController = turnoController;
    }

    public void iniciar() {
        boolean salir = false;

        while (!salir) {
            int opcion = vistaMenu.mostrarMenuPrincipal();

            switch (opcion) {
                case 1:
                    gestionarPacientes();
                    vistaMenu.pausar();
                    break;
                case 2:
                    gestionarOdontologos();
                    vistaMenu.pausar();
                    break;
                case 3:
                    gestionarSecretarias();
                    vistaMenu.pausar();
                    break;
                case 4:
                    gestionarTurnos();
                    vistaMenu.pausar();
                    break;
                case 0:
                    if (vistaMenu.pedirConfirmacion("Seguro que desea salir?")) {
                        vistaMenu.mostrarMensaje("Saliendo del sistema...");
                        salir = true;
                    }
                    break;
                default:
                    vistaMenu.mostrarError("Opcion invalida.");
                    vistaMenu.pausar();
            }
        }
    }

    private void gestionarPacientes() {
        int opcion;

        do {
            opcion = vistaPaciente.mostrarMenu();

            try {
                switch (opcion) {
                    case 1: {
                        DatoPaciente nuevoPaciente = vistaPaciente.pedirDatosPaciente();
                        Paciente p = pacienteController.registrarPaciente(
                                nuevoPaciente.getNombre(),
                                nuevoPaciente.getApellido(),
                                nuevoPaciente.getDni(),
                                nuevoPaciente.getEmail(),
                                nuevoPaciente.getCalle(),
                                nuevoPaciente.getNumero(),
                                nuevoPaciente.getLocalidad(),
                                nuevoPaciente.getProvincia(),
                                nuevoPaciente.getObraSocial()
                        );
                        vistaPaciente.mostrarMensaje("Paciente registrado correctamente.");
                        vistaPaciente.mostrarPaciente(p);
                        break;
                    }
                    case 2: {
                        Long idPaciente = vistaPaciente.pedirIdPaciente();
                        vistaPaciente.mostrarPaciente(pacienteController.buscarPacientePorId(idPaciente));
                        break;
                    }
                    case 3: {
                        Integer dniPaciente = vistaPaciente.pedirDniPaciente();
                        vistaPaciente.mostrarPaciente(pacienteController.buscarPacientePorDni(dniPaciente));
                        break;
                    }
                    case 4:
                        vistaPaciente.mostrarPacientes(pacienteController.listarPacientesOrdenadosPorApellido());
                        break;
                    case 5: {
                        DatoPaciente actualizado = vistaPaciente.pedirDatosPacienteActualizado();
                        Paciente p = pacienteController.actualizarPaciente(
                                actualizado.getId(),
                                actualizado.getNombre(),
                                actualizado.getApellido(),
                                actualizado.getDni(),
                                actualizado.getEmail(),
                                actualizado.getCalle(),
                                actualizado.getNumero(),
                                actualizado.getLocalidad(),
                                actualizado.getProvincia(),
                                actualizado.getObraSocial()
                        );
                        vistaPaciente.mostrarMensaje("Paciente actualizado correctamente.");
                        vistaPaciente.mostrarPaciente(p);
                        break;
                    }
                    case 6: {
                        Long idEliminar = vistaPaciente.pedirIdPaciente();
                        if (vistaMenu.pedirConfirmacion("Seguro que desea eliminar el paciente?")
                                && pacienteController.eliminarPaciente(idEliminar)) {
                            vistaPaciente.mostrarMensaje("Paciente eliminado correctamente.");
                        }
                        break;
                    }
                    case 0:
                        break;
                    default:
                        vistaPaciente.mostrarMensaje("Opcion invalida.");
                }
            } catch (ClinicaException e) {
                vistaMenu.mostrarError(e.getMessage());
            } catch (RuntimeException e) {
                vistaMenu.mostrarError("Error inesperado: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void gestionarOdontologos() {
        int opcion;

        do {
            opcion = vistaOdontologo.mostrarMenu();

            try {
                switch (opcion) {
                    case 1: {
                        DatoOdontologo datoOdontologo = vistaOdontologo.pedirDatosOdontologo();
                        Odontologo odontologo = construirOdontologo(datoOdontologo);
                        if (odontologo == null) {
                            vistaOdontologo.mostrarMensaje("No se pudo determinar el tipo de odontologo.");
                            break;
                        }
                        Odontologo registrado = odontologoController.registrarOdontologo(odontologo);
                        vistaOdontologo.mostrarMensaje("Odontologo registrado correctamente.");
                        vistaOdontologo.mostrarOdontologo(registrado);
                        break;
                    }
                    case 2: {
                        Long idOdontologo = vistaOdontologo.pedirIdOdontologo();
                        vistaOdontologo.mostrarOdontologo(odontologoController.buscarOdontologoPorId(idOdontologo));
                        break;
                    }
                    case 3: {
                        String matricula = vistaOdontologo.pedirMatriculaOdontologo();
                        vistaOdontologo.mostrarOdontologo(odontologoController.buscarOdontologoPorMatricula(matricula));
                        break;
                    }
                    case 4:
                        vistaOdontologo.mostrarOdontologos(odontologoController.listarOdontologos());
                        break;
                    case 5: {
                        DatoOdontologo actualizado = vistaOdontologo.pedirDatosOdontologoActualizado();
                        Odontologo modificado = odontologoController.actualizarOdontologo(
                                actualizado.getId(),
                                actualizado.getNombre(),
                                actualizado.getApellido(),
                                actualizado.getDni(),
                                actualizado.getMatricula()
                        );
                        vistaOdontologo.mostrarMensaje("Odontologo actualizado correctamente.");
                        vistaOdontologo.mostrarOdontologo(modificado);
                        break;
                    }
                    case 6: {
                        Long idEliminar = vistaOdontologo.pedirIdOdontologo();
                        if (vistaMenu.pedirConfirmacion("Seguro que desea eliminar el odontologo?")
                                && odontologoController.eliminarOdontologo(idEliminar)) {
                            vistaOdontologo.mostrarMensaje("Odontologo eliminado correctamente.");
                        }
                        break;
                    }
                    case 0:
                        break;
                    default:
                        vistaOdontologo.mostrarMensaje("Opcion invalida.");
                }
            } catch (ClinicaException e) {
                vistaMenu.mostrarError(e.getMessage());
            } catch (RuntimeException e) {
                vistaMenu.mostrarError("Error inesperado: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private Odontologo construirOdontologo(DatoOdontologo datoOdontologo) {
        if (datoOdontologo instanceof DatoOdontologoGeneral) {
            DatoOdontologoGeneral d = (DatoOdontologoGeneral) datoOdontologo;
            return new OdontologoGeneral(d.getNombre(), d.getApellido(), d.getDni(), d.getMatricula());
        }
        if (datoOdontologo instanceof DatoOrtodoncista) {
            DatoOrtodoncista d = (DatoOrtodoncista) datoOdontologo;
            return new Ortodoncista(d.getNombre(), d.getApellido(), d.getDni(), d.getMatricula());
        }
        if (datoOdontologo instanceof DatoEndodoncista) {
            DatoEndodoncista d = (DatoEndodoncista) datoOdontologo;
            return new Endodoncista(d.getNombre(), d.getApellido(), d.getDni(), d.getMatricula());
        }
        return null;
    }

    private void gestionarSecretarias() {
        int opcion;

        do {
            opcion = vistaSecretaria.mostrarMenu();

            try {
                switch (opcion) {
                    case 1: {
                        DatoSecretaria dato = vistaSecretaria.pedirDatosSecretaria();
                        Secretaria registrada = secretariaController.registrarSecretaria(
                                dato.getNombre(), dato.getApellido(), dato.getDni());
                        vistaSecretaria.mostrarMensaje("Secretaria registrada correctamente.");
                        vistaSecretaria.mostrarSecretaria(registrada);
                        break;
                    }
                    case 2: {
                        Long idSecretaria = vistaSecretaria.pedirIdSecretaria();
                        vistaSecretaria.mostrarSecretaria(secretariaController.buscarSecretariaPorId(idSecretaria));
                        break;
                    }
                    case 3: {
                        Integer dniSecretaria = vistaSecretaria.pedirDniSecretaria();
                        vistaSecretaria.mostrarSecretaria(secretariaController.buscarSecretariaPorDni(dniSecretaria));
                        break;
                    }
                    case 4:
                        vistaSecretaria.mostrarSecretarias(secretariaController.listarSecretarias());
                        break;
                    case 5: {
                        DatoSecretaria actualizada = vistaSecretaria.pedirDatosSecretariaActualizada();
                        Secretaria modificada = secretariaController.actualizarSecretaria(
                                actualizada.getId(),
                                actualizada.getNombre(),
                                actualizada.getApellido(),
                                actualizada.getDni()
                        );
                        vistaSecretaria.mostrarMensaje("Secretaria actualizada correctamente.");
                        vistaSecretaria.mostrarSecretaria(modificada);
                        break;
                    }
                    case 6: {
                        Long idEliminar = vistaSecretaria.pedirIdSecretaria();
                        if (vistaMenu.pedirConfirmacion("Seguro que desea eliminar la secretaria?")
                                && secretariaController.eliminarSecretaria(idEliminar)) {
                            vistaSecretaria.mostrarMensaje("Secretaria eliminada correctamente.");
                        }
                        break;
                    }
                    case 0:
                        break;
                    default:
                        vistaSecretaria.mostrarMensaje("Opcion invalida.");
                }
            } catch (ClinicaException e) {
                vistaMenu.mostrarError(e.getMessage());
            } catch (RuntimeException e) {
                vistaMenu.mostrarError("Error inesperado: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void gestionarTurnos() {
        int opcion;

        do {
            opcion = vistaTurno.mostrarMenu();

            try {
                switch (opcion) {
                    case 1: {
                        DatoTurno nuevoTurno = vistaTurno.pedirDatosTurno();
                        Paciente paciente = pacienteController.buscarPacientePorDni(nuevoTurno.getDniPaciente());
                        Odontologo odontologo = odontologoController.buscarOdontologoPorMatricula(nuevoTurno.getMatriculaOdontologo());
                        Secretaria secretaria = secretariaController.buscarSecretariaPorDni(nuevoTurno.getDniSecretaria());

                        Turno turno = turnoController.registrarTurno(
                                paciente.getId(),
                                odontologo.getId(),
                                secretaria.getId(),
                                nuevoTurno.getFecha(),
                                nuevoTurno.getHora(),
                                nuevoTurno.getMotivoConsulta()
                        );
                        vistaTurno.mostrarMensaje("Turno registrado correctamente.");
                        vistaTurno.mostrarTurno(turno);
                        break;
                    }
                    case 2: {
                        Long idTurno = vistaTurno.pedirIdTurno();
                        vistaTurno.mostrarTurno(turnoController.buscarTurnoPorId(idTurno));
                        break;
                    }
                    case 3:
                        vistaTurno.mostrarTurnos(turnoController.listarTurnos());
                        break;
                    case 4: {
                        Long idPaciente = vistaTurno.pedirIdPaciente();
                        vistaTurno.mostrarTurnos(turnoController.listarTurnosPorPaciente(idPaciente));
                        break;
                    }
                    case 5: {
                        Long idOdontologo = vistaTurno.pedirIdOdontologo();
                        vistaTurno.mostrarTurnos(turnoController.listarTurnosPorOdontologo(idOdontologo));
                        break;
                    }
                    case 6: {
                        Long idSecretaria = vistaTurno.pedirIdSecretaria();
                        vistaTurno.mostrarTurnos(turnoController.listarTurnosPorSecretaria(idSecretaria));
                        break;
                    }
                    case 7: {
                        DatoTurno actualizado = vistaTurno.pedirDatosTurnoActualizado();
                        Odontologo odontologoAct = odontologoController.buscarOdontologoPorMatricula(actualizado.getMatriculaOdontologo());
                        Secretaria secretariaAct = secretariaController.buscarSecretariaPorDni(actualizado.getDniSecretaria());

                        Turno modificado = turnoController.actualizarTurno(
                                actualizado.getId(),
                                odontologoAct.getId(),
                                secretariaAct.getId(),
                                actualizado.getFecha(),
                                actualizado.getHora(),
                                actualizado.getMotivoConsulta(),
                                actualizado.getEstado()
                        );
                        vistaTurno.mostrarMensaje("Turno actualizado correctamente.");
                        vistaTurno.mostrarTurno(modificado);
                        break;
                    }
                    case 8: {
                        Long idTurnoEstado = vistaTurno.pedirIdTurno();
                        EstadoTurno nuevoEstado = vistaTurno.pedirEstadoTurno();
                        Turno t = turnoController.cambiarEstadoTurno(idTurnoEstado, nuevoEstado);
                        vistaTurno.mostrarMensaje("Estado del turno actualizado correctamente.");
                        vistaTurno.mostrarTurno(t);
                        break;
                    }
                    case 9: {
                        Long idEliminar = vistaTurno.pedirIdTurno();
                        if (vistaMenu.pedirConfirmacion("Seguro que desea eliminar el turno?")
                                && turnoController.eliminarTurno(idEliminar)) {
                            vistaTurno.mostrarMensaje("Turno eliminado correctamente.");
                        }
                        break;
                    }
                    case 10: {
                        Long idTurnoMonto = vistaTurno.pedirIdTurno();
                        vistaTurno.mostrarMonto(turnoController.calcularMontoTurno(idTurnoMonto));
                        break;
                    }
                    case 11: {
                        LocalDate desde = vistaTurno.pedirFecha("Fecha desde (yyyy-MM-dd): ");
                        LocalDate hasta = vistaTurno.pedirFecha("Fecha hasta (yyyy-MM-dd): ");
                        vistaTurno.mostrarTurnos(turnoController.buscarTurnosPorRangoFechas(desde, hasta));
                        break;
                    }
                    case 12: {
                        Long idOdontologo = vistaTurno.pedirIdOdontologo();
                        Long idPaciente = vistaTurno.pedirIdPaciente();
                        vistaTurno.mostrarTurnos(turnoController.buscarTurnosPorOdontologoYPaciente(idOdontologo, idPaciente));
                        break;
                    }
                    case 0:
                        break;
                    default:
                        vistaTurno.mostrarMensaje("Opcion invalida.");
                }
            } catch (ClinicaException e) {
                vistaMenu.mostrarError(e.getMessage());
            } catch (RuntimeException e) {
                vistaMenu.mostrarError("Error inesperado: " + e.getMessage());
            }
        } while (opcion != 0);
    }
}
