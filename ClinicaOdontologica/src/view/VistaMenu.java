package view;

import java.util.Scanner;

public class VistaMenu {

    private Scanner scanner;

    public VistaMenu() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenuPrincipal() {
        System.out.println("\n=== SISTEMA DE CLINICA ODONTOLOGICA ===");
        System.out.println("1. Gestion de Pacientes");
        System.out.println("2. Gestion de Odontologos");
        System.out.println("3. Gestion de Secretarias");
        System.out.println("4. Gestion de Turnos");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String mensaje) {
        System.out.println("\n[!] " + mensaje);
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