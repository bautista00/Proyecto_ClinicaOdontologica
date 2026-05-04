package view;

import java.util.Scanner;

public class VistaPrincipal {
    private Scanner scanner;
    private VistaPaciente vistaPaciente;
    private VistaOdontologo vistaOdontologo;
    private VistaTurno vistaTurno;

    // ===== CONSTRUCTOR =====
    public VistaPrincipal() {
    }

    // ===== SETTERS =====
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setVistaPaciente(VistaPaciente vistaPaciente) {
        this.vistaPaciente = vistaPaciente;
    }

    public void setVistaOdontologo(VistaOdontologo vistaOdontologo) {
        this.vistaOdontologo = vistaOdontologo;
    }

    public void setVistaTurno(VistaTurno vistaTurno) {
        this.vistaTurno = vistaTurno;
    }

    // ===== GETTERS =====
    public VistaPaciente getVistaPaciente() {
        return vistaPaciente;
    }

    public VistaOdontologo getVistaOdontologo() {
        return vistaOdontologo;
    }

    public VistaTurno getVistaTurno() {
        return vistaTurno;
    }

    // ===== MENU PRINCIPAL =====
    public int mostrarMenu() {
        System.out.println("\n===== CLINICA ODONTOLOGICA =====");
        System.out.println("1. Gestionar pacientes");
        System.out.println("2. Gestionar odontologos");
        System.out.println("3. Gestionar turnos");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opcion: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        return opcion;
    }

    // ===== METODOS AUXILIARES =====
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public boolean pedirConfirmacion(String mensaje) {
        System.out.print(mensaje + " (s/n): ");
        String respuesta = scanner.nextLine();
        return respuesta.equalsIgnoreCase("s");
    }

    public void pausar() {
        System.out.print("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }

    public void cerrar() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
