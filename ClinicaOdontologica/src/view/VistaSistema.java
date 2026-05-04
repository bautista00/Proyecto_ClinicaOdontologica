package view;

import java.util.Scanner;

public class SistemaView {

    private Scanner scanner;

    public SistemaView() {
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
        return Integer.parseInt(scanner.nextLine());
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}