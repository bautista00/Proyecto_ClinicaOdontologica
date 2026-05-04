import controller.MenuController;
import controller.OdontologoController;
import controller.PacienteController;
import controller.SecretariaController;
import controller.TurnoController;
import repository.OdontologoRepository;
import repository.PacienteRepository;
import repository.SecretariaRepository;
import repository.TurnoRepository;
import service.Facturador;
import service.OdontologoServiceImpl;
import service.PacienteServiceImpl;
import service.SecretariaServiceImpl;
import service.TurnoServiceImpl;
import view.VistaMenu;
import view.VistaOdontologo;
import view.VistaPaciente;
import view.VistaSecretaria;
import view.VistaTurno;

public class Main {

    public static void main(String[] args) {

        PacienteRepository pacienteRepository = new PacienteRepository();
        OdontologoRepository odontologoRepository = new OdontologoRepository();
        SecretariaRepository secretariaRepository = new SecretariaRepository();
        TurnoRepository turnoRepository = new TurnoRepository();

        Facturador facturador = new Facturador();

        PacienteServiceImpl pacienteService = new PacienteServiceImpl(pacienteRepository);
        OdontologoServiceImpl odontologoService = new OdontologoServiceImpl(odontologoRepository);
        SecretariaServiceImpl secretariaService = new SecretariaServiceImpl(secretariaRepository);
        TurnoServiceImpl turnoService = new TurnoServiceImpl(
                turnoRepository,
                pacienteRepository,
                odontologoRepository,
                secretariaRepository,
                facturador
        );

        PacienteController pacienteController = new PacienteController(pacienteService);
        OdontologoController odontologoController = new OdontologoController(odontologoService);
        SecretariaController secretariaController = new SecretariaController(secretariaService);
        TurnoController turnoController = new TurnoController(turnoService);

        VistaMenu vistaMenu = new VistaMenu();
        VistaPaciente vistaPaciente = new VistaPaciente();
        VistaOdontologo vistaOdontologo = new VistaOdontologo();
        VistaSecretaria vistaSecretaria = new VistaSecretaria();
        VistaTurno vistaTurno = new VistaTurno();

        MenuController menuController = new MenuController(
                vistaMenu,
                vistaPaciente,
                vistaOdontologo,
                vistaSecretaria,
                vistaTurno,
                pacienteController,
                odontologoController,
                secretariaController,
                turnoController
        );

        menuController.iniciar();
    }
}