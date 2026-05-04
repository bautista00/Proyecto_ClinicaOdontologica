package controller;


import repository.OdontologoRepository;
import repository.PacienteRepository;
import repository.TurnoRepository;
import service.OdontologoServiceImpl;
import service.PacienteServiceImpl;
import service.TurnoServiceImpl;
import view.VistaOdontologo;
import view.VistaPaciente;
import view.VistaPrincipal;
import view.VistaTurno;

import java.util.Scanner;

public class SistemaController implements Runnable {
    private VistaPrincipal vistaPrincipal;
    private boolean ejecutando;

    public SistemaController(VistaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        this.ejecutando = true;

        Scanner scanner = new Scanner(System.in);

        PacienteRepository pacienteRepository = new PacienteRepository();
        OdontologoRepository odontologoRepository = new OdontologoRepository();
        TurnoRepository turnoRepository = new TurnoRepository();

        PacienteServiceImpl pacienteService = new PacienteServiceImpl(pacienteRepository);
        OdontologoServiceImpl odontologoService = new OdontologoServiceImpl(odontologoRepository);

        TurnoServiceImpl turnoService = new TurnoServiceImpl(
                pacienteRepository,
                odontologoRepository,
                turnoRepository
        );

        PacienteController pacienteController = new PacienteController(pacienteService);
        OdontologoController odontologoController = new OdontologoController(odontologoService);

        TurnoController turnoController = new TurnoController(
                turnoService,
                pacienteService,
                odontologoService
        );

        VistaPaciente vistaPaciente = new VistaPaciente(scanner, pacienteController);
        VistaOdontologo vistaOdontologo = new VistaOdontologo(scanner, odontologoController);
        VistaTurno vistaTurno = new VistaTurno(scanner, turnoController);

        this.vistaPrincipal.setScanner(scanner);
        this.vistaPrincipal.setVistaPaciente(vistaPaciente);
        this.vistaPrincipal.setVistaOdontologo(vistaOdontologo);
        this.vistaPrincipal.setVistaTurno(vistaTurno);
    }

    @Override
    public void run() {
        vistaPrincipal.mostrarMensaje("Sistema iniciado");

        while (ejecutando) {
            int opcion = vistaPrincipal.mostrarMenu();

            if (opcion == 1) {
                vistaPrincipal.getVistaPaciente().mostrarMenu();
            } else if (opcion == 2) {
                vistaPrincipal.getVistaOdontologo().mostrarMenu();
            } else if (opcion == 3) {
                vistaPrincipal.getVistaTurno().mostrarMenu();
            } else if (opcion == 4) {
                salir();
            } else {
                vistaPrincipal.mostrarMensaje("Opcion invalida.");
            }
        }

        vistaPrincipal.cerrar();
    }

    private void salir() {
        boolean confirmar = vistaPrincipal.pedirConfirmacion("¿Esta seguro que desea salir?");

        if (confirmar) {
            ejecutando = false;
            vistaPrincipal.mostrarMensaje("Sistema finalizado");
        }
    }
}
