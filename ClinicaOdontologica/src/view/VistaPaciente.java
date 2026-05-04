package view;

import controller.PacienteController;
import entity.Paciente;

import java.util.Scanner;

public class VistaPaciente {
    private Scanner scanner;
    private PacienteController pacienteController;

    public VistaPaciente(Scanner scanner, PacienteController pacienteController) {
        this.scanner = scanner;
        this.pacienteController = pacienteController;
    }

    public void mostrarMenu() {
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n===== MENU PACIENTES =====");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Listar pacientes");
            System.out.println("3. Buscar paciente por ID");
            System.out.println("4. Eliminar paciente");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                registrarPaciente();
            } else if (opcion == 2) {
                listarPacientes();
            } else if (opcion == 3) {
                buscarPaciente();
            } else if (opcion == 4) {
                eliminarPaciente();
            } else if (opcion == 5) {
                System.out.println("Volviendo al menu principal...");
            } else {
                System.out.println("Opcion invalida.");
            }
        }
    }

    private void registrarPaciente() {
        DatoPaciente dato = new DatoPaciente();

        System.out.println("\n--- Registrar Paciente ---");

        System.out.print("Nombre: ");
        dato.setNombre(scanner.nextLine());

        System.out.print("Apellido: ");
        dato.setApellido(scanner.nextLine());

        System.out.print("DNI: ");
        dato.setDni(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Email: ");
        dato.setEmail(scanner.nextLine());

        System.out.println("\n--- Domicilio ---");

        System.out.print("Calle: ");
        dato.setCalle(scanner.nextLine());

        System.out.print("Numero: ");
        dato.setNumero(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Localidad: ");
        dato.setLocalidad(scanner.nextLine());

        System.out.print("Provincia: ");
        dato.setProvincia(scanner.nextLine());

        System.out.print("Tiene obra social? (s/n): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            dato.setObraSocial(true);
        } else {
            dato.setObraSocial(false);
        }

        Paciente paciente = pacienteController.registrar(dato);

        if (paciente != null) {
            System.out.println("Paciente registrado correctamente.");
            System.out.println(paciente);
        } else {
            System.out.println("No se pudo registrar el paciente.");
        }
    }

    private void listarPacientes() {
        System.out.println("\n--- Lista de Pacientes ---");

        for (Paciente paciente : pacienteController.listar()) {
            System.out.println(paciente);
        }
    }

    private void buscarPaciente() {
        System.out.print("Ingrese ID del paciente: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Paciente paciente = pacienteController.buscarPorId(id);

        if (paciente != null) {
            System.out.println(paciente);
        } else {
            System.out.println("No se encontro un paciente con ese ID.");
        }
    }

    private void eliminarPaciente() {
        System.out.print("Ingrese ID del paciente a eliminar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        pacienteController.eliminar(id);
        System.out.println("Paciente eliminado si existia.");
    }
}
