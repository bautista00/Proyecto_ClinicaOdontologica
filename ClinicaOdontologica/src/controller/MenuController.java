package controller;

import entity.Endodoncista;
import entity.EstadoTurno;
import entity.Odontologo;
import entity.OdontologoGeneral;
import entity.Ortodoncista;
import entity.Paciente;
import entity.Secretaria;
import entity.Turno;
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
                    vistaMenu.mostrarMensaje("Opcion invalida.");
                    vistaMenu.pausar();
            }
        }
    }

    private void gestionarPacientes() {
        int opcion;

        do {
            opcion = vistaPaciente.mostrarMenu();

            switch (opcion) {
                case 1:
                    DatoPaciente nuevoPaciente = vistaPaciente.pedirDatosPaciente();
                    Paciente pacienteRegistrado = pacienteController.registrarPaciente(
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
                    if (pacienteRegistrado != null) {
                        vistaPaciente.mostrarMensaje("Paciente registrado correctamente.");
                        vistaPaciente.mostrarPaciente(pacienteRegistrado);
                    }
                    break;

                case 2:
                    Long idPaciente = vistaPaciente.pedirIdPaciente();
                    vistaPaciente.mostrarPaciente(pacienteController.buscarPacientePorId(idPaciente));
                    break;

                case 3:
                    Integer dniPaciente = vistaPaciente.pedirDniPaciente();
                    vistaPaciente.mostrarPaciente(pacienteController.buscarPacientePorDni(dniPaciente));
                    break;

                case 4:
                    vistaPaciente.mostrarPacientes(pacienteController.listarPacientes());
                    break;

                case 5:
                    DatoPaciente pacienteActualizado = vistaPaciente.pedirDatosPacienteActualizado();
                    Paciente pacienteModificado = pacienteController.actualizarPaciente(
                            pacienteActualizado.getId(),
                            pacienteActualizado.getNombre(),
                            pacienteActualizado.getApellido(),
                            pacienteActualizado.getDni(),
                            pacienteActualizado.getEmail(),
                            pacienteActualizado.getCalle(),
                            pacienteActualizado.getNumero(),
                            pacienteActualizado.getLocalidad(),
                            pacienteActualizado.getProvincia(),
                            pacienteActualizado.getObraSocial()
                    );
                    if (pacienteModificado != null) {
                        vistaPaciente.mostrarMensaje("Paciente actualizado correctamente.");
                        vistaPaciente.mostrarPaciente(pacienteModificado);
                    }
                    break;

                case 6:
                    Long idEliminarPaciente = vistaPaciente.pedirIdPaciente();
                    if (vistaMenu.pedirConfirmacion("Seguro que desea eliminar el paciente?")) {
                        if (pacienteController.eliminarPaciente(idEliminarPaciente)) {
                            vistaPaciente.mostrarMensaje("Paciente eliminado correctamente.");
                        }
                    }
                    break;

                case 0:
                    break;

                default:
                    vistaPaciente.mostrarMensaje("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    private void gestionarOdontologos() {
        int opcion;

        do {
            opcion = vistaOdontologo.mostrarMenu();

            switch (opcion) {
                case 1:
                    DatoOdontologo datoOdontologo = vistaOdontologo.pedirDatosOdontologo();
                    Odontologo odontologo = null;

                    if (datoOdontologo instanceof DatoOdontologoGeneral) {
                        DatoOdontologoGeneral d = (DatoOdontologoGeneral) datoOdontologo;
                        odontologo = new OdontologoGeneral(
                                d.getNombre(),
                                d.getApellido(),
                                d.getDni(),
                                d.getMatricula()
                        );
                    } else if (datoOdontologo instanceof DatoOrtodoncista) {
                        DatoOrtodoncista d = (DatoOrtodoncista) datoOdontologo;
                        odontologo = new Ortodoncista(
                                d.getNombre(),
                                d.getApellido(),
                                d.getDni(),
                                d.getMatricula()
                        );
                    } else if (datoOdontologo instanceof DatoEndodoncista) {
                        DatoEndodoncista d = (DatoEndodoncista) datoOdontologo;
                        odontologo = new Endodoncista(
                                d.getNombre(),
                                d.getApellido(),
                                d.getDni(),
                                d.getMatricula()
                        );
                    }

                    if (odontologo != null) {
                        Odontologo odontologoRegistrado = odontologoController.registrarOdontologo(odontologo);
                        if (odontologoRegistrado != null) {
                            vistaOdontologo.mostrarMensaje("Odontologo registrado correctamente.");
                            vistaOdontologo.mostrarOdontologo(odontologoRegistrado);
                        }
                    } else {
                        vistaOdontologo.mostrarMensaje("No se pudo determinar el tipo de odontologo.");
                    }
                    break;

                case 2:
                    Long idOdontologo = vistaOdontologo.pedirIdOdontologo();
                    vistaOdontologo.mostrarOdontologo(odontologoController.buscarOdontologoPorId(idOdontologo));
                    break;

                case 3:
                    String matricula = vistaOdontologo.pedirMatriculaOdontologo();
                    vistaOdontologo.mostrarOdontologo(odontologoController.buscarOdontologoPorMatricula(matricula));
                    break;

                case 4:
                    vistaOdontologo.mostrarOdontologos(odontologoController.listarOdontologos());
                    break;

                case 5:
                    DatoOdontologo odontologoActualizado = vistaOdontologo.pedirDatosOdontologoActualizado();
                    Odontologo odontologoModificado = odontologoController.actualizarOdontologo(
                            odontologoActualizado.getId(),
                            odontologoActualizado.getNombre(),
                            odontologoActualizado.getApellido(),
                            odontologoActualizado.getDni(),
                            odontologoActualizado.getMatricula()
                    );
                    if (odontologoModificado != null) {
                        vistaOdontologo.mostrarMensaje("Odontologo actualizado correctamente.");
                        vistaOdontologo.mostrarOdontologo(odontologoModificado);
                    }
                    break;

                case 6:
                    Long idEliminarOdontologo = vistaOdontologo.pedirIdOdontologo();
                    if (vistaMenu.pedirConfirmacion("Seguro que desea eliminar el odontologo?")) {
                        if (odontologoController.eliminarOdontologo(idEliminarOdontologo)) {
                            vistaOdontologo.mostrarMensaje("Odontologo eliminado correctamente.");
                        }
                    }
                    break;

                case 0:
                    break;

                default:
                    vistaOdontologo.mostrarMensaje("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    private void gestionarSecretarias() {
        int opcion;

        do {
            opcion = vistaSecretaria.mostrarMenu();

            switch (opcion) {
                case 1:
                    DatoSecretaria nuevaSecretaria = vistaSecretaria.pedirDatosSecretaria();
                    Secretaria secretariaRegistrada = secretariaController.registrarSecretaria(
                            nuevaSecretaria.getNombre(),
                            nuevaSecretaria.getApellido(),
                            nuevaSecretaria.getDni()
                    );
                    if (secretariaRegistrada != null) {
                        vistaSecretaria.mostrarMensaje("Secretaria registrada correctamente.");
                        vistaSecretaria.mostrarSecretaria(secretariaRegistrada);
                    }
                    break;

                case 2:
                    Long idSecretaria = vistaSecretaria.pedirIdSecretaria();
                    vistaSecretaria.mostrarSecretaria(secretariaController.buscarSecretariaPorId(idSecretaria));
                    break;

                case 3:
                    Integer dniSecretaria = vistaSecretaria.pedirDniSecretaria();
                    vistaSecretaria.mostrarSecretaria(secretariaController.buscarSecretariaPorDni(dniSecretaria));
                    break;

                case 4:
                    vistaSecretaria.mostrarSecretarias(secretariaController.listarSecretarias());
                    break;

                case 5:
                    DatoSecretaria secretariaActualizada = vistaSecretaria.pedirDatosSecretariaActualizada();
                    Secretaria secretariaModificada = secretariaController.actualizarSecretaria(
                            secretariaActualizada.getId(),
                            secretariaActualizada.getNombre(),
                            secretariaActualizada.getApellido(),
                            secretariaActualizada.getDni()
                    );
                    if (secretariaModificada != null) {
                        vistaSecretaria.mostrarMensaje("Secretaria actualizada correctamente.");
                        vistaSecretaria.mostrarSecretaria(secretariaModificada);
                    }
                    break;

                case 6:
                    Long idEliminarSecretaria = vistaSecretaria.pedirIdSecretaria();
                    if (vistaMenu.pedirConfirmacion("Seguro que desea eliminar la secretaria?")) {
                        if (secretariaController.eliminarSecretaria(idEliminarSecretaria)) {
                            vistaSecretaria.mostrarMensaje("Secretaria eliminada correctamente.");
                        }
                    }
                    break;

                case 0:
                    break;

                default:
                    vistaSecretaria.mostrarMensaje("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    private void gestionarTurnos() {
        int opcion;

        do {
            opcion = vistaTurno.mostrarMenu();

            switch (opcion) {
                case 1:
                    DatoTurno nuevoTurno = vistaTurno.pedirDatosTurno();
                    Turno turnoRegistrado = turnoController.registrarTurno(
                            nuevoTurno.getIdPaciente(),
                            nuevoTurno.getIdOdontologo(),
                            nuevoTurno.getIdSecretaria(),
                            nuevoTurno.getFecha(),
                            nuevoTurno.getHora(),
                            nuevoTurno.getMotivoConsulta()
                    );
                    if (turnoRegistrado != null) {
                        vistaTurno.mostrarMensaje("Turno registrado correctamente.");
                        vistaTurno.mostrarTurno(turnoRegistrado);
                    }
                    break;

                case 2:
                    Long idTurno = vistaTurno.pedirIdTurno();
                    vistaTurno.mostrarTurno(turnoController.buscarTurnoPorId(idTurno));
                    break;

                case 3:
                    vistaTurno.mostrarTurnos(turnoController.listarTurnos());
                    break;

                case 4:
                    Long idPaciente = vistaTurno.pedirIdPaciente();
                    vistaTurno.mostrarTurnos(turnoController.listarTurnosPorPaciente(idPaciente));
                    break;

                case 5:
                    Long idOdontologo = vistaTurno.pedirIdOdontologo();
                    vistaTurno.mostrarTurnos(turnoController.listarTurnosPorOdontologo(idOdontologo));
                    break;

                case 6:
                    Long idSecretaria = vistaTurno.pedirIdSecretaria();
                    vistaTurno.mostrarTurnos(turnoController.listarTurnosPorSecretaria(idSecretaria));
                    break;

                case 7:
                    DatoTurno turnoActualizado = vistaTurno.pedirDatosTurnoActualizado();
                    Turno turnoModificado = turnoController.actualizarTurno(
                            turnoActualizado.getId(),
                            turnoActualizado.getIdOdontologo(),
                            turnoActualizado.getIdSecretaria(),
                            turnoActualizado.getFecha(),
                            turnoActualizado.getHora(),
                            turnoActualizado.getMotivoConsulta(),
                            turnoActualizado.getEstado()
                    );
                    if (turnoModificado != null) {
                        vistaTurno.mostrarMensaje("Turno actualizado correctamente.");
                        vistaTurno.mostrarTurno(turnoModificado);
                    }
                    break;

                case 8:
                    Long idTurnoEstado = vistaTurno.pedirIdTurno();
                    EstadoTurno nuevoEstado = vistaTurno.pedirEstadoTurno();
                    Turno turnoEstadoActualizado = turnoController.cambiarEstadoTurno(idTurnoEstado, nuevoEstado);
                    if (turnoEstadoActualizado != null) {
                        vistaTurno.mostrarMensaje("Estado del turno actualizado correctamente.");
                        vistaTurno.mostrarTurno(turnoEstadoActualizado);
                    }
                    break;

                case 9:
                    Long idEliminarTurno = vistaTurno.pedirIdTurno();
                    if (vistaMenu.pedirConfirmacion("Seguro que desea eliminar el turno?")) {
                        if (turnoController.eliminarTurno(idEliminarTurno)) {
                            vistaTurno.mostrarMensaje("Turno eliminado correctamente.");
                        }
                    }
                    break;

                case 10:
                    Long idTurnoMonto = vistaTurno.pedirIdTurno();
                    vistaTurno.mostrarMonto(turnoController.calcularMontoTurno(idTurnoMonto));
                    break;

                case 0:
                    break;

                default:
                    vistaTurno.mostrarMensaje("Opcion invalida.");
            }
        } while (opcion != 0);
    }
}