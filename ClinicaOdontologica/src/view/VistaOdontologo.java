package view;

import controller.OdontologoController;
import entity.Odontologo;

import java.util.Scanner;

public class VistaOdontologo {
    private Scanner scanner;
    private OdontologoController odontologoController;

    public VistaOdontologo(Scanner scanner, OdontologoController odontologoController) {
        this.scanner = scanner;
        this.odontologoController = odontologoController;
    }

    public void mostrarMenu() {
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n===== MENU ODONTOLOGOS =====");
            System.out.println("1. Registrar odontologo");
            System.out.println("2. Listar odontologos");
            System.out.println("3. Buscar odontologo por ID");
            System.out.println("4. Eliminar odontologo");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                registrar();
            } else if (opcion == 2) {
                listar();
            } else if (opcion == 3) {
                buscar();
            } else if (opcion == 4) {
                eliminar();
            } else if (opcion == 5) {
                System.out.println("Volviendo...");
            } else {
                System.out.println("Opcion invalida");
            }
        }
    }

    private void registrar() {
        DatoOdontologo dato = new DatoOdontologo();

        System.out.println("\n--- Registrar Odontologo ---");

        System.out.print("Nombre: ");
        dato.setNombre(scanner.nextLine());

        System.out.print("Apellido: ");
        dato.setApellido(scanner.nextLine());

        System.out.print("DNI: ");
        dato.setDni(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Matricula: ");
        dato.setMatricula(scanner.nextLine());

        System.out.println("Tipo de odontologo:");
        System.out.println("1. General");
        System.out.println("2. Ortodoncista");
        System.out.println("3. Endodoncista");

        dato.setTipo(scanner.nextInt());
        scanner.nextLine();

        Odontologo odontologo = odontologoController.registrar(dato);

        if (odontologo != null) {
            System.out.println("Odontologo registrado correctamente.");
            System.out.println(odontologo);
        } else {
            System.out.println("No se pudo registrar.");
        }
    }

    private void listar() {
        System.out.println("\n--- Lista de Odontologos ---");

        for (Odontologo o : odontologoController.listar()) {
            System.out.println(o);
        }
    }

    private void buscar() {
        System.out.print("Ingrese ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Odontologo o = odontologoController.buscarPorId(id);

        if (o != null) {
            System.out.println(o);
        } else {
            System.out.println("No encontrado.");
        }
    }

    private void eliminar() {
        System.out.print("Ingrese ID a eliminar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        odontologoController.eliminar(id);
        System.out.println("Eliminado si existia.");
    }

}
